<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メッセージの累積</title>
</head>
<body>
	<form action="message" method="POST">
		メッセージ入力 <input type="text" name="message"><input type="submit">
	</form>
	<hr>
	<h1>メッセージ一覧</h1>
	<br>
	<%
	ArrayList<String> list = (ArrayList<String>) session.getAttribute("history");
		for (int i = 0; i < list.size(); i++) {
	%>
	<%=list.get(i)%><br>
	<%
	}
	%>
</body>
</html>