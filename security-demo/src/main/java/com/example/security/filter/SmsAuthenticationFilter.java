package com.example.security.filter;

import com.example.security.auth.SmsAuthenticationToken;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * @author JonSnow
 * @description TODO
 * @date 2020/11/20
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private String phoneNumParameter = "phoneNum";
    private String verifyCodeParameter = "verifyCode";
    private boolean postOnly = true;

    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher("/sms/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (request.getRequestURI().contains("login.html")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            Set<String> parameterNames = request.getParameterMap().keySet();
            if (!parameterNames.contains("phoneNum") || !parameterNames.contains("verifyCode")) {
                return null;
            }

            String phoneNum = this.obtainPhoneNum(request);
            String verifyCode = this.obtainVerifyCode(request);
            if (Objects.isNull(phoneNum) || Objects.isNull(verifyCode)) {
                return null;
            }
            SmsAuthenticationToken  authRequest = new SmsAuthenticationToken(phoneNum, verifyCode);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    @Nullable
    protected String obtainPhoneNum(HttpServletRequest request) {
        return request.getParameter(this.phoneNumParameter);
    }

    @Nullable
    protected String obtainVerifyCode(HttpServletRequest request) {
        return request.getParameter(this.verifyCodeParameter);
    }

}
