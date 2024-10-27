<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/loginStyle.css" class="css">
<link rel="stylesheet" href="CSS/mailConfirm.css" class="css">
</head>
<body>
	<div class="flex-container">
		<div class="margin">
			<div class="loginbase" style="min-width:350px">
				<h2>本人確認のため、メールアドレスの認証をお願いします。</h2>
				<p>入力されているメールアドレス宛にメールアドレスの認証方法をお送りしますので、間違っている場合は修正した上で「認証メールを送る」ボタンを教えてください</p>
				<form action="sendMail" method="POST">
					<% if(request.getAttribute("errorMessage") != null){ %>
						<p style="color:red"><%= request.getAttribute("errorMessage") %></p>
					<%} %>
					<input id="mailaddressBox" type="email" name="mailaddress" value=<%= session.getAttribute("userEmail") %>>
					<input type="submit" id="loginButton" value="認証メールを送る">
				</form>
			</div>
		</div>
	</div>
</body>
</html>