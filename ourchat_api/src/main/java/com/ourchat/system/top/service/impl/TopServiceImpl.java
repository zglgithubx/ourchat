package com.ourchat.system.top.service.impl;

import com.ourchat.system.login.entity.Customer;
import com.ourchat.system.top.mapper.TopMapper;
import com.ourchat.system.top.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class TopServiceImpl implements TopService {
    @Autowired
    private TopMapper topMapper;
    @Override
    public Customer searchFriend(String criteria) {
        if (( criteria != null) && (! criteria.isEmpty())) {
            if(Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$",criteria)){
                return topMapper.searchFriend(criteria);
            }else{
                System.out.println(topMapper.searchFriendName(criteria).toString());
                return null;
            }
        }
        return null;
    }
}
