package com.ourchat.system.login.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@ApiModel("用户实体类")
public class Customer implements UserDetails {
    @ApiModelProperty("手机号")
    private String phoneNumber;
    @ApiModelProperty("密码")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }
    //账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    //账户是否被锁
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    //密码是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    //账户是否可用
    @Override
    public boolean isEnabled() {
        return false;
    }
}
