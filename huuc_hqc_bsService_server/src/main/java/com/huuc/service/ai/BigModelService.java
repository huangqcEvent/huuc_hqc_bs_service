package com.huuc.service.ai;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.Gson;
import lombok.Data;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BigModelService extends WebSocketListener {

    private static final Logger logger = LoggerFactory.getLogger(BigModelService.class);

    @Value("${xunFei.ai.hostUrl}")
    private String hostUrl;
    @Value("${xunFei.ai.domain}")
    private String domain;
    @Value("${xunFei.ai.appId}")
    private String appid;
    @Value("${xunFei.ai.apiSecret}")
    private String apiSecret;
    @Value("${xunFei.ai.apiKey}")
    private String apiKey;

    private static final LinkedList<RoleContent> historyList = new LinkedList<>();

    private static String totalAnswer = "";

    private static boolean wsCloseFlag = false;

    private static String NewQuestion = "";

    public String chat(String question) throws Exception {
        NewQuestion = question;
        String authUrl = getAuthUrl(hostUrl, apiKey, apiSecret);
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = authUrl.replace("http://", "ws://").replace("https://", "wss://");
        Request request = new Request.Builder().url(url).build();
        totalAnswer = "";
        wsCloseFlag = false;
        client.newWebSocket(request, this);
        synchronized (this) {
            while (!wsCloseFlag) {
                this.wait(200);
            }
        }
        return totalAnswer;
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        MyThread myThread = new MyThread(webSocket);
        myThread.start();
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        JsonParse myJsonParse = new Gson().fromJson(text, JsonParse.class);
        if (myJsonParse.header.code != 0) {
            logger.error("发生错误，错误码为：{}, 本次请求的sid为：{}", myJsonParse.header.code, myJsonParse.header.sid);
            webSocket.close(1000, "");
        }
        List<Text> textList = myJsonParse.payload.choices.text;
        for (Text temp : textList) {
            totalAnswer += temp.content;
        }
        if (myJsonParse.header.status == 2) {
            if (canAddHistory()) {
                RoleContent roleContent = new RoleContent();
                roleContent.setRole("assistant");
                roleContent.setContent(totalAnswer);
                historyList.add(roleContent);
            } else {
                historyList.removeFirst();
                RoleContent roleContent = new RoleContent();
                roleContent.setRole("assistant");
                roleContent.setContent(totalAnswer);
                historyList.add(roleContent);
            }
            synchronized (this) {
                wsCloseFlag = true;
                this.notifyAll();
            }
        }
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        try {
            if (response != null) {
                int code = response.code();
                logger.error("onFailure code: {}", code);
                if (response.body() != null) {
                    logger.error("onFailure body: {}", response.body().string());
                }
                if (101 != code) {
                    logger.error("connection failed");
                    throw new RuntimeException("WebSocket connection failed");
                }
            }
        } catch (IOException e) {
            logger.error("IOException in onFailure", e);
        }
    }

    public static boolean canAddHistory() {
        int historyLength = 0;
        for (RoleContent temp : historyList) {
            historyLength += temp.getContent().length();
        }
        if (historyLength > 12000) {
            int removeCount = Math.min(historyList.size(), 5);
            for (int i = 0; i < removeCount; i++) {
                historyList.removeFirst();
            }
            return false;
        } else {
            return true;
        }
    }

    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
        URL url = new URL(hostUrl);
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        String preStr = "host: " + url.getHost() + "\n" +
                "date: " + date + "\n" +
                "GET " + url.getPath() + " HTTP/1.1";
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
        mac.init(spec);
        byte[] hexDigits = mac.doFinal(preStr.getBytes(StandardCharsets.UTF_8));
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder()
                .addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8)))
                .addQueryParameter("date", date)
                .addQueryParameter("host", url.getHost())
                .build();
        return httpUrl.toString();
    }

    class MyThread extends Thread {
        private final WebSocket webSocket;

        public MyThread(WebSocket webSocket) {
            this.webSocket = webSocket;
        }

        public void run() {
            try {
                JSONObject requestJson = buildRequestJson(NewQuestion);
                webSocket.send(requestJson.toString());
                synchronized (BigModelService.this) {
                    while (!wsCloseFlag) {
                        BigModelService.this.wait(200);
                    }
                }
                webSocket.close(1000, "");
            } catch (Exception e) {
                logger.error("Exception in MyThread", e);
            }
        }
    }

    private JSONObject buildRequestJson(String question) {
        JSONObject requestJson = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("app_id", appid);
        header.put("uid", UUID.randomUUID().toString().substring(0, 10));

        JSONObject parameter = new JSONObject();
        JSONObject chat = new JSONObject();
        chat.put("domain", domain);
        chat.put("temperature", 0.5);
        chat.put("max_tokens", 4096);
        parameter.put("chat", chat);

        JSONObject payload = new JSONObject();
        JSONObject message = new JSONObject();
        JSONArray text = new JSONArray();

        if (!historyList.isEmpty()) {
            for (RoleContent tempRoleContent : historyList) {
                text.add(JSON.toJSON(tempRoleContent));
            }
        }

        RoleContent roleContent = new RoleContent();
        roleContent.setRole("user");
        roleContent.setContent(question);
        text.add(JSON.toJSON(roleContent));
        historyList.add(roleContent);

        message.put("text", text);
        payload.put("message", message);

        requestJson.put("header", header);
        requestJson.put("parameter", parameter);
        requestJson.put("payload", payload);

        return requestJson;
    }

    static class JsonParse {
        Header header;
        Payload payload;
    }

    static class Header {
        int code;
        int status;
        String sid;
    }

    static class Payload {
        Choices choices;
    }

    static class Choices {
        List<Text> text;
    }

    static class Text {
        String role;
        String content;
    }

    @Data
    static class RoleContent {
        String role;
        String content;
    }
}
