package com.ourchat.common.netty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettybootServerInitConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${netty.port}")
    private int port;

    @Value("${netty.path}")
    private String path;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            WssServer.getInstance(path).start(port);
        }
    }
}
