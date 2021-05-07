package com.ourchat.system.login.mapper;

import com.ourchat.system.login.entity.Customer;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface UserMapper {
    @Select("select phone_number,password from customer where phone_number=#{phoneNumber}")
    @Result(column = "phone_number",property = "phoneNumber",jdbcType = JdbcType.VARCHAR)
    Customer getUserByPhoneNumber(String phoneNumber);
}
