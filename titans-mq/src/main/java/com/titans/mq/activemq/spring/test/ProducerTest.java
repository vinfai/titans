package com.titans.mq.activemq.spring.test;

import com.titans.mq.activemq.spring.service.ConsumerService;
import com.titans.mq.activemq.spring.service.ProducerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.Destination;

/**
 * @author vinfai
 * @since 2017/6/1
 */
public class ProducerTest {
    public static void main(String[] args) {
        //classpath
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-*.xml");

        ProducerService producerService = (ProducerService) context.getBean("producerService");
        ConsumerService consumerService = (ConsumerService) context.getBean("consumerService");
        Destination destination = (Destination) context.getBean("defaultDestination");
        producerService.sendMessage("你好");

        consumerService.receive(destination);

    }
}
