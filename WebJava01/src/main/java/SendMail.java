
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.userDAO;
import Mail.Mail;

/**
 * Servlet implementation class SendMail
 */
@WebServlet("/sendMail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Mail mail = new Mail();
	private String url = "http://localhost:8080/WebJava01/mailConfirm_address?id=";
	userDAO dao = new userDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dao.connect();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String email = request.getParameter("mailaddress");
		String userEmail = (String) session.getAttribute("userEmail");
		String uuid = (String) session.getAttribute("uuid");
		System.out.println(uuid);
		url = url + uuid;
		System.out.println(url);
		System.out.println(email);
		System.out.println(userEmail);
		if (email.equals(userEmail)) {
			System.out.println("equal");
			mail.sendConfirmationEmail(email, url);
		} else {
			boolean emailCheckFlag = dao.EmailCheck(email);
			System.out.println(emailCheckFlag);
			if (emailCheckFlag == false) {
				dao.changeEmail(uuid, email);
				session.setAttribute("userEmail", email);
				System.out.println("メールアドレスが更新されました");
				mail.sendConfirmationEmail(email, url);
			} else if(emailCheckFlag == true){
				String errorMessage = "入力されたメールアドレスはすでに登録されています";
				request.setAttribute("errorMessage", errorMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/mailConfirm.jsp");
				dispatcher.forward(request, response);
			}
		}
		response.sendRedirect("mailConfirm.jsp");
		dao.disconnect();
	}

}
