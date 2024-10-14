package com.huuc.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @author Jwt工具类
 */
public class JwtUtils {
    public static String createJwt(String secretKey,Long ttlMillis, Map<String, Object> claims){
         // 指定签名的时候使用的签名算法，也就是header那部分
         SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

         // 生成JWT的过期时间
         long expMillis = System.currentTimeMillis() + ttlMillis;
         Date exp = new Date(expMillis);
         //创建一个JwtBuilder对象
         JwtBuilder jwtBuilder = Jwts.builder();
         return jwtBuilder
                 // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                 .setClaims(claims)
                 // 设置签名使用的签名算法和签名使用的秘钥,密钥就是签名
                 .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                 // 设置过期时间
                 .setExpiration(exp)
                 .compact();
     }

    public static Claims parseJWT(String secretKey, String token) {
        return Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
    }
}
