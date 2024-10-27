package Mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail{
	private final String username = "takeruchandesu@gmail.com";
	private final String password = "takeru2003";
	
	public void sendConfirmationEmail(String recipientEmail , String confirmationLink) {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,"cquf zsrp idzz mahi");
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("アカウントの確認");
			message.setText("アカウント登録ありがとうございます\n\n以下のリンクをクリックしてアカウントを確認してください\n\n" + confirmationLink);
			
			Transport.send(message);
			System.out.println("メールを送信しました。");
			
		}catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendChengePasswordEmail(String recipientEmail , String confirmationLink) {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,"cquf zsrp idzz mahi");
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("パスワードの変更");
			message.setText("\n\n以下のリンクをクリックしてパスワードを変更してください\n\n" + confirmationLink);
			
			Transport.send(message);
			System.out.println("メールを送信しました。");
			
		}catch(MessagingException e) {
			e.printStackTrace();
		}
	}
}