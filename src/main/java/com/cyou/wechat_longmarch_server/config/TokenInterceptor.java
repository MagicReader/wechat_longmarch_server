package com.cyou.wechat_longmarch_server.config;

import com.cyou.wechat_longmarch_server.entity.responseBody.CommonResponseBody;
import com.cyou.wechat_longmarch_server.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Token 拦截验证器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
        String token=request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            this.forbiddenRequest(response);
            return false;
        }

        String userId = (String) redisUtil.get(token);
        if(StringUtils.isEmpty(userId)){
            this.forbiddenRequest(response);
            return false;
        }

        request.setAttribute("userId",userId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void forbiddenRequest(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(JSON.toJSONString(CommonResponseBody.forbidden()));
        out.flush();
        out.close();
    }
}
