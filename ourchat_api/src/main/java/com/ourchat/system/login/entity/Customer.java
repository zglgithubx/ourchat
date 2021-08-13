package com.ourchat.system.login.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@ApiModel("用户实体类")
public class Customer implements UserDetails {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("邮箱")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("密码")
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("昵称")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("性别")
    private Boolean gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("年龄")
    private Byte age;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("地址")
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("个性签名")
    private String signature;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("头像路径")
    private String iconPath;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("背景路径")
    private String backgroundImagePath;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("是否漫游")
    private Boolean isRoaming;

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", signature='" + signature + '\'' +
                ", iconPath='" + iconPath + '\'' +
                ", backgroundImagePath='" + backgroundImagePath + '\'' +
                ", isRoaming=" + isRoaming +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    public Boolean getRoaming() {
        return isRoaming;
    }

    public void setRoaming(Boolean roaming) {
        isRoaming = roaming;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return email;
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
