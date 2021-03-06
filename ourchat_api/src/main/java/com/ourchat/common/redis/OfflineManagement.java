package com.ourchat.common.redis;

import com.alibaba.fastjson.JSONObject;
import com.ourchat.system.message.entity.OfflineMessage;
import com.ourchat.common.redis.GetBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * 离线消息管理
 */
public class OfflineManagement {
    private static final String RECEIVER="RECEIVER:";
    private static RedisTemplate redisTemplate=(RedisTemplate) GetBean.getBean("redisTemplate");
    /**
     * 新增消息
     */
    public static void addMessage(String recevier, OfflineMessage offlineMessage){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        List<OfflineMessage> list=null;
        if(isExistsMessage(recevier)){
            list= JSONObject.parseArray(valueOperations.get(RECEIVER+recevier).toString(),OfflineMessage.class);
        }else{
            list=new ArrayList<>();
        }
        list.add(offlineMessage);
        valueOperations.set(RECEIVER+recevier,list);
    }

    /**
     * 查询是否有离线消息
     */
    public static boolean isExistsMessage(String receiver){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        return valueOperations.get(RECEIVER+receiver)!=null;
    }

    /**
     * 读取离线消息,并删除
     */
    public static List<OfflineMessage> readMessage(String receiver){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        if(!isExistsMessage(receiver)){
            return null;
        }
        List<OfflineMessage> list=JSONObject.parseArray(valueOperations.get(RECEIVER+receiver).toString(), OfflineMessage.class);
        redisTemplate.delete(RECEIVER+receiver);
        return list;
    }
}
