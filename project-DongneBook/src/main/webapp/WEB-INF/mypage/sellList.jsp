<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%int count = 0;%>
<%
	List<Transaction> sellList = (List<Transaction>)request.getAttribute("sellList"); 
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
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
<table id="menu3">
<c:forEach var="sell" items="${sellList}">
<% if(count % 4 == 0) { %>
  <tr>
<% } %>
    <td>
    
	<a href="<c:url value='/mypage/sell'> 
		<c:param name='bookId' value='${sell.bookId}'/>
		</c:url>">
    <img src="${pageContext.request.contextPath}/upload/${sell.image}" width="100" height="150">
    <br>
    ${sell.title} <c:if test="${sell.sold eq 1}">(판매완료)</c:if>
    <br>
    ${sell.price}원</a>
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