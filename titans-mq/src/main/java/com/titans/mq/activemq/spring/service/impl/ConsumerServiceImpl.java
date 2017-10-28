package com.titans.mq.activemq.spring.service.impl;

import com.titans.mq.activemq.spring.service.ConsumerService;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Map;

/**
 * @author vinfai
 * @since 2017/6/1
 */
@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService {

    @Resource
    private JmsTemplate jmsTemplate;

    @Override
    public void receive(Destination destination) {
        Message message = jmsTemplate.receive(destination);
        if (message instanceof TextMessage) {
            try {
                System.out.println(((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else if (message instanceof MapMessage){
            ActiveMQMapMessage map = (ActiveMQMapMessage)message;
            System.out.println("received map: "+map);
            try {
                Map<String, Object> contentMap = map.getContentMap();
                for (String s : contentMap.keySet()) {
                    System.out.println(s+"-->"+contentMap.get(s));
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        System.out.println("接收到消息" + message.toString() + ",from :" + destination.toString());
    }
}
