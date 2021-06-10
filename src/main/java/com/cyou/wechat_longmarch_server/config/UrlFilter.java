package com.cyou.wechat_longmarch_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * url拦截器
 */
@Configuration
public class UrlFilter implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TokenInterceptor拦截器
        InterceptorRegistration registration1 = registry.addInterceptor(tokenInterceptor);
        registration1.addPathPatterns(//添加拦截路径
                "/api/march/**");
        registration1.excludePathPatterns(//添加不拦截路径
                "/api/user/**");
    }
}
