<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="model.*" %>

<%!int count = 0; %>
<% Book book = (Book)request.getAttribute("book");
	String userId = (String) session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>동네북</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/main.css' />" type="text/css">
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
<div id="article" style="margin: 0 15% 5%; text-align: center;">
	<div id="detail" style="text-align: center;">
		<div align="left" style="margin-top: 50px;">
			<span id="page-info" style="text-align: left; font-size: 18pt; font-weight: bold;
				  color: white; background-color: rgb(147, 176, 255); margin-left: 20px;">
				&nbsp;&nbsp;👉&nbsp;위시리스트 &nbsp;
			</span>
		</div>
	</div>
</div>
<table class="tg" style="width:70%; height:auto; margin-left:auto; margin-right:auto; border-collapse : collapse;">
	<c:forEach var="book" items="${wishBookList}">
		<% if(count % 4 == 0) { %>
			 <tr>
		<% } %>
		<td class="tg-0lax" style="width:1%">
			<div>
				<a href="<c:url value='/user/wishDelete'>
				<c:param name='userId' value='<%=userId %>'/>
				<c:param name='bookId' value='${book.bookId}'/></c:url>">
				삭제
				</a>
			</div>
			<a href="<c:url value='/book/detail'><c:param name='bookId' value='${book.bookId}'/></c:url>">
		    <img src="${pageContext.request.contextPath}/upload/${book.image}" width="100" height="150">
		    <br>
		    ${book.title}
		    <br>
		    ${book.price}원</a>
		</td>
		<% if(count % 4 == 3){ %>
			</tr>
		<% }
		   count++;
		%>
	</c:forEach>
	<% count = 0; %>
</table>
</body>
</html>
