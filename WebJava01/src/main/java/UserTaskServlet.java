

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Database;
import DAO.Task;
import DAO.taskDAO;
import DAO.userDAO;

/**
 * Servlet implementation class UserTaskServlet
 */
@WebServlet("/UserTask")
public class UserTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   HttpSession session = request.getSession();
		   
		   String userName = (String)session.getAttribute("userId");
		   
		   if(userName == null) {
			   response.sendRedirect("login.jsp");
			   return;
		   }
		   
		   System.out.println("データを取得します");
		   try(Connection con = Database.getConnection()){
			   taskDAO taskDAO = new taskDAO(con);
			   List<Task> tasks = taskDAO.getTaskByUserId(userName);
			   session.setAttribute("tasks", tasks);
//			   RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
//			   dispatcher.forward(request, response);
			   response.sendRedirect("welcome.jsp");
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   userDAO dao = new userDAO();
		   dao.disconnect();
		   
	}

}
