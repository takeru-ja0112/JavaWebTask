<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file = "headerNavi.jsp" %>
	<h1>ようこそ <%= (String)session.getAttribute("userId") %></h1>
	<%@include file = "TaskList.jsp" %>
</body>
</html>