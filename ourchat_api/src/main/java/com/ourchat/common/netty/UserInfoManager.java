package com.ourchat.common.netty;

import com.ourchat.common.redis.OfflineManagement;
import com.ourchat.system.message.entity.OfflineMessage;
import com.ourchat.common.utils.BlankUtil;
import com.ourchat.common.utils.NettyUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserInfoManager {
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);
//    private static ConcurrentMap<Channel, UserInfo> userInfos = new ConcurrentHashMap<>();
    private static ConcurrentMap<String, UserInfo> userInfos = new ConcurrentHashMap<>();
    /**
     * 登录注册 channel
     */
    public static void addChannel(Channel channel, String uid) {
        String remoteAddr = NettyUtil.parseChannelRemoteAddr(channel);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(uid);
        userInfo.setAddr(remoteAddr);
        userInfo.setChannel(channel);
        userInfos.put(uid,userInfo);
    }
    public static void removeChannel(Channel channel){
        Set<String> keySet=userInfos.keySet();
        for(String string:keySet){
            if(userInfos.get(string).getChannel()==channel){
                userInfos.remove(string);
                break;
            }
        }
        System.out.println("移除channel成功");
    }

    /**
     * 普通消息
     * @param message
     */
    public static void broadcastMess(String sender,String recevier,String message) {
        if (!BlankUtil.isBlank(message)) {
            try {
                rwLock.readLock().lock();
                if(userInfos.containsKey(recevier)){
                    //用户在线发送即时消息
                    String backmessage=sender+":"+message;
                    userInfos.get(recevier).getChannel().writeAndFlush(new TextWebSocketFrame(backmessage));
                    System.out.println("用户收到消息"+backmessage);
                }else{
                    System.out.println("用户："+recevier+"不在线");
                    //用户不在线，存储离线消息
                    dealWithOffline(sender,recevier,message);
                    System.out.println("离线消息存储完成");
                }
            } finally {
                rwLock.readLock().unlock();
            }
        }
    }
    public static void dealWithOffline(String sender,String recevier,String message){
        OfflineMessage offlineMessage=new OfflineMessage();
        offlineMessage.setSender(sender);
        offlineMessage.setMessage(message);
        offlineMessage.setSendDate(new Date());
        List<OfflineMessage> list=new ArrayList<>();
        list.add(offlineMessage);
        OfflineManagement.addMessage(recevier,offlineMessage);
        System.out.println("成功保存离线消息");
    }

}
