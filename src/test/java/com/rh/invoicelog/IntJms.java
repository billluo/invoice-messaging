package com.rh.invoicelog;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class IntJms {
    public static void main(String[] args) throws JMSException {
    	 
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/jms-context.xml");
 
        JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
        ActiveMQDestination destination = (ActiveMQDestination) context.getBean("destination");
        //customization
        MessageReceiver messageReceiver = (MessageReceiver) context.getBean("messageReceiver");
 
        // sending a message
        template.convertAndSend(destination, "Hi, this test using same template to send and receive message");
 
        // receiving a message
        Object msg = template.receive(destination);
        if (msg instanceof TextMessage) {
        		System.out.println("--------------------------------------------");
        }
        
 
    }
}
