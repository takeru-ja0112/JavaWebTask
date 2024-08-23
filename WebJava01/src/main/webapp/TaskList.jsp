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
		<form action="deleteTask" method="action">
			<div id="deleteButton">
				<img src="images/trashIcon.svg" alt=""> <input type="submit"
					value="タスクを削除" class="deleteText">
			</div>
		</form>
	</div>

	<table border="1">
		<tr>
			<th>タスク名</th>
			<th>ステータス</th>
		</tr>
		<%
		List<Task> tasks = (List<Task>) session.getAttribute("tasks");
		if (tasks == null) {
			tasks = new ArrayList<>();
		}
		for (Task task : tasks) {
		%>
		<tr>
			<td><%=task.getTaskName()%> <br> <br> 作成日時：<%=task.FormattedDate()%></td>
			<td class="status"><%=task.getStatus()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<script>
    document.addEventListener('DOMContentLoaded', () => {
        const cells = document.querySelectorAll('td');
        cells.forEach(cell => {
            cell.addEventListener('click', () => {
                cell.classList.toggle('highlighted');
            });
        });
    });
	</script>
</body>
</html>