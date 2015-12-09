package com.rh.invoicelog;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rh.invoice.domain.ErrorLog;

/**
 * @author bluo
 *
 */

@Component
public class MessageReceiver implements MessageListener {
	
	private ReportMail reportMail;
	/**
	 * when receiving message as objects list, pack list as csv file as attachment
	 * and send out
	 */	
	@Autowired
	private MessageReceiver(ReportMail reportMail){
		this.reportMail = reportMail;
	}
	
	static Logger receiveLog = Logger.getLogger(MessageReceiver.class.getName());
	
	@Override  
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {   	   	  
			try {			
				receiveLog.info(((ObjectMessage) message).getObject().toString());			
				@SuppressWarnings("unchecked")
				List<ErrorLog> recErrorLogs = 
					(ArrayList<ErrorLog>) ((ObjectMessage) message).getObject();	
				//pass error entries to create report and send out email
				reportMail.sendMailAndAttachements(recErrorLogs);
			} catch (JMSException e) {			
				e.printStackTrace();		
			} 
		}
	}
}