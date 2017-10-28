package com.titans.mq.activemq.spring.service.impl;

import com.titans.mq.activemq.spring.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author vinfai
 * @since 2017/6/1
 */
@Service("producerService")
public class ProducerServiceImpl implements ProducerService {

    @Resource
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(String msg) {
        String defaultDestination = jmsTemplate.getDefaultDestination().toString();
        System.out.println("send message to " + defaultDestination + ";msg:" + msg);
        jmsTemplate.send(session -> session.createTextMessage(msg));
    }

    @Override
    public void sendMessage(Destination destination, String msg) {
        System.out.println("send message to " + destination.toString() + ";msg:" + msg);
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}
