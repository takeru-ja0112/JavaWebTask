

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
 * Servlet implementation class OkTask
 */
@WebServlet("/okTask")
public class OkTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String taskIdText = request.getParameter("id");
		int taskId = Integer.parseInt(taskIdText);
		
		try(Connection con = Database.getConnection()){
			taskDAO dao = new taskDAO(con);
			dao.okTask(taskId);
			
			HttpSession session = request.getSession();
			session.setAttribute("successMsg", "タスクステータスを変更しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserTask");
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
