

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
 * Servlet implementation class ChangePassword
 */
@WebServlet("/PasswordEmail")
public class PasswordEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       userDAO dao = new userDAO();
       Mail mail = new Mail();
       private String url = "http://localhost:8080/WebJava01/changePassword.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		dao.connect();
		
		String email = request.getParameter("email");
		System.out.println(email);
		
		int id = dao.EmailIdCheck(email);
		if(id == 0) {
			System.out.println("メールアドレスと一致するIDがない");
			request.setAttribute("errorMessage", "メールアドレスと一致するIDがありませんでした");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/PasswordEmail.jsp");
			dispatcher.forward(request, response);
		}
		System.out.println("抽出したIDは" + id);
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		String link = url;
		mail.sendChengePasswordEmail(email ,link);
		request.setAttribute("successMessage", "メールが送信されました");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PasswordEmail.jsp");
		dispatcher.forward(request, response);
		
		dao.disconnect();
	}

}
