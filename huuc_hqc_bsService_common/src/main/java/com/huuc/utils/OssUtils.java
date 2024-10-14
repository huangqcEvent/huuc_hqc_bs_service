package com.huuc.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.huuc.properties.OssProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Component
public class OssUtils {
    private final OssProperties ossProperties;

    /**
     * 文件上传到Oss,返回路径
     */
    public String upload(String fileName, byte[] bytes) {
        // 连接到阿里云的Oss指定桶
        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        String objectName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        //开始上传
        InputStream inputStream = new ByteArrayInputStream(bytes);
        ossClient.putObject(ossProperties.getBucketName(), objectName, inputStream);
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(ossProperties.getBucketName())
                .append(".")
                .append(ossProperties.getEndpoint())
                .append("/")
                .append(objectName);
        log.info("文件上传成功,路径为:" + stringBuilder);
        return stringBuilder.toString();
    }
}
