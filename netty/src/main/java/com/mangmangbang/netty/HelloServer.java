package com.mangmangbang.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 实现客户端发送一个请求，服务器会返回hello netty
 */
public class HelloServer {

    public static void main(String [] args)throws Exception{
        //定义一对线程组
        //主线程组，用于接收客户端的连接，但是不做任何处理，跟老板一样，不做事
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        //从线程组，主线程组会把任务丢给从县城组，让手下线程组去做任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //netty服务器的创建
            //服务端启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //为启动类设置主从线程组
            serverBootstrap.group(bossGroup, workerGroup)
                    //设置nio的双向通道
                    .channel(NioServerSocketChannel.class)
                    //子处理器，用于处理workerGroup
                    .childHandler(new HelloServerInitializer());

            //启动server,并且设置9090为启动的端口号，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(9090).sync();

            //监听关闭的channel,设置为同步方式
            channelFuture.channel()//获取某个客户端的通道
                    .closeFuture().sync();
        }finally {
            //关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
