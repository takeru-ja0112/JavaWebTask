package Mail;

public class Test {

	public static void main(String[] args) {
		Mail emailSender = new Mail();
		emailSender.sendConfirmationEmail("takeruchandesu@icloud.com",
				"http://localhost:8080/confirm?email=takeruchandesu@gmail.com&token=abc123");
	}
}
