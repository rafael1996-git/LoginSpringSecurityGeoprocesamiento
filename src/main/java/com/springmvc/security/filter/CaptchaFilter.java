package com.springmvc.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import com.captcha.botdetect.web.servlet.Captcha;

public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Captcha captcha = Captcha.load(request, "securityCaptchaExample");

        if (request.getParameter("type") != null) {
            if (request.getParameter("type").equals("loginForm")) {
                if (request.getParameter("captchaCode") != null) {
                    String captchaCode = request.getParameter("captchaCode");
                    boolean isHuman = captcha.validate(captchaCode);
                    
                    if (!isHuman) {
                        // Do something if captcha invalidate.
                        String contextPath = ((HttpServletRequest) request).getContextPath();
                        ((HttpServletResponse) response).sendRedirect(contextPath + "/?wrongcaptcha");
                        return;
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }
}
