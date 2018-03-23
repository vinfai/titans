package com.titans.netty.example01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * echoServer
 *
 * @author fangwenhui
 * @date 2018-03-23 17:39
 **/
public class EchoServer {


    /**
     *    start netty server
     */
    public void start() throws InterruptedException {
        int port = 12345;
        EventLoopGroup group = new NioEventLoopGroup();//使用NIO传输
        try {

            EchoServerHandler handler = new EchoServerHandler();



            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class)//2.NIO传输指定channel的类型
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(handler);
                        }
                    });
            ChannelFuture f = b.bind().sync();//异步绑定服务器，阻塞等待直到绑定完成
            f.channel().closeFuture().sync();//channel 阻塞知道关闭通知返回。

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync(); //关闭资源
        }
    }


}
