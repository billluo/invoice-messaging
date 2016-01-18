package com.rh.invoice.controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.rh.invoicelog.JmsInt01;
import com.rh.invoicelog.MessageSender;



public class ValidationScheduler {
	Timer timer;
	static Logger logger = Logger.getLogger(JmsInt01.class.getName());
	
	public ValidationScheduler(int minutes) {
		
		timer = new Timer();
		//for test
		timer.schedule(new ValidationTask(), minutes*1000/60);
//		timer.schedule(new ValidationTask(), minutes*1000*60);
	}
	
	class ValidationTask extends TimerTask{
		
		public void run() {
			System.out.format("Vendor file validation is running ...");
	        @SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/jms-context.xml");
	 
	        JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
	        ActiveMQDestination destination = (ActiveMQDestination) context.getBean("destination");        
	        //send message
	        MessageSender messageSender = (MessageSender) context.getBean("messageSender");   
	        messageSender.sendErrorMessage();

	        // receiving a message
	        try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        template.receive(destination);
	        logger.info( " receive is done");

			timer.cancel();		
			System.exit(0);
		}
	}
	
	public static void main(String args[]) {
		new ValidationScheduler(60);
		System.out.format("Task is scheduled%n");
	}

}
