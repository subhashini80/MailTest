package com.db;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.velocity.VelocityEngineUtils;





public class MailTask 
{



private  VelocityEngine velocityEngine = null;

private MailSender mailer;






public void processInvite(final String invite)
{
	
			 long startTime = System.currentTimeMillis();
			 long endTime, timeTaken;
			 System.out.println("Executing task using Thread:" + Thread.currentThread().getName());
			// boolean flag = inviteDAO.checkIfProcessing(invite.getEvent_id());
			 boolean flag = checkIfProcessing(invite);
			 if(!flag)
			 {
				 startTime = System.currentTimeMillis();
				 process(invite);
				 endTime = System.currentTimeMillis();
				 System.out.println("Time Taken for processing an invite "+ (endTime-startTime)/1000 + " seconds");
			 }
			 System.out.println("Finished executing task using Thread:" + Thread.currentThread().getName());
			  

}

private boolean checkIfProcessing(String invite) {
	// TODO Auto-generated method stub
	return false;
}


private void process(String invite) {
	//int id= Integer.parseinvite;
	//inviteDAO.markProcess(id);
	List<String> attendeesLst = new ArrayList();
	attendeesLst.add("xanaduuser2014@gmail.com");
	attendeesLst.add("subhashini.80@gmail.com");
	for(String attendee:attendeesLst)
	{
		 Map<String, Object> props = new HashMap<String, Object>();
		 props.put("firstName", "test");
		 props.put("lastName", "test");
		 
		 props.put("eventName", "Event name");
	     props.put("streetName", "Street name");
	     props.put("cityName", "City name");
	     props.put("zipCode", "zipcode");
		String msg = getTemplate(props);
		
		mailer.sendMail(msg, attendee);
	}
	
	

	//inviteDAO.deleteReader(id);
}



private String getTemplate(Map<String, Object> props)
{
	 String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/emailBody.vm", props);
	 return body;
}











public VelocityEngine getVelocityEngine() {
	return velocityEngine;
}


public void setVelocityEngine(VelocityEngine velocityEngine) {
	this.velocityEngine = velocityEngine;
}



public MailSender getMailer() {
	return mailer;
}

public void setMailer(MailSender mailer) {
	this.mailer = mailer;
}

}
