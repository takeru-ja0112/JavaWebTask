

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class confirmTask
 */
@WebServlet("/confirmTask")
public class ConfirmTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String taskName = request.getParameter("taskName");
		XSSservlet XssInput = new XSSservlet(taskName);
		String sanitizeValue = XssInput.getSanitizeValue();
		
		
		session.setAttribute("taskValue", sanitizeValue);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmTaskView.jsp");
		dispatcher.forward(request, response);
		
		
	}

}