//package com.rh.mailtest;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;;
//public class MailerTest {
//
//	public static void main(String[] args) {
//
//        //Create the application context
//        @SuppressWarnings("resource")
//		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/mail-test.xml");
//         
//        //Get the mailer instance
//        ApplicationMailer mailer = (ApplicationMailer) context.getBean("mailService");
// 
//        //Send a composed mail
//        mailer.sendMail("bluo@rh.com", "Test Subject", "Testing body");
// 
//        //Send a pre-configured mail
//        mailer.sendPreConfiguredMail("Exception occurred everywhere.. where are you ????");
//
//	}
//
//}
