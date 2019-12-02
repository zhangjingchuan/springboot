package com.youxuewen.wenxin.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * 处理消息的handler
 * TextWebSocketFrame :再netty中，适用于为websocket专门处理文本的对象，frame是消息的载体
 */
public class  ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有客户端的channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

        //获取客户端传输过来的消息
        String content = textWebSocketFrame.text();
        System.out.println("接收到的数据:"+content);

        for(Channel channel : clients){
            channel.writeAndFlush(new TextWebSocketFrame("[服务器在：]"+ LocalDateTime.now()+"接收到消息， 消息为:"+content));
        }
//        clients.writeAndFlush(new TextWebSocketFrame("[服务器在：]"+ LocalDateTime.now()+"接收到消息， 消息为:"+content));
    }

    /**
     * 添加客户端触发
     * 当客户端连接到服务端之后（打开连接）
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        clients.add(channel);
    }

    /**
     * 客户端离开触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved， ChannelGroup会自动移除对应客户端的channel
        Channel channel = ctx.channel();
//        clients.remove(channel);

        System.out.println("客户端断开，channel对应的长id为:"+channel.id().asLongText());
        System.out.println("客户端断开，channel对应的短id为:"+channel.id().asShortText());
    }
}
