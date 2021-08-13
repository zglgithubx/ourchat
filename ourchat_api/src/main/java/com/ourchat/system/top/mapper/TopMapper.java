package com.ourchat.system.top.mapper;

import com.ourchat.system.login.entity.Customer;
import com.ourchat.system.top.entity.CustomerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopMapper {
    @Select("select * from customer where email=#{email}")
    CustomerVO searchFriend(String email);

    @Select("select * from customer where name like CONCAT('%',#{name},'%')")
    List<CustomerVO> searchFriendName(String name);
}
