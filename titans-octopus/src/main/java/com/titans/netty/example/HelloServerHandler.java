package com.titans.netty.example;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

import java.util.Collection;
import java.util.TreeMap;

/**
 * @author vinfai
 * @since 2016/10/24
 */
public class HelloServerHandler extends SimpleChannelHandler {


    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) throws Exception {
       //super.messageReceived(ctx, event);
        System.out.println(event.getMessage());//使用stringDecode
        //System.out.println(" client channel instkey :"+ctx.getChannel().getInterestOps());
        ctx.getChannel().write(" msg from server:"+event.getMessage());
        //ctx.getChannel().setInterestOps(Channel.OP_READ_WRITE);
        /*ChannelBuffer buf = (ChannelBuffer)event.getMessage();
        while (buf.readable()){
            System.out.print((char) buf.readByte());
            System.out.flush();
        }
        System.out.println();
        ChannelFuture future = ctx.getChannel().write((ChannelBuffer) event.getMessage());
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("operationComplete"+future.isSuccess());
            }
        });
        System.out.println(ctx.getChannel().isWritable());
        super.messageReceived(ctx, event);*/
//        ch.write();
        //System.out.println(event.getMessage());
        //System.out.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
//        super.exceptionCaught(ctx, e);
        e.getCause().printStackTrace();
        Channel channel = e.getChannel();
        channel.close();
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent event) throws Exception {
        String msg = "I'm server";
        Channel ch = event.getChannel();
        ChannelBuffer buffer = ChannelBuffers.buffer(20);
        buffer.writeBytes(msg.getBytes("UTF-8"));
        ctx.getChannel().write(buffer);
    }

   /* @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent event) throws Exception {
       // super.writeRequested(ctx, e);
        Channel ch = event.getChannel();
        //ch.write((ChannelBuffer) event.getMessage());
        System.out.println(((ChannelBuffer)event.getMessage()).capacity());
    }*/
}
