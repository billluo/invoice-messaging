package com.rh.invoicelog;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import com.rh.service.ErrorService;


@Component
public class MessageSender {
    private JmsTemplate jmsTemplate;
    private ActiveMQQueue destination;
    private ErrorService errorService;

    @Autowired
    MessageSender(final JmsTemplate jmsTemplate, 
    		final ActiveMQQueue destination, ErrorService errorService) {
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
        this.errorService = errorService;
    }

    public void send(final String message) {
        jmsTemplate.send(this.destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
    
    public void send(final ObjectMessage message) {
        jmsTemplate.send(this.destination, new MessageCreator() {
            @Override
    	        public ObjectMessage  createMessage(Session session) throws JMSException {
    	        		ObjectMessage message = session.createObjectMessage();
    	        		message.setObject((Serializable) message);       	              
    	        
    	        		return message;
   	        }
        });
    }
    /**
     * use this method to send out Object message
     */
    public void sendErrorMessage() {
    		jmsTemplate.send(destination,new MessageCreator() {
    		//use object to pass data, receiver will get object list and convert to report format
    		@Override
              public ObjectMessage  createMessage(Session session) throws JMSException {
    	    	  			ObjectMessage message = session.createObjectMessage();
    	    	  			message.setObject((Serializable) errorService.getErrorEntries());       	              
    	    	  			return message;
    			}
    		});
    }
}