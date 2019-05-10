<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%session.setAttribute("sessionUser", null); %>
<!DOCTYPE html>
<html op="news">
<head>
<meta name="referrer" content="origin">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="shortcut icon" href="favicon.ico">
<title>Hacker News</title>
</head>
<body>
	<%@include file="news.jsp" %>
</body>
</html>