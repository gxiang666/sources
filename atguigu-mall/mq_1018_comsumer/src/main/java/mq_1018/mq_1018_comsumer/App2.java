package mq_1018.mq_1018_comsumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Hello world!
 *
 */
public class App2 {
	public static void main(String[] args) throws Exception {
		hb();
	}

	public static void hb() throws Exception {
		// 创建连接
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// 创建 会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 创建消息对象
		Queue queue = session.createQueue("office-queue");

		// 接收端
		MessageConsumer consumer = session.createConsumer(queue);

		// 接收消息
		consumer.setMessageListener(new MessageListener() {// 消息监听器，监听mq服务器的变化
			@Override
			public void onMessage(Message message) {
				// 打印结果
				TextMessage textMessage = (TextMessage) message;
				String text = "";
				try {
					text = textMessage.getText();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("海波老师，听到了" + text + ",积极的响应了要求。。。");
			}
		});
		// 等待接收消息
		System.in.read();
	}

}
