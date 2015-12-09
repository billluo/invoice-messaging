package com.rh.invoicelog;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rh.invoice.repository.ErrorLogRepositoryImpl;

public class JpaTest {
	
    static Logger logger= Logger.getLogger(JpaTest.class.getName());
	
	public static void main(String[] args) {
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/data-access.xml");
        
        ErrorLogRepositoryImpl errorLogRepositoryImpl= 
        		(ErrorLogRepositoryImpl) context.getBean("errorLogRepositoryImpl");
        
        String msg =errorLogRepositoryImpl.findById(9753309).toString();
        
        logger.info(msg);
	}

}
