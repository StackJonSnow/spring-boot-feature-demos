package com.example.security.config;

import com.example.security.auth.SmsAuthenticationManager;
import com.example.security.auth.SmsAuthenticationProvider;
import com.example.security.filter.SmsAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

/**
 * @Author JonSnow
 * @Date 2020/4/21
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("user"));
        System.out.println(encoder.encode("admin"));
        System.out.println(encoder.encode("test"));
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("admin")
//            .password("$2a$10$wjqiNgqLiTWOgEL2hlH15uVomi3lSj/GeolINLl.Shud.btxkuv1u")
//            .roles("ADMIN", "USER")
//            .and()
//            .withUser("user")
//            .password("$2a$10$BhT2mWP.4GlffxvxnhNFVuSES.wgBSxtULtKkAc6DXXLnlRKRDuLm")
//            .roles("USER");
        //基于数据库用户信息认证
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler("/smslogin.html");
        smsAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler("/index");
        smsAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        smsAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/sms/login"));
        SmsAuthenticationManager smsAuthenticationManager = new SmsAuthenticationManager();
        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setStringRedisTemplate(stringRedisTemplate);
        smsAuthenticationManager.setAuthenticationProvider(smsAuthenticationProvider);
        smsAuthenticationFilter.setAuthenticationManager(smsAuthenticationManager);
        http.authorizeRequests()
            //user开头的资源只有拥有USER权限的用户才能访问，admin同理
            .antMatchers("/user/**").hasRole("USER")
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/index*").hasRole("USER")
            .antMatchers("**/login", "/login.html", "/smslogin.html", "/**/*.js", "/**/*.css").permitAll()
            .anyRequest().authenticated()
            .and()
//            loginPage:请求登录页的路径，loginProcessingUrl:登录接口，defaultSuccessUrl:若指定访问某个地址，则登录后跳转至指定地址，否则跳转至此处设置路径
            .formLogin().loginPage("/login.html").loginProcessingUrl("/form/login").defaultSuccessUrl("/index")
            .usernameParameter("username").passwordParameter("password")
            .and()
            .logout()
            .and()
            .addFilterBefore(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//            .rememberMe((rememberMe) -> rememberMe.rememberMeServices(rememberMeServices()))
            .csrf().disable();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//
//    }

    /**
     * spring security 5.0 以后要求密码必须加密，所以这里要提供一个密码加密实例
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public Filter smsAuthenticationFilter() {
//        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
//        smsAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/sms/login"));
//        smsAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        return smsAuthenticationFilter;
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    public AuthenticationManager authenticationManager() {
//        SmsAuthenticationManager smsAuthenticationManager = new SmsAuthenticationManager();
//        smsAuthenticationManager.setAuthenticationProvider(smsAuthenticationProvider());
//        return smsAuthenticationManager;
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    public SmsAuthenticationProvider smsAuthenticationProvider() {
//        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
//        smsAuthenticationProvider.setStringRedisTemplate(stringRedisTemplate);
//        return smsAuthenticationProvider;
//    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices =
                new SpringSessionRememberMeServices();
        // optionally customize
//        rememberMeServices.setAlwaysRemember(true);
        rememberMeServices.setValiditySeconds(10);
        return rememberMeServices;
    }

}
