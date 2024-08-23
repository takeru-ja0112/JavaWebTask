

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Database;
import DAO.taskDAO;

/**
 * Servlet implementation class registTask
 */
@WebServlet("/registTask")
public class RegistTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("userId");
		String sessionTask = (String) session.getAttribute("taskValue");
		
		try(Connection con = Database.getConnection() ){
			taskDAO dao = new taskDAO(con);
			dao.registTask(sessionName, sessionTask);
			
			session.removeAttribute("taskValue");
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserTask");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 

}
