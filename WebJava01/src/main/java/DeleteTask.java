
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
 * Servlet implementation class DeleteTask
 */
@WebServlet("/deleteTask")
public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String taskIdText = request.getParameter("id");
		int taskId = Integer.parseInt(taskIdText);
		try (Connection con = Database.getConnection()) {

			taskDAO dao = new taskDAO(con);
			
			
			HttpSession session = request.getSession();
			session.setAttribute("successMsg", "タスクの削除が完了しました");
			dao.deleteTask(taskId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserTask");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
