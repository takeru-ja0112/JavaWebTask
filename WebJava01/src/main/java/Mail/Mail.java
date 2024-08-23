package Mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	public static void main(String[] args) {
		String host = "smtp.gmail.com"; 
		final String user = "takeruchandesu@gmail.com";
		final String password = "takeru2003";
		
		String to = "takeruchandesu@gmail.com";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user,password);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("javaMail Test");
			message.setText("This is a test email sent from Java!");
			
			Transport.send(message);
			
			System.out.println("メールが送信されました。");
			
	}catch(MessagingException e) {
		e.printStackTrace();
	}
	}
}
