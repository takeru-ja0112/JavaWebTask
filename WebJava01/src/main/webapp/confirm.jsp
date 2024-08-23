<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/loginStyle.css" class="css">
<link rel= "stylesheet" href="CSS/confirm.css" class="css">
</head>
<body>
	<div class="flex-container">
		<div class="margin">
			<div class="loginbase">
				<h2>入力確認</h2>
				<form action="Regist" method="post">
					<div class="list"><p><%=session.getAttribute("userId")%></p></div>
					<div class="list"><p><%=session.getAttribute("userEmail")%></p></div>
					<div class="list"><p><%=session.getAttribute("password")%></p></div>
					<input type="submit" value="登録" id="loginButton">
				</form>
			</div>
			<div class="official">
				<a href="newUser.jsp">入力画面に戻る</a>
			</div>
		</div>
</body>
</html>