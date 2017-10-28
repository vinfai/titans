package com.titans.mq.activemq.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * activemq 消息接收，点对点
 * @author vinfai
 * @since 2017/5/26
 */
public class JmsConsumer {

    static String brokeUrl = "tcp://192.168.8.109:9521";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);
        Connection connection = connectionFactory.createConnection();
        connection.start();//
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("mytestQueue1");
        MessageConsumer consumer = session.createConsumer(destination);
        //异步监听方式
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage tm = (TextMessage)message;
                try {
                    System.out.println(tm.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("haha");

            }
        });
        /*while (true) {
            TextMessage receive = (TextMessage)consumer.receive();
            System.out.println(receive.getText());
        }*/

    }
}
