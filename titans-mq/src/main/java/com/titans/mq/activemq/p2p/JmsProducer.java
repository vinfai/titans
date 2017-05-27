package com.titans.mq.activemq.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.time.LocalDateTime;

/**
 * ActiveMQ 消息生产者 例子
 * @author vinfai
 * @since 2017/5/26
 */
public class JmsProducer {

    static String brokeUrl = "tcp://192.168.8.109:9521";
    public static void main(String[] args) throws JMSException {
        //1.创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);
        Connection connection = connectionFactory.createConnection();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("mytestQueue1");
        //会话中的生产者
        MessageProducer producer = session.createProducer(destination);
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        int  i = 0;
        int n = 1;
        //send message
        while (true) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("hello_" + LocalDateTime.now());
            producer.send(textMessage);

            System.out.println("sendMessage :" + textMessage);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            //发送N条消息
            if (i == n) {
                break;
            }
        }
    }

}
