package com.ourchat.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WssServer {
    /**
     * 单例静态内部类
     */

    public static class SingletionWSServer {
        static final WssServer instance = new WssServer();
    }

    public static WssServer getInstance() {
        return SingletionWSServer.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WssServer() {

        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WssServerInitialzer()); // 添加自定义初始化处理器

    }
    public void start() {
        future = this.server.bind(8086);
//        System.err.println("netty 服务端启动成功 .....");
    }
}
