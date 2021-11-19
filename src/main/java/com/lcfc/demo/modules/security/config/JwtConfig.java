package com.lcfc.demo.modules.security.config;


import com.lcfc.demo.modules.security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;


@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtConfig {

    // 密钥
    private String secret;

    // 过期时间
    private int expire;

    //
    private String header;


    /**
     * 根据用户生成token
     * @param token
     * @return  返回一个token
     */
    public String createToken (UsernamePasswordToken token){
        //获取当前时间
        Date nowDate = new Date();
        // 设置
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);//过期时间

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)  // ??
                .setSubject(token.getUsername())  // 包含用户名
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret) // 签名算法
                .compact();
    }


    // 获取 claim
    public Claims getTokenClaim (String token) {
        try {
            // 后面判断token是否失效
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }



    // 判断token是否过期
    public boolean isTokenExpired (String token) {
        return getTokenClaim(token).getExpiration().before(new Date());
    }

}
