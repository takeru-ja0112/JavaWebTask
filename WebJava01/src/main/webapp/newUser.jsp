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
				<h2>ユーザ新規作成</h2>
				<%
				if (request.getAttribute("errorMessage") != null) {
				%>
				<p style="color: red"><%=request.getAttribute("errorMessage")%></p>
				<%
				}
				%>
				<form action="confirmation" method="post">
					<div>
						<input type="text" id="userId" class="position" name="userId" placeholder="ユーザID"
							required>
					</div>
					<div>
						<input type="email" id="email" class="position"  name="email" placeholder="メールアドレス"
							required>
					</div>
					<div>
						<input type="password" id="password" class="position" name="password"
							placeholder="パスワード" required>
						<p>パスワードは8文字以上</p>
					</div>
					<input type="submit" value="確認" id="loginButton">
				</form>
			</div>
		</div>
	</div>
</body>
</html>