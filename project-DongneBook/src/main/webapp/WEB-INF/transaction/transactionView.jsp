<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	@SuppressWarnings("unchecked") 
	Transaction transaction = (Transaction)request.getAttribute("transaction");
	HttpSession session1 = request.getSession();	
	String userId = (String) session.getAttribute("userId");
	String buddyId = (String) session.getAttribute("buddyId");
	
	int bookId = Integer.parseInt(request.getParameter("bookId"));
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>동네북</title>
    <!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel=stylesheet href="<c:url value='/css/detail.css' />" type="text/css">
	<script>
  function chat_popup() {
	  window.open("<%= request.getContextPath() %>/chat/chatView.jsp", "chat", "width=640" "height=400");
  }
  </script>
</head>
<body>
	<section>
		<div id="article" style="margin: 0 15% 5%; text-align: center;">
			<div id="detail" style="text-align: center;">
				<br><br>
				<div id="transaction" style="display: inline-block;">
						<h2>[ 거래 양식 ]</h2><br>
						<table class="info" style=" margin-left:auto; margin-right:auto;">
							<tr>
								<td>
									판매자: <input type="text" style="width: 240;" name="sellerId" value="${transaction.sellerId}" readonly>
								</td>
							</tr>
							<tr>
								<td>
									구매자: <input type="text" style="width: 240;" name="buyerId" value="${transaction.buyerId}" readonly>
								</td>
							</tr>
							<tr>
								<td><h4>최종 가격</h4></td>
							</tr>
							<tr>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="lastPrice" value="${transaction.lastPrice}" readonly>
								</td>
							</tr>
							<tr>
								<td><br><h4>만나는 날짜</h4></td>
							</tr>
							<tr>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="meetingDate" value="${transaction.meetingDate}" readonly>
								</td>
							</tr>
							<tr>
								<td><br><h4>만나는 장소</h4></td>
							</tr>
							<tr>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="meetingPlace" value="${transaction.meetingDate}" readonly>
								</td>
							</tr>
							<tr>
								<td><br><h4>메모</h4></td>
							</tr>
							<tr>	
								<td>
								<textarea name="meetingMemo" cols="50" rows="8" readonly>${transaction.meetingMemo}</textarea>
								</td>
							</tr>
							<tr>
								<td><br><h4>수락 현황</h4></td>
							</tr>
							<tr>
								<td> 판매자: 
									<input type="checkbox" name="sellerCheck" value="판매자(${transaction.sellerId}) 수락"
										<c:if test="${1 eq transaction.sellerCheck}">
											checked
										</c:if>
										disabled
									>
									&nbsp;구매자: 
									<input name="sellerCheck" type="hidden" value="${transaction.sellerCheck}"/>
									<input type="checkbox" name="buyerCheck" value="구매자(${transaction.buyerId}) 수락"
										<c:if test="${1 eq transaction.buyerCheck}">
											checked
										</c:if> 
										disabled
									>
									<input name="buyerCheck" type="hidden" value="${transaction.buyerCheck}"/>
								</td>
							</tr>
						</table>
						<br>
						<table style="width: 100%">
							<tr>
								<td>
									<c:choose>
										<c:when test="${empty transaction}">
											<a href="<c:url value='/transaction/create' />" class="btn btn-info" role="button" >등록</a>&nbsp;
										</c:when>
										<c:otherwise>
											<a class="btn btn-info" href="<c:url value='/transaction/update' >
												<c:param name='bookId' value="${transaction.bookId}"/>
												<c:param name='sellerId' value="${transaction.sellerId}"/>
												<c:param name='buyerId' value="${transaction.buyerId}"/>
												</c:url>"
												role="button">
												수정
											</a>&nbsp;
											<a href="<c:url value='/transaction/checkUpdate' />" class="btn btn-info" role="button" >수락</a>
										</c:otherwise>
									</c:choose> 
								</td>
							</tr>
						</table>
				</div>
			</div>
		</div>
	</section>
</body>
