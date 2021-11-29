<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="model.Chat" %>
<%@page import="model.service.ChatManager" %>
<%@page import="model.dao.ChatDAO" %>
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
		<div id="article" style="margin: 0 15% 5%; text-align: center;">
			<div id="detail" style="text-align: center;">
				<div align="left" style="margin-top: 50px;">
					<span id="page-info" style="text-align: left; font-size: 18pt; font-weight: bold;
												color: white; background-color: rgb(147, 176, 255); margin-left: 20px;">
						&nbsp;&nbsp;👉&nbsp;채팅방 목록 &nbsp;
					</span>
				</div>
				<br><br>
				<div id="book" style="display: inline-block;">
					<c:forEach var="buddyId" items="${buddyList}">
						<c:if test="${buddyId != userId}">
							<div style="text-align: center; display:table-cell; vertical-align: middle; background: linear-gradient(to right, rgba(147, 176, 255, 0.5), rgba(46, 191, 145, 0.5)); height: 50px; width: 300px; border-radius: 15px;">
								<a href="<c:url value='/chat/view'><c:param name='buddyId' value="${buddyId}"/><c:param name='userId' value="${userId}"/></c:url>" onclick="window.open(this.href, '_blank', 'width=570, height=600'); return false;">
									${buddyId}
								</a>
							</div>
							<br>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
