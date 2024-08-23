<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/loginStyle.css" class="CSS">
<link rel="stylesheet" href="CSS/newTaskStyle.css" class="CSS">
</head>
<body>
	<%@include file="headerNavi.jsp"%>
	<div class="flex-container">
		<div class="margin">
			<div class="taskForm">
				<h1>新しいタスクの登録</h1>
				<form action="confirmTask" method="POST">
					<textarea class="taskBox" id="taskName" name="taskName"><% if(session.getAttribute("taskValue") != null)
					{ %><%= session.getAttribute("taskValue") %><%
					}
					%></textarea><br><br>
					<div class="display-flexBox">
						<div class="backButton">
							<a href="welcome.jsp">一覧に戻る</a>
						</div>
						<input type="submit" value="確認画面">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>