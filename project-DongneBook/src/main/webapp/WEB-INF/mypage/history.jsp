<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<title>동네북 - 거래내역</title>
    <!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel=stylesheet href="<c:url value='/css/history.css' />" type="text/css">
<style>
ul {
    list-style: none;
 }
</style>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %> 
<br>
<div id="menu1">
 <ul>
  <li><a href="<c:url value='/transaction/sell' />">판매 내역</a></li>
  <li><a href="<c:url value='/transaction/buy' />">구매 내역</a></li>
 </ul>
</div>
<br>
</body>
</html>
