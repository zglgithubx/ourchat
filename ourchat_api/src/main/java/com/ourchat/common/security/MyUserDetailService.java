package com.ourchat.common.security;

import com.ourchat.system.login.mapper.CustomerMapper;

import com.ourchat.system.login.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        /**
         * 这里实际情况应该是根据参数s查询数据库用户数据
         */
        Customer customer = userMapper.getCustomerEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("账户不存在");
        }
        return new User(customer.getEmail(),bCryptPasswordEncoder.encode(customer.getPassword()),AuthorityUtils.NO_AUTHORITIES);
    }
}
