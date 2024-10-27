<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>
<link rel="stylesheet" href="CSS/loginStyle.css" class="css">
<body>
	<div class="flex-container">
		<div class="margin">
			<div class="loginbase">
				<h2>パスワード変更</h2>
				<p>メールアドレス宛にパスワード変更のメールを送信します。</p>
				<form action="PasswordEmail" method="post">
					<%
					if (request.getAttribute("errorMessage") != null) {
					%>
					<p style="color: red;"><%=request.getAttribute("errorMessage")%></p>
					<%
					}
					%>
					<div>
						<input type="email" id="email" class="position" name="email"
							placeholder="メールアドレス" required>
					</div>
					<%
					if (request.getAttribute("successMessage") != null) {
					%>
					<p style="color: blue;"><%=request.getAttribute("successMessage")%></p>
					<%
					}
					%>
					<input type="submit" value="メール送信" id="loginButton">
				</form>
			</div>
		</div>
	</div>
</body>
</html>