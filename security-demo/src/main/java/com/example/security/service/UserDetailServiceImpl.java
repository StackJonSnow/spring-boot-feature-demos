package com.example.security.service;

import com.example.security.dao.UserRepository;
import com.example.security.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author JonSnow
 * @description TODO
 * @date 2020/11/18
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        Optional<User> userOp = Optional.ofNullable(user);
        return userOp.orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
    }
}
