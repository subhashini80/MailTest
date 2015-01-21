package com.db;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
 
public class MailSendorTest {
	
    public static void main(String[] args) 
    {
        //Create the application context
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:com/db/appContext.xml");

    	ThreadPoolTaskExecutor pexecutor = (ThreadPoolTaskExecutor) context.getBean("pexecutor");
    	final MailTask mailTask = (MailTask) context.getBean("mailTask");
        //Send a composed mail
       // mailer.sendMail("lokeshgupta1981@gmail.com", "Test Subject", "Testing body");
 
        //Send a pre-configured mail
        //mailer.sendPreConfiguredMail("Exception occurred everywhere.. where are you ????");
        pexecutor.execute( new Runnable() {
       	 public void run() { 
       		 mailTask.processInvite("1");
       	 }
       		 }); 
       
       
        for (;;) {
    		int count = pexecutor.getActiveCount();
    		System.out.println("Active Threads : " + count);
    		try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		if (count == 0) {
    			//pexecutor.shutdown();
    			break;
    		}
    	}
        System.out.println("Finished processing");
    }
    

}
 
