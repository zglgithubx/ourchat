package com.ourchat.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Slf4j
public class WssServer {

    /**
     * 单例静态内部类
     */
    private static String path;
    private static class SingletionWSServer {
        static final WssServer instance = new WssServer(path);
    }

    public static WssServer getInstance(String nettyPath) {
        path=nettyPath;
        return SingletionWSServer.instance;
    }

    private final ServerBootstrap server;
    private WssServer(String path) {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WssServerInitialzer(path)); // 添加自定义初始化处理器
    }
    public void start(int port) {
        ChannelFuture future = this.server.bind(port);
        log.info("Netty started on port(s): "+port+" (ws) with context path '"+path+"'");
    }
}
