package com.rh.invoicelog;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.rh.invoice.domain.ErrorLog;

public class ReportMail {
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	private CsvReport csvReport;
	static Logger logger = Logger.getLogger(ReportMail.class.getName());
	
	ReportMail() {}

	ReportMail(CsvReport csvReport) {
		this.csvReport = csvReport;
	}

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMailAndAttachements(List<ErrorLog> recErrorLogs) {
		//create csv file as attachment
		csvReport.createReport(recErrorLogs);
		//send mail
		sendMail("Nagesh", "this is a test mail for jms: data are from other interfaces;"
				+ " and bad file is included--in the bottom of file.");
	}
	
	public void sendMail(String dear, String content) {
	
	   MimeMessage message = mailSender.createMimeMessage();
		
	   try{
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
		helper.setFrom(simpleMailMessage.getFrom());
		helper.setTo(simpleMailMessage.getTo());
		helper.setSubject(simpleMailMessage.getSubject());
		helper.setText(String.format(
			simpleMailMessage.getText(), dear, content));
		
		//set a name for attachment
		File file = csvReport.getErrorLogFile().getReportFile();
		logger.info(file.getName());		
		helper.addAttachment(file.getName(), file);
	    }catch (MessagingException e) {
		throw new MailParseException(e);
	    }
	    mailSender.send(message);
        }
}