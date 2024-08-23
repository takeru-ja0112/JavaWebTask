

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.userDAO;

/**
 * Servlet implementation class Confirmation
 */
@WebServlet("/confirmation")
public class Confirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String userEmail = request.getParameter("email");
		String password = request.getParameter("password");
		userDAO dao = new userDAO();
		
		XSSservlet xssUserId = new XSSservlet(userId);
		XSSservlet xssUserEmail = new XSSservlet(userEmail);
		XSSservlet xssPassword = new XSSservlet(password);
		
		String sanitizeUserId = xssUserId.getSanitizeValue();
		String sanitizeUserEmail = xssUserEmail.getSanitizeValue();
		String sanitizePassword = xssPassword.getSanitizeValue();
		
		dao.connect();
		boolean checkFlag = dao.userCheck(userId);
		
		if(password.length() >= 8 && checkFlag == false ) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", sanitizeUserId);
			session.setAttribute("userEmail", sanitizeUserEmail);
			session.setAttribute("password", sanitizePassword);
			session.setMaxInactiveInterval(300);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/confirm.jsp");
			dispatcher.forward(request, response);
		}else if(password.length() < 8){
			String errorMessage = "パスワードは8文字以上にしてください";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/newUser.jsp");
			dispatcher.forward(request, response);
		}else if(checkFlag == true){
			String errorMessage = "その名前はすでに登録されています";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/newUser.jsp");
			dispatcher.forward(request, response);
		}
		dao.disconnect();
		
	}

}
