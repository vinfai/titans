package com.titans.mq.activemq.spring.service;

import javax.jms.Destination;

/**
 * JMS sender
 * Created by user on 2017/6/1.
 */
public interface ProducerService {

    void sendMessage(String msg);

    void sendMessage(Destination destination, String msg);

}
