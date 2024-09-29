

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConfirmEditTask
 */
@WebServlet("/confirmEditTask")
public class ConfirmEditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String taskValueEdit = request.getParameter("taskNameEdit");
		XSSservlet xss = new XSSservlet(taskValueEdit);
		String sanitizeTaskValueEdit = xss.getSanitizeValue();
		int maxValue = 100;
		
		
		if(sanitizeTaskValueEdit.length() >  maxValue) {
			request.setAttribute("errorValue", "文字数は100文字以内にしてください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("editTask.jsp");
			session.setAttribute("taskValue", sanitizeTaskValueEdit);
			dispatcher.forward(request, response);
		}
		session.setAttribute("sanitizeTaskValueEdit", sanitizeTaskValueEdit);
		RequestDispatcher dispatcher = request.getRequestDispatcher("confirmEditTaskView.jsp");
		dispatcher.forward(request, response);
	}

}
