package com.titans.netty.example;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * netty 入门
 * @author vinfai
 * @since 2016/10/24
 */
public class HelloServer {

    public static void main(String[] args) {
        //ChannelFactory is a factory which creates and manages Channels and its related resources.
        NioServerSocketChannelFactory nioServerSocketChannelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());

        //
        ServerBootstrap bootstrap = new ServerBootstrap(nioServerSocketChannelFactory);

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("encode",new StringEncoder());
                pipeline.addLast("decode",new StringDecoder());
                pipeline.addLast("handler",new HelloServerHandler());
                return pipeline;
//                return Channels.pipeline(new HelloServerHandler()).addBefore("encode", );
            }
        });

        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(8888));
    }
}
