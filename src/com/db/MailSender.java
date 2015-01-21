package com.db;


import java.io.File;

import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;





public class MailSender 
{

	//private static final Logger log = Logger.getLogger(EmailService.class.getName());
private JavaMailSender mailSender;

int cnt=0;

private ThreadPoolTaskExecutor  executor;






public void sendMail(final String msg, final String attendee)
{
	cnt++;
	executor.execute( new Runnable() {
		 public void run() { 
			 System.out.println("Executing task using Thread:" + Thread.currentThread().getName());
			 try {       
				 
				 SimpleMailMessage message = new SimpleMailMessage();
				 FileDataSource img1 = new FileDataSource (new File ("c:/TestApp/src/com/db/xan.jpg"));
				 FileDataSource img2 = new FileDataSource (new File ("c:/TestApp/src/com/db/happy-birthday.jpg"));
				 
				    message.setFrom("xanaduuser2014@gmail.com");
				    message.setTo(attendee);
				    message.setSubject("Invitation");
				    message.setText(msg);
				   send(message);
				 } catch (Exception e) 
				 {      
				      
					 System.out.println("Failed to send email to: " + attendee+ " reason: "+e.getMessage());     
				} 
			 System.out.println("Finished executing task using Thread:" + Thread.currentThread().getName());
			 }   
		 }); 
	
}


/**
 * Sends e-mail using Velocity template for the body and
 * the properties passed in as Velocity variables.
 *
 * @param   msg                 The e-mail message to be sent, except for the body.
 * @param   hTemplateVariables  Variables to use when processing the template.
 */

public void send(final SimpleMailMessage msg) {
    MimeMessagePreparator preparator = new MimeMessagePreparator() {
        @Override
        public void prepare(MimeMessage mimeMessage) throws Exception {
           MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
           message.setTo(msg.getTo());
           message.setFrom(msg.getFrom());
           message.setSubject(msg.getSubject());

           

           message.setText(msg.getText(), true);
        }
     };

     mailSender.send(preparator);

    System.out.println("Sent e-mail to '{}'."+ msg.getTo());
}




public ThreadPoolTaskExecutor  getExecutor() {
	return executor;
}
public void setExecutor(ThreadPoolTaskExecutor  taskExecutor) {
	this.executor = taskExecutor;
}


public JavaMailSender getMailSender() {
	return mailSender;
}


public void setMailSender(JavaMailSender mailSender) {
	this.mailSender = mailSender;
}




}
