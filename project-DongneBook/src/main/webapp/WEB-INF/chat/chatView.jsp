<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="model.*" %>
<%
HttpSession session1 = request.getSession();	
String userId = (String) session.getAttribute("userId");

User user = (User)request.getAttribute("user");
String buddyId = (String)request.getAttribute("buddyId");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="refresh" CONTENT="5">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>동네북</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/chatView.css' />" type="text/css">
</head>
<body>
<br><br><br><br>
<div class="div1">
<h1><%=buddyId %></h1><br> <hr>
<div class="div2" style="overflow: scroll;">
<table>
<c:forEach var="chat" items="${chatList}">
  <tr>
 <c:if test="${chat.senderId eq user.userId}">
	<td>&emsp;</td>
	<td>&emsp;</td>
	<td style="text-align:right;">${chat.contents}</td>
 </c:if>
 <c:if test="${chat.receiverId eq user.userId}">
	<td style="text-align:left;">${chat.contents}</td>
	<td>&emsp;</td>
	<td>&emsp;</td>
 </c:if>
   </tr>
</c:forEach>
</table>
</div>
<form id="sendMessage">
	&emsp;&emsp;<textarea cols="55"></textarea>
	<input type="submit" value="전송">
</form>
</div>
</body>
</html>
