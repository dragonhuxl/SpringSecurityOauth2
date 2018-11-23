package com.example.demo.config;

import com.example.demo.common.APIResult;
import com.example.demo.common.HTTPCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @AUTHOR huxl
 * @DATE 2018/11/19 17:32
 * @DES 自定义认证异常类
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        APIResult apiResult = new APIResult(HTTPCode.TOKENERR);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().println(apiResult);
    }
}
