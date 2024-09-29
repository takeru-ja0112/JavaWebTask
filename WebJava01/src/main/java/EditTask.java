

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
 * Servlet implementation class EdhitTask
 */
@WebServlet("/editTask")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String taskIdText = request.getParameter("id");
		int taskId = Integer.parseInt(taskIdText);
		
		HttpSession session = request.getSession();
		try(Connection con = Database.getConnection()){
			taskDAO dao = new taskDAO(con);
			String taskValueEdit = dao.getTaskValues(taskId);
			
			session.setAttribute("taskValueEdit", taskValueEdit);
			session.setAttribute("taskId", taskId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/editTask.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
