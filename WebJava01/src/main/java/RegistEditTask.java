

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
 * Servlet implementation class RegistEditTask
 */
@WebServlet("/registEditTask")
public class RegistEditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String taskValue = (String) session.getAttribute("sanitizeTaskValueEdit");
		int taskId = (int) session.getAttribute("taskId");
		try(Connection con = Database.getConnection()){
			taskDAO dao = new taskDAO(con);
			
			dao.editTask(taskValue, taskId);
			
			session.removeAttribute("taskId");
			session.removeAttribute("sanitizeTaskValueEdit");
			session.removeAttribute("taskValue");
			session.setAttribute("successMsg", "タスクの編集が完了しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserTask");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
