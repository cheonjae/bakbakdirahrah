<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	String[] writing = {"없음", "연필/샤프", "볼펜/형광펜"};
	String[] discolor = {"없음", "있음"};
	String[] pageDamage = {"없음", "있음"};
	String[] coverDamage = {"없음", "깨끗하지않음"};
%>
<%
	@SuppressWarnings("unchecked") 
	Book book = (Book)request.getAttribute("book");
	List<Transaction> sellDetail = (List<Transaction>)request.getAttribute("sellDetail");
	HttpSession session1 = request.getSession();	
	String userId = (String) session.getAttribute("userId");
	String buddyId = request.getParameter("buddyId");
	
	String bookId = request.getParameter("bookId");
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
function acceptQues() {
	return confirm("거래를 수락하시겠습니까?");		
}
</script>
</head>
<body class="detail-body">
<%@include file="/WEB-INF/navbar.jsp" %> 
 <section class="detail-section">
            <div id="detail">
                <div id="book">
                    <!-- 책 정보 -->
                    <br><br>
                    <%
if (sellDetail != null) {	
	  Iterator<Transaction> sellIter = sellDetail.iterator();
	
		Transaction transaction = (Transaction)sellIter.next();
%>
                    <b><%= categoryName[transaction.getBook().getCateId() - 1] %></b>
                    <br>
                    <h3><b><%=transaction.getBook().getTitle() %></b></h3>
                    <table class="detail-info">
                        <tr>
                            <td rowspan="5"><img src="${pageContext.request.contextPath}/upload/<%=transaction.getBook().getImage() %>" width="100" height="150"></td>
                            <td>저자</td>
                            <td> |&ensp;<%=transaction.getBook().getAuthor() %> </td>
						</tr>
						<tr>
                            <td>출판사</td>
                            <td> |&ensp;<%=transaction.getBook().getPublisher() %> </td>
						</tr>
						<tr>
                            <td>가격</td>
                            <td> |&ensp;<%=transaction.getBook().getPrice() %>원</td>
                        </tr>
                    </table>

                    <!-- 책 상태 -->
                    <br><br>
                    <h3><b>책 상태</b></h3>
                    <table class="detail-info2">
                        <thead>
                            <tr> 
                                <th><b>필기 흔적</b></th>
                                <th><b>페이지 변색</b></th>
                                <th><b>페이지 훼손</b></th>
                                <th><b>겉표지</b></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
							<td><%= writing[transaction.getBook().getWriting()] %></td>
                        	<td><%= discolor[transaction.getBook().getPageDiscoloration()] %></td>
                        	<td><%= pageDamage[transaction.getBook().getPageDamage()] %></td>
                        	<td><%= coverDamage[transaction.getBook().getCoverDamage()] %></td>
                            </tr>
                        </tbody>
                    </table>

                    <!-- 책 상세 보기 -->
                    <br><br>
                    <h3><b>책 설명</b></h3>
                    <textarea name="content" cols="50" rows="8" readonly><%=transaction.getBook().getDescription() %></textarea>
                </div>
            </div>
	</section>
	<br><br>

<%
	if(transaction.getBuyerId() != null) {
%>
	
	<table class="menu4">
		<tr>
		  <td width="190" align="center" bgcolor="#93B0FF" height="22">책제목</td>
		  <td width="200" align="center" bgcolor="#93B0FF">구매자</td>
		  <td width="200" align="center" bgcolor="#93B0FF">최종가격</td>	  
		  <td width="200" align="center" bgcolor="#93B0FF">만나는날짜</td>
		  <td width="200" align="center" bgcolor="#93B0FF">만나는장소</td>
		  <td width="200" align="center" bgcolor="#93B0FF">메모</td>
		  <td width="200" align="center" bgcolor="#93B0FF">판매자 체크</td>
		  <td width="200" align="center" bgcolor="#93B0FF">구매자 체크</td>
		  <td width="200" align="center"></td>
		</tr>
	 <c:forEach var="sellDetail" items="${sellDetail}">  
  		<tr>
		  <td width="190" align="center" bgcolor="ffffff" height="20">
		  	<%=transaction.getBook().getTitle() %>
		  </td>
		  <td width="200" bgcolor="ffffff" style="padding-left: 10">
			  ${sellDetail.buyerId}
		  </td>
		  <td width="200" bgcolor="ffffff" style="padding-left: 10">
			  ${sellDetail.lastPrice}
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
		     ${sellDetail.meetingDate} 
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
			 ${sellDetail.meetingPlace}
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
			 ${sellDetail.meetingMemo}
		  </td>
		  <td> 
			<input name="sellerCheck" type="hidden" value="${sellDetail.sellerCheck}"/>
			<input type="checkbox" name="sellerCheck2" value="판매자(${sellDetail.sellerId}) 수락"
				<c:if test="${1 eq sellDetail.sellerCheck}">
				checked
				</c:if>
				disabled
			>
			</td>
			<td>
				<input name="buyerCheck" type="hidden" value="${sellDetail.buyerCheck}"/>
				<input type="checkbox" name="buyerCheck2" value="구매자(${sellDetail.buyerId}) 수락"
					<c:if test="${1 eq sellDetail.buyerCheck}">
					checked
					</c:if> 
					disabled
				>
		</td>
		<td>
				  <a href="<c:url value='/transaction/check2' >
												<c:param name='bookId' value="<%=bookId %>"/>
												<c:param name='buddyId' value="${sellDetail.buyerId}"/>
												<c:param name='userId' value="${sellDetail.sellerId}"/>
												</c:url>"
											class="btn btn-info" role="button" onClick="return acceptQues();">수락</a>
										</td>
		</tr>
	  </c:forEach> 
<%
	}
%>	
	  </table>	  
	  <%
	  }%>
	  <br><br><br><br>	 
</body>
