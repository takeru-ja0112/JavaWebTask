
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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		userDAO dao = new userDAO();
		dao.connect();
		System.out.println("接続完了しました。");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		boolean validate = dao.validateLogin(userId, password);
		if (validate) {
			System.out.println("ログイン成功");
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			//			セッション時間
			session.setMaxInactiveInterval(600);

			RequestDispatcher dispatcher = request.getRequestDispatcher("UserTask");
			dispatcher.forward(request, response);
//			response.sendRedirect("UserTask");
		} else {
			//			確認用
			System.out.println("ログイン失敗");
			String errorMessage = "IDまたはパスワードが間違っています。";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}

		dao.disconnect();
	}
}
