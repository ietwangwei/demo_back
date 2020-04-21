package com.example.demo.config;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        // System.out.println("----CrossFilter init----");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        // 这里填写你允许进行跨域的主机ip
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的Header值，不支持通配符
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                "*,ACCESS-TOKEN,OPENID,APPID,X-Request-Token,Cache-Control,Content-Language,Content-Type,Expires,Last-Modified,Pragma,X-Requested-With");
        // 允许的访问方法
        httpServletResponse.setHeader("Access-Control-Allow-Methods",
                "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        httpServletResponse.setHeader("Access-Control-Max-Age", "1209600");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (null != httpRequest && "OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            return;
        }
        filterChain.doFilter(servletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
        // System.out.println("----CrossFilter destroy----");
    }
}
