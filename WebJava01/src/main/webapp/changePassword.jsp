<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/loginStyle.css" class="css">
</head>
<body>
	<div class="flex-container">
		<div class="margin">
			<div class="loginbase">
				<h2>パスワード変更</h2>
				<div>
					<p>以下に、新しいパスワードを</p>
					<p>入力してください</p>
				</div>
				<br>
				<% if(request.getAttribute("errorMsg") != null){ %>
					<p style = "color:red"><%= request.getAttribute("errorMsg")%></p>
				<%} %>
				<form action="changePassword" method="post">
					<div>
						<input type="password" id="email" class="position" name="pass"
							placeholder="新しいパスワード" required>
					</div>
					<div>
						<input type="password" id="email" class="position" name="checkPass"
							placeholder="確認のパスワード" required>
					</div>
					<input type="submit" value="登録" id="loginButton">
				</form>
			</div>
		</div>
	</div>
</body>
</html>