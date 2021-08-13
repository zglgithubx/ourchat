package com.ourchat.system.top.service.impl;


import com.ourchat.system.top.entity.CustomerVO;
import com.ourchat.system.top.mapper.TopMapper;
import com.ourchat.system.top.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class TopServiceImpl implements TopService {
    @Autowired
    private TopMapper topMapper;
    @Override
    public CustomerVO searchFriend(String criteria) {
        return topMapper.searchFriend(criteria);
    }

    @Override
    public List<CustomerVO> searchFriendName(String criteria) {
        return topMapper.searchFriendName(criteria);
    }


}
