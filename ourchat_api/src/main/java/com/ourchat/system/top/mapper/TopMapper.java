package com.ourchat.system.top.mapper;

import com.ourchat.system.login.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopMapper {
    @Select("select * from customer where email=#{email}")
    Customer searchFriend(String email);

//    @Select("select * from customer where name like \"'%'#{name}'%'\"")
@Select("select * from customer where name like CONCAT('%',#{name},'%')")
    List<Customer> searchFriendName(String name);
}
