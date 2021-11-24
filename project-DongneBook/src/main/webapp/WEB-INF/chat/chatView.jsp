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
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>동네북</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
<script>
function chatCreate() {
	form.submit();
}
function removeCheck() {
	return confirm("채팅방에서 나가시겠습니까? 모든 내용이 삭제됩니다.");		
}
$(document).ready(function() {
	$("#div2").scrollTop($("#div2")[0].scrollHeight);
});
</script>
<link rel=stylesheet href="<c:url value='/css/chatView.css' />" type="text/css">
</head>
<body>
<br><br><br><br>
<div class="div1">
<h1><%=buddyId %></h1><br> <hr>
<div id="div2" class="div2" style="overflow: scroll;">
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
<form id="form" name="form" method="POST" action="<c:url value='/chat/create'/>">
	<br>
	&emsp;&emsp;<textarea cols="55" name="contents" onkeyup="if(window.event.keyCode==13){chatCreate()}"></textarea>
	<input type="hidden" name ="userId" value="<%= request.getParameter("userId")%>">
	<input type="hidden" name ="buddyId" value="<%= request.getParameter("buddyId")%>">
	<input type="button" name="enterEvent" class="btn btn-primary" value="전송" onClick="chatCreate()" >

			<a href="<c:url value='/chat/roomDelete'>
		<c:param name='buddyId' value="<%=buddyId%>"/>
		</c:url>" class="btn btn-Danger" role="button" style="background-color: red;" onclick="return removeCheck();">
			<span class="glyphicon glyphicon-remove" style="color: white;" aria-hidden="true"></span>
		</a>
</form>
</div>
</body>
</html>
