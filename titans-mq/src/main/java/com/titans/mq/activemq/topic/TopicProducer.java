package com.titans.mq.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;
import java.time.LocalDateTime;

/**
 * ActiveMQ 发布与订阅
 * @author vinfai
 * @since 2017/5/27
 */
public class TopicProducer {

    static String brokeUrl = "tcp://192.168.8.109:9521";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //不同点：使用createtopic.
        Topic mytopic = session.createTopic("mytopic");
        MessageProducer producer = session.createProducer(mytopic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        int  i = 0;
        int n = 10;
        while (true) {
            TextMessage message = new ActiveMQTextMessage();
            message.setText("topic,haha" + LocalDateTime.now());
            producer.send(message);
            System.out.println(message);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            //发送N条消息
            if (i == n) {
                break;
            }

        }
        session.close();
        connection.close();

    }
}
