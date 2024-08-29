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
					</div> <details id="taskDetails">
						<summary> …</summary>
						<div class="detailsMenu">
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
	<script>
		function confirmDeletion(){
				return confirm("本当に削除しますか？");
			}

		
		const detailsElements = document.querySelectorAll('details');

		document.addEventListener('click' , function(event){
			detailsElements.forEach(details => {
			if(!details.contains(event.target)){
				details.removeAttribute('open');
			}
		});
	});

	detailsElements.forEach(details => {
			details.addEventListener('click' , function(event){
				event.stopPropagation();
			});
				
		
		});
		
	</script>
</body>
</html>