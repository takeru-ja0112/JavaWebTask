<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/headerNavi.css" class="css">
<link rel="stylesheet" href="CSS/loginStyle.css" class="css">
</head>
<body>
	<%
	if (session == null || session.getAttribute("userId") == null) {
		response.sendRedirect("login.jsp");
	}
	%>
	<header>
		<div class="contentBox">
			<div class="title">
				<h1>Javaタスク管理</h1>
			</div>
			<div class="headerBox">
				<p><%= (String)session.getAttribute("userId") %></p>
				<form action="logout" method="post">
					<input type="submit" value="ログアウト" id="loginButton" class="logout">
				</form>
			</div>
		</div>
	</header>

</body>
</html>