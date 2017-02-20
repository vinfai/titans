package com.titans.netty.example;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author vinfai
 * @since 2016/10/25
 */
public class HelloClientHandler extends SimpleChannelHandler {

    private final ChannelBuffer firstMessage;
    private final AtomicLong transferredBytes = new AtomicLong();


    public HelloClientHandler() {
        firstMessage = ChannelBuffers.buffer(1000);
        for (int i = 0; i < firstMessage.capacity(); i ++) {
            firstMessage.writeByte((char) i);
        }
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        // Send the first message.  Server will not send anything here
        // because the firstMessage's capacity is 0.
        //e.getChannel().write(firstMessage);
        String msg = "channelConnected event.hello,i'm client.";
        ChannelBuffer buffer = ChannelBuffers.buffer(msg.length());
        try {
            buffer.writeBytes(msg.getBytes("UTF-8"));
            e.getChannel().write(buffer);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        // Send back the received message to the remote peer.
       /* transferredBytes.addAndGet(((ChannelBuffer) e.getMessage()).readableBytes());
        e.getChannel().write(e.getMessage());*/
//        System.out.println("channel."+((ChannelBuffer)e.getMessage()).toString("utf-8"));
        System.out.println("channel."+e.getMessage());
        int i = 0;
       for(;;){
           try {
               Thread.sleep(5000);
               ChannelBuffer buffer = ChannelBuffers.buffer(10);
               buffer.writeBytes(("hello"+i).getBytes("UTF-8"));
               e.getChannel().write(buffer);

               i++;
           } catch (Exception e1) {
               e1.printStackTrace();
           }

       }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        // Close the connection when an exception is raised.
        e.getCause().printStackTrace();
        e.getChannel().close();
    }

    public long getTransferredBytes() {
        return transferredBytes.get();
    }
}
