package br.com.puc.tcc.util;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


public class EnviaEmailGmail {

	  public static void send(String to, String subject, String message) {
		 
		  try {
			    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
				
				mailSender.setHost("smtp.gmail.com");
				mailSender.setPort(587);
				mailSender.setUsername("thiago.sistema.gestao.ambiental@gmail.com");
				mailSender.setPassword("sistemagestao123");
				
				Properties props = new Properties();
				props.put("mail.transport.protocol", "smtp");
				props.put("mail.smtp.auth", true);
				props.put("mail.smtp.starttls.enable", true);
				props.put("mail.smtp.connectiontimeout", 10000);
				
				mailSender.setJavaMailProperties(props);
				
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				
				simpleMailMessage.setFrom("Sistema Gestao Ambiental <thiago.sistema.gestao.ambiental@gmail.com>");
				simpleMailMessage.setTo(to);
				simpleMailMessage.setSubject(subject);
				simpleMailMessage.setText(message);
				
				mailSender.send(simpleMailMessage);
			  
		      System.out.println("Email sent!");
		    } catch (Exception ex) {
		      System.out.println("The email was not sent. Error message: " 
		          + ex.getMessage());
		    }
	  }
	  
}