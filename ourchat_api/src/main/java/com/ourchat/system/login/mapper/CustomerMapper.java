package com.ourchat.system.login.mapper;

import com.ourchat.system.login.entity.Customer;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface CustomerMapper {
    @Select("select email,password from customer where email=#{email}")
    Customer getCustomerEmail(String email);

    @Insert("insert into customer (email,password,name,gender,age,address,icon_path) values (#{email},#{password},#{name},#{gender},#{age},#{address},#{iconPath})")
    Boolean addCustomer(Customer customer);
}
