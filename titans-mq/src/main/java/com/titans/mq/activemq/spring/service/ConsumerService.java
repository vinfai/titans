package com.titans.mq.activemq.spring.service;


import javax.jms.Destination;

/**
 * @author vinfai
 * @since 2017/6/1
 */
public interface ConsumerService {

    void receive(Destination destination);
}
