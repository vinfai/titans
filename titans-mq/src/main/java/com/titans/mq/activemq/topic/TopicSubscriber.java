package com.titans.mq.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 订阅主题，注：如果在发布主题前，没有订阅，是收不到消息的，这跟点对点的队列模式不同;则使用持久订阅方式，不会漏掉信息
 * @author vinfai
 * @since 2017/5/27
 */
public class TopicSubscriber {

    static String brokeUrl = "tcp://192.168.8.109:9521";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("client-id01");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //需要手动确认消息，测试消息的重发
//        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Topic mytopic = session.createTopic("mytopic");
        MessageConsumer consumer = session.createConsumer(mytopic);
        MessageConsumer consumer2 = session.createConsumer(mytopic);

        /**
         * Exception in thread "main" javax.jms.JMSException: You cannot create a durable subscriber without specifying a unique clientID on a Connection
         at org.apache.activemq.ActiveMQConnection.checkClientIDWasManuallySpecified(ActiveMQConnection.java:1311)
         at org.apache.activemq.ActiveMQSession.createDurableSubscriber(ActiveMQSession.java:1403)
         at org.apache.activemq.ActiveMQSession.createDurableSubscriber(ActiveMQSession.java:1347)
         at com.titans.mq.activemq.topic.TopicSubscriber.main(TopicSubscriber.java:25)

         */
        //能接受到历史消息
        /**
         * subscriber toppic:topic,haha2017-05-27T15:49:03.099
             subscriber toppic:topic,haha2017-05-27T15:49:13.109
             subscriber toppic:topic,haha2017-05-27T15:49:23.114
             consumer11:topic,haha2017-05-27T15:49:23.114
             consumer2:topic,haha2017-05-27T15:49:23.114
         使用CLIENT_ACKNOWLEDGE 时候需要手动ack，或者重启后会重新发送
         subscriber toppic:topic,haha2017-05-27T15:53:34.448
         subscriber toppic:topic,haha2017-05-27T15:53:44.456
         subscriber toppic:topic,haha2017-05-27T15:53:54.460
         subscriber toppic:topic,haha2017-05-27T15:54:04.464
         subscriber toppic:topic,haha2017-05-27T15:54:14.484
         subscriber toppic:topic,haha2017-05-27T15:54:24.490
         subscriber toppic:topic,haha2017-05-27T15:54:34.494
         subscriber toppic:topic,haha2017-05-27T15:54:44.497
         subscriber toppic:topic,haha2017-05-27T15:54:54.501
         subscriber toppic:topic,haha2017-05-27T15:55:04.505
         */
        javax.jms.TopicSubscriber subscriber = session.createDurableSubscriber(mytopic, "subsriber01");
        subscriber.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("subscriber toppic:"+textMessage.getText());
                    //使用CLIENT_ACKNOWLEDGE MODE 手动发送
                    message.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }


            }
        });
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("consumer11:"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                /*try {
                    message.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }*/
            }
        });

       consumer2.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("consumer2:"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
