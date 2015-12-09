package com.rh.invoicelog;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.rh.invoice.domain.ErrorLog;
import com.rh.invoice.repository.ErrorLogRepositoryImpl;

public class JmsInt01 {
	
	static Logger logger = Logger.getLogger(JmsInt01.class.getName());
	
    public static void main(String[] args) throws JMSException { 
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/jms-context.xml");
 
        JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
        ActiveMQDestination destination = (ActiveMQDestination) context.getBean("destination");
        
        ErrorLogRepositoryImpl errorLogRepositoryImpl= 
        		(ErrorLogRepositoryImpl) context.getBean("errorLogRepositoryImpl");
        
        final String invoiceNbr = "2087868";
        final ArrayList<ErrorLog> errMessage = errorLogRepositoryImpl.findByInvoiceNumber(invoiceNbr);
        //send list of error logs to receiver
        template.send(destination,new MessageCreator() {
			//use object to pass data, receiver will get object list and convert to report format
			@Override
	          public ObjectMessage  createMessage(Session session) throws JMSException {
  	    	  			ObjectMessage message = session.createObjectMessage();
  	    	  			message.setObject(errMessage);       	              
  	    	  			return message;
				}
		});
 
        // receiving a message
        Object msg1 = template.receive(destination);
        logger.info("receive is done");        
    }
}

//final ErrorLog errMessage =errorLogRepositoryImpl.findById(9753309);
//String errMessage =errorLogRepositoryImpl.findById(9753309).toString();

// sending a message
//template.convertAndSend(destination, errMessage);
//if (msg1 instanceof TextMessage) {
//try {
////		TextMessage textMessage = (TextMessage) msg;
////		messageReceiver.onMessage(textMessage);
//		logger.info("--------------------------------------------");
//		logger.info(((TextMessage) msg1).getText());
//		logger.info("--------------------------------------------");
//} catch (JMSException e) {
//  System.out.println(e);
//}
//}
