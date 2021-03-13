package com.example.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author JonSnow
 * @description TODO
 * @date 2020/11/23
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {

    private final Object phoneNum;
    private Object smsCode;

    public SmsAuthenticationToken(Object phoneNum, Object smsCode, List<GrantedAuthority> authorities) {
        super(authorities);
        this.phoneNum = phoneNum;
        this.smsCode = smsCode;
        this.setAuthenticated(false);
    }

    public SmsAuthenticationToken(Object phoneNum, Object smsCode) {
        super(null);
        this.phoneNum = phoneNum;
        this.smsCode = smsCode;
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return smsCode;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return phoneNum;
    }

    @Override
    public String getName() {
        return Optional.ofNullable(phoneNum).orElse("").toString();
    }

}
