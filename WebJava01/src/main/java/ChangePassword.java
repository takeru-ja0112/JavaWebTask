

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.userDAO;
import security.PassHash;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	userDAO dao = new userDAO();
	PassHash hash = new PassHash();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		dao.connect();
		request.setCharacterEncoding("UTF-8");
		String pass = request.getParameter("pass");
		String chePass = request.getParameter("checkPass");
		System.out.println(pass + ":" + chePass);
		
		if(!pass.equals(chePass)){
			System.out.println("一致していません");
			request.setAttribute("errorMsg", "パスワードが一致していません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/changePassword.jsp");
			dispatcher.forward(request, response);
		}
		XSSservlet xssPass = new XSSservlet(pass);
		String formPass = xssPass.getSanitizeValue();
		if(formPass.length() < 8) {
			request.setAttribute("errorMsg", "パスワードは8文字以上にしてください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/changePassword.jsp");
			dispatcher.forward(request, response);
		}
		String hashPass = hash.hashPassword(formPass);
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("id");
		dao.ChengePassword(id, hashPass);
		dao.disconnect();
		response.sendRedirect("successPassword.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
