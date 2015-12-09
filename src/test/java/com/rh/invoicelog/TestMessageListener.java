package com.rh.invoicelog;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class TestMessageListener implements SessionAwareMessageListener<TextMessage> {
	
	   @Override
	   public void onMessage(TextMessage textMessage, Session session) throws JMSException {
	         System.out.println("Message Received "+textMessage.getText());  
	   }
}
