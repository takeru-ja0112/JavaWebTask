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
	<div class="button-container">
		<button>
			<img src="images/writeIcon.svg" alt=""><a href="newTask.jsp">新しく作成</a>
		</button>
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
					</div> <details>
						<summary> …</summary>
						<form action="delete" method="POST">
							<input type="hidden" name="id" value=<%=task.getTaskId()%>>
							<input type="submit" value="削除">
						</form>
						<form action="edit" method="POST">
							<input type="hidden" name="id" value=<%=task.getTaskId()%>>
							<input type="submit" value="編集">
						</form>
					</details>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</div>

</body>
</html>