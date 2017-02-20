package com.titans.netty.example;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * @author vinfai
 * @since 2016/10/25
 */
public class HelloClient {
    public static void main(String[] args) {
        ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        ClientBootstrap clientBootstrap = new ClientBootstrap(factory);
        try {
            clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
                @Override
                public ChannelPipeline getPipeline() throws Exception {

                    ChannelPipeline pipeline = Channels.pipeline();
                    pipeline.addLast("encode", new StringEncoder());
                    pipeline.addLast("decode", new StringDecoder());
                    pipeline.addLast("handler",new HelloClientHandler());
                    return pipeline;
                }
            });
            clientBootstrap.setOption("tcpNoDelay", true);
            clientBootstrap.setOption("receiveBufferSize", 1048576);
            clientBootstrap.setOption("sendBufferSize", 1048576);

            ChannelFuture future = clientBootstrap.connect(new InetSocketAddress("localhost",8888));
            future.getChannel().setInterestOps(Channel.OP_READ_WRITE);
//            System.out.println("interert ops:"+future.getChannel().getInterestOps());

            /*future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        ChannelBuffer b = ChannelBuffers.buffer(10);
                        b.writeBytes("hello".getBytes());
                        future.getChannel().write(b);
                    }else{
                        Throwable cause = future.getCause();
                        cause.printStackTrace();
                    }
                }
            });*/
            future.getChannel().getCloseFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clientBootstrap.releaseExternalResources();
        }
    }
}
