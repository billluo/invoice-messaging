package com.rh.invoicelog;

import java.util.ArrayList;
import java.util.logging.Logger;
import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.rh.invoice.domain.ErrorLog;
import com.rh.invoice.repository.ErrorLogRepositoryImpl;

public class JmsInt02 {
	
	static Logger logger = Logger.getLogger(JmsInt01.class.getName());
	
    public static void main(String[] args) throws JMSException, InterruptedException { 
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/jms-context.xml");
 
        JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
        ActiveMQDestination destination = (ActiveMQDestination) context.getBean("destination");        
        //send message
        MessageSender messageSender = (MessageSender) context.getBean("messageSender");   
        messageSender.sendErrorMessage();

        // receiving a message
        Thread.sleep(10000);
        Object msg1 = template.receive(destination);
        logger.info( " receive is done");                
    }
}   
//  ErrorLogRepositoryImpl errorLogRepositoryImpl= 
//	(ErrorLogRepositoryImpl) context.getBean("errorLogRepositoryImpl");        
//ErrorLogFile errorLogFile =(ErrorLogFile) context.getBean("errorLogFile");
//InvoiceBadFile invoiceBadFile = (InvoiceBadFile) context.getBean("invoiceBadFile");
//CsvReport csvReport =(CsvReport) context.getBean("csvReport");
//ReportMail testReportMail = (ReportMail) context.getBean("reportMail");
//  final String invoiceNbr = "2087868";
//  final ArrayList<ErrorLog> errMessage = 
//  		errorLogRepositoryImpl.findByCriteria(new InvoiceErrorCriteria());
//  
//  logger.info(errMessage.toString());  

