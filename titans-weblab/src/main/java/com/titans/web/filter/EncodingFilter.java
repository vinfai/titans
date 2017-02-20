package com.titans.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * EncodingFilter
 * @author vinfai
 * @since 2016/10/8
 */
@WebFilter(filterName = "encodingFilter",value = "/*",asyncSupported = true,initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
public class EncodingFilter implements Filter{
    private String encode = "";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encode = filterConfig.getInitParameter("encoding");
        System.out.println("encode is :"+encode);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encode);
        response.setCharacterEncoding(encode);
        System.out.println("EncodingFilter set encoding now."+encode);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("encoding EncodingFilter is destory");
    }
}
