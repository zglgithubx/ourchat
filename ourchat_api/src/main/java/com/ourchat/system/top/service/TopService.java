package com.ourchat.system.top.service;

import com.ourchat.system.login.entity.Customer;
import com.ourchat.system.top.entity.CustomerVO;

import java.util.List;

public interface TopService {
    CustomerVO searchFriend(String criteria);
    List<CustomerVO> searchFriendName(String criteria);
}
