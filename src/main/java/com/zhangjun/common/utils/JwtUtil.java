package com.zhangjun.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @Author zhangjun
 * @Date 2025/4/28 02:49
 * @Version 1.0
 */
@Component
public class JwtUtil {

    //有效期为一个小时
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    //设置密钥明文(盐)
    public static final String JWT_KEY = "dongfeng";

    //生成令牌
    public static String getUUID()
    {
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }


    /**
     * 生成jwt
     * @param subject token中要存储的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis)
    {
        JwtBuilder builder = getJwtBuilder(subject,ttlMillis,getUUID());//设置过期时间
        return builder.compact();
    }

    //生成JWT的业务逻辑代码
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis,String uuid)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();

        long nowMillis = System.currentTimeMillis();//获取系统当前的时间戳
        Date now = new Date(nowMillis);
        if (ttlMillis ==null)
        {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setId(uuid)  //唯一的ID
                .setSubject(subject)    //主题 可以是JSON数据
                .setIssuer("zhangjun") //签发者
                .setIssuedAt(now) //签发时间
                .signWith( signatureAlgorithm, secretKey ) //使用HS256对称加密算法签名，第二个参数为密钥
                .setExpiration(exp);


    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis)
    {
        JwtBuilder builder = getJwtBuilder(subject,ttlMillis,id);//设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密后的密钥secretKey
     * @return
     */
    public static SecretKey generalKey()
    {
        byte[] encodeedKey = Base64.getDecoder().decode(JWT_KEY);
        SecretKey key = new SecretKeySpec(encodeedKey,0,encodeedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception
    {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


}
