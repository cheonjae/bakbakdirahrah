<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%!int count = 0;%>
<%
	List<Transaction> buyList = (List<Transaction>)request.getAttribute("buyList"); 
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

<table id="menu4" style="padding:10px;">
	<%
if (buyList != null) {	
	  Iterator<Transaction> buyIter = buyList.iterator();
	  Transaction transaction = null;
	  
	  	while(buyIter.hasNext()) {
			transaction = (Transaction)buyIter.next();
	  	}
%>
		<tr>
		  <td width="190" align="center" bgcolor="#82C8E6" height="22">책제목</td>
		  <td width="200" align="center" bgcolor="#82C8E6">판매자</td>
		  <td width="200" align="center" bgcolor="#82C8E6">최종가격</td>	  
		  <td width="200" align="center" bgcolor="#82C8E6">만나는날짜</td>
		  <td width="200" align="center" bgcolor="#82C8E6">만나는장소</td>
		  <td width="200" align="center" bgcolor="#82C8E6">메모</td>
		</tr>
	<c:forEach var="buy" items="${buyList}">
  		<tr>
		  <td width="190" align="center" bgcolor="ffffff" height="20">
		  	 ${buy.book.title}
		  </td>
		  <td width="200" bgcolor="ffffff" style="padding-left: 10">
			  ${buy.sellerId}
		  </td>
		  <td width="200" bgcolor="ffffff" style="padding-left: 10">
			  ${buy.lastPrice}
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
		     ${buy.meetingDate} 
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
			 ${buy.meetingPlace}
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
			 ${buy.meetingMemo}
		  </td>
		</tr>
	  </c:forEach> 
<%
	}
%>	
	  </table>
</body>
</html>
