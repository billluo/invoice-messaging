package com.rh.invoicelog;

import java.util.ArrayList;
import java.util.logging.Logger;
import javax.jms.JMSException;
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

public class IntTest01 {
	
	static Logger logger = Logger.getLogger(IntTest01.class.getName());
	
    public static void main(String[] args) throws JMSException { 
    	
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/jms-context.xml");
        JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
        ActiveMQDestination destination = (ActiveMQDestination) context.getBean("destination");      
        ErrorLogRepositoryImpl errorLogRepositoryImpl= 
        		(ErrorLogRepositoryImpl) context.getBean("errorLogRepositoryImpl");                
//        final String invoiceNbr = "2087868";
//        final ArrayList<ErrorLog> errMessage = errorLogRepositoryImpl.findByInvoiceNumber(invoiceNbr);
        final ArrayList<ErrorLog> errMessage = 
        		errorLogRepositoryImpl.findByCriteria(new InvoiceErrorCriteria());
        
        logger.info(errMessage.toString());  
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
        logger.info( " receive is done");         
    }

}
