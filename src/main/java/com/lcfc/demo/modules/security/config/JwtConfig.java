package com.lcfc.demo.modules.security.config;


import com.lcfc.demo.modules.security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtConfig {

    // 密钥
    @Value("${config.jwt.secret:abcdefg1234567}")
    private String secret;

    // 过期时间
    @Value("${config.jwt.expire}")
    private int expire;

    //
    @Value("${config.jwt.header}")
    private String header;


    /**
     * 根据用户生成token
     * @param user
     * @return  返回一个token
     */
    public String createToken (User user){
        //获取当前时间
        Date nowDate = new Date();
        // 设置
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);//过期时间
        // role 和 permission
        Map<String,Object> rights = new HashMap<>();

        // 存放当前用户角色和权限
        rights.put("role",user.getRoles());
        rights.put("permissions",user.getPermissions());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)  // ??
                .setSubject(user.getUserName())  // 包含用户名
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .setClaims(rights)
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
