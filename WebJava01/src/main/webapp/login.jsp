<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="CSS/loginStyle.css" class="css">
</head>
<body>
	<div class="flex-container">
		<div class="margin">
			<div class="loginbase">
				<h2>ログイン</h2>
				<form action="Login" method="post">
					<div>
						<input type="text" id="userId" class="position" name="userId" placeholder="ユーザID"
							required>
					</div>
					<div>
						<input type="password" id="password" class="position" name="password"
							placeholder="パスワード" required>
					</div>
					<%
					if (request.getAttribute("errorMessage") != null) {
					%>
					<p style="color: red"><%=request.getAttribute("errorMessage")%></p>
					<%
					}
					%>
					<input type="submit" value="ログイン" id="loginButton">
				</form>
			</div>
			<div class="official">
				<a href="newUser.jsp">ユーザを新規作成</a>
			</div>
			<div class="official">
				<a href="#">パスワードを忘れた方</a>
			</div>
		</div>
	</div>
</body>
</html>