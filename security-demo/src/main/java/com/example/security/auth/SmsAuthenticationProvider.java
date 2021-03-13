package com.example.security.auth;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author JonSnow
 * @description TODO
 * @date 2020/11/23
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String verificatioinCode = stringRedisTemplate.opsForValue().get(authentication.getPrincipal());
//        if (!Objects.equals(verificatioinCode, authentication.getCredentials())) {
//            throw new InternalAuthenticationServiceException("短信验证失败");
//        }

        //这里应该从数据库查询角色
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        SmsAuthenticationToken authResult = new SmsAuthenticationToken(authentication.getPrincipal(), authentication.getPrincipal(), authorities);
        authResult.setAuthenticated(true);
        return authResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
}
