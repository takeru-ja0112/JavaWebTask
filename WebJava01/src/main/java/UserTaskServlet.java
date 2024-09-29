

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   HttpSession session = request.getSession();
		   int currentPage = 1;
		   int recordsPerPage = 10;
		   
		   if(request.getParameter("page") != null) {
			   currentPage = Integer.parseInt(request.getParameter("page"));
		   }
		   String status;
		   if((String)request.getParameter("status") != null) {
			   status = (String)request.getParameter("status");
			   session.setAttribute("status", status);
		   }else if((String)session.getAttribute("status") != null){
			   status = (String)session.getAttribute("status");
		   }else {
			   status = "すべて";
		   }
		   
//		   開始オフセットを取得
		   int start = (currentPage - 1) * recordsPerPage; 
//		   ID取得
		   String userName = (String)session.getAttribute("userId");
//		   pageIdを取得
		   String pageParam = request.getParameter("pageId");
		   
		   
//		   UserIdがなければログイン画面に戻る
		   if(userName == null) {
			   response.sendRedirect("login.jsp");
			   return;
		   }
		   
		   try(Connection con = Database.getConnection()){
			   taskDAO taskDAO = new taskDAO(con);
//			   debug log
			   List<Task> tasks = taskDAO.getTaskByUserId(userName , recordsPerPage ,start,status);
			   
			   int totalRecords = taskDAO.getTotalTask(userName, status);
			   
			   session.setAttribute("totalRecords", totalRecords);
			   session.setAttribute("tasks", tasks);
			   session.setAttribute("currentPage", currentPage);
			   session.setAttribute("recordsPerPage", recordsPerPage);
			   
			   response.sendRedirect("welcome.jsp");
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   userDAO dao = new userDAO();
		   dao.disconnect();
		   
	}

}
