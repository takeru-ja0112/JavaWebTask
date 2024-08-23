<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/confirmBase.css" class="CSS">
<link rel="stylesheet" href="CSS/newTaskStyle.css" class="CSS">
</head>
<body>
	<%@include file="headerNavi.jsp"%>
	<div class="flex-container">
		<div class="margin">
			<div class="taskForm">
				<form action="registTask" method="POST">
					<h1>登録内容</h1>
					<div class="taskConfirmBox">
						<p><%=session.getAttribute("taskValue")%></p>
					</div>
					<div class="display-flexBox">
						<div class="backButton">
							<a href="newTask.jsp">入力に戻る</a>
						</div>
						<input type="submit" Value="登録">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>