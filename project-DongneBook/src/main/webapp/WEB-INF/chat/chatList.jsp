<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>동네북</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel=stylesheet href="<c:url value='/css/listview.css' />" type="text/css">
</head>
<body>
	<%@include file="/WEB-INF/navbar.jsp" %>
	<section>
		<h3>채팅방 목록</h3>
		<i>유저 정보: ${userId}</i>
		<table>
			<c:forEach var="buddyId" items="${buddyList}}">
				<tr>
					<td>${buddyId}</td>
				</tr>
			</c:forEach>
		</table>
	</section>
</body>
</html>
