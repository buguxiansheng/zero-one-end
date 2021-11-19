package com.lcfc.demo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "CorsFilter")
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //对那些请求路径进行跨域处理
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                // 允许的请求头，默认允许所有的请求头
                .allowedHeaders("*")
                // 允许的方法，默认允许GET、POST、HEAD
                .allowedMethods("*")
                // 探测请求有效时间，单位秒
                .maxAge(1800);
    }
}
