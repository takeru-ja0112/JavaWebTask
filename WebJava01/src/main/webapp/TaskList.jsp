<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, DAO.Task,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/TaskList.css">
</head>
<body>
	<%
	if (session.getAttribute("successMsg") != null) {
	%>
	<div class="msgBox">
		<p><%=session.getAttribute("successMsg")%></p>
	</div>
	<%
	session.removeAttribute("successMsg");
	}
	%>
	<div class="button-container">
		<button>
			<img src="images/writeIcon.svg" alt=""><a href="newTask.jsp">新しく作成</a>
		</button>
		<div class="statusBox tab" id="statusBox">
			<button class="tab" id="allButton"  onclick="window.location.href='UserTask?status=すべて'">
				すべて
			</button>
			<button class="tab" id="unFinButton"  onclick="window.location.href='UserTask?status=未完了'">
				未完了
			</button>
			<button class="tab" id="finButton"  onclick="window.location.href='UserTask?status=完了'">
				完了
			</button>
		</div>
	</div>

	<div class="taskBase">
		<div class="taskTitle">
			<div class="taskMenu">
				<h3>タスク一覧</h3>
			</div>
		</div>
		<div>
			<ul>
				<%
				List<Task> tasks = (List<Task>) session.getAttribute("tasks");
				if (tasks == null) {
					tasks = new ArrayList<>();
				}
				for (Task task : tasks) {
				%>
				<li class="taskName">
					<div class="taskValue">
						<p><%=task.getTaskName()%></p>
						<br>
						<div class="created">
							<p>
								作成日時：<%=task.FormattedDate()%></p>
						</div>
					</div> <input type="hidden" id="taskValue" name="taskValue">
					<div class="status">
						<p><%=task.getStatus()%></p>
					</div> <details id="taskDetails">
						<summary> …</summary>
						<div class="detailsMenu">
							<form action="okTask" method="GET">
								<input type="hidden" name="id" value=<%=task.getTaskId()%>>
								<input type="submit" value="完了" onclick="return confirmOK()">
							</form>
							<form action="editTask" method="POST">
								<input type="hidden" name="id" value=<%=task.getTaskId()%>>
								<input type="submit" value="編集">
							</form>
							<form action="deleteTask" method="POST">
								<input type="hidden" name="id" value=<%=task.getTaskId()%>>
								<input type="submit" value="削除" class="deleteTaskButton"
									onclick="return confirmDeletion()">
							</form>
						</div>
					</details>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
	<div class="pageBox">
		<%
		int totalRecords = (Integer) session.getAttribute("totalRecords");
		int recordsPerPage = (Integer) session.getAttribute("recordsPerPage");
		int currentPage = (Integer) session.getAttribute("currentPage");

		int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
		%>
		<nav>
			<ul>
				<%
				for (int i = 1; i <= totalPages; i++) {
					if (currentPage == i) {
				%>
				<li><a href="UserTask?page=<%=i%>" class="activePage active"><%=i%></a></li>

				<%
				} else {
				%>
				<li><a href="UserTask?page=<%=i%>" class="activePage"><%=i%></a></li>
				<%
				}
				}
				%>
			</ul>
		</nav>
	</div>
	<script src="JS/taskList.js"></script>
</body>
</html>