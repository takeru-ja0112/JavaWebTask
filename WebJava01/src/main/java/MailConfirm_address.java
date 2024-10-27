

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.userDAO;

/**
 * Servlet implementation class MailConfirm_address
 */
@WebServlet("/mailConfirm_address")
public class MailConfirm_address extends HttpServlet {
	private static final long serialVersionUID = 1L;
	userDAO dao = new userDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		dao.connect();
		
		String uuid = request.getParameter("id");
		System.out.println(uuid);
		dao.uuidRegist(uuid);
		
		dao.uuidDelete(uuid);
		
		response.sendRedirect("mailConfirm_address.jsp");
		dao.disconnect();
	}


}
