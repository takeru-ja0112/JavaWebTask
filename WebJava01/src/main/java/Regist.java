

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.userDAO;
import security.PassHash;

/**
 * Servlet implementation class Regist
 */
@WebServlet("/Regist")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		userDAO dao = new userDAO();
		PassHash hash = new PassHash();
		dao.connect();
		HttpSession session = request.getSession();
		
		UUID uuid = UUID.randomUUID();
		
		String vali =uuid.toString();
		session.setAttribute("uuid", vali);
		
		String userId = (String)session.getAttribute("userId");
		String password = (String)session.getAttribute("password");
		String hashPassword = hash.hashPassword(password);
		
		String userEmail = (String)session.getAttribute("userEmail");
		dao.regist_tmp(vali, userId, hashPassword, userEmail);
//		System.out.println("登録が完了しました。");
		
		dao.disconnect();
		
		response.sendRedirect("mailConfirm.jsp");
	}

}
