package mq_1018.mq_1018_producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Hello world!
 *
 */
public class App2 {
	public static void main(String[] args) throws JMSException {
		// 发送一个队列模式的消息
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// 创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 消息对象
		Topic topic = session.createTopic("office-topic");

		// 消息内容
		TextMessage textMessage = session.createTextMessage("为尚硅谷的伟大复兴而努力奋斗...");

		// 发送端
		MessageProducer producer = session.createProducer(topic);

		// 发送消息
		producer.send(textMessage);

		// 关闭连接
		producer.close();
		session.close();
		connection.close();

	}
}
