<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	String[] writing = {"없음", "연필/샤프", "볼펜/형광펜"};
	String[] discolor = {"없음", "있음"};
	String[] pageDamage = {"없음", "있음"};
	String[] coverDamage = {"깨끗함", "깨끗하지않음"};
%>
<%
	@SuppressWarnings("unchecked") 
	Book book = (Book)request.getAttribute("book");
	HttpSession session1 = request.getSession();	
	String userId = (String) session.getAttribute("userId");
	String bookId = (String)session.getAttribute("bookId");
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
function bookRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
function wishAdd() {
	return alert("찜이 등록되엇습니다.");
}
function wishQuestion() {
	return confirm("찜 하시겠습니까?");
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
                    <b><%= categoryName[book.getCateId() - 1] %></b>
                    <br>
                    <h3><b>${book.title}</b></h3>
                    <table class="detail-info">
                        <tr>
                        
                            <td rowspan="5"><img src="${pageContext.request.contextPath}/upload/${book.image}" width="100" height="150"></td>
                            <td>저자</td>
                            <td> |&ensp;${book.author} </td>
                        </tr>
                        <tr>
                            <td>출판사</td>
                            <td> |&ensp;${book.publisher}</td>
                        </tr>
                        <tr>
                            <td>가격</td>
                            <td> |&ensp;${book.price}원</td>
                        </tr>
                        <tr>
                            <td >판매자</td>
                            <td> |&ensp;${book.userId}</td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
				<c:if test="${userId ne book.userId}">
	                               	<a class="btn btn-primary"
	                                href="<c:url value='/user/wishAdd'>
	                                	<c:param name='userId' value='<%=userId %>'/> 
	                                	<c:param name='bookId' value="${book.bookId}"/> 
	                                 </c:url>" role="button" onClick="return wishQuestion();">찜</a> 
	                                 
	                                <a class="btn btn-primary"
	                            	href="<c:url value='/chat/view'>
	                            		 <c:param name='bookId' value='${book.bookId}'/>
	                            		 <c:param name='userId' value='<%=userId%>'/>
	                            		 <c:param name='buddyId' value='${book.userId}'/>
	                            	</c:url>" role="button" onclick="window.open(this.href, '_blank', 'width=570, height=600'); return false;">채팅</a>   
	                            	
	                            	<a class="btn btn-primary" 
	                            	href="<c:url value='/transaction/view'>
						<c:param name='bookId' value="${book.bookId}"/>
						<c:param name='userId' value='<%=userId%>'/>
						<c:param name='buddyId' value="${book.userId}"/>
						</c:url>" role="button"
						onclick="window.open(this.href, '_blank', 'width=570, height=700'); return false;">
						거래 신청
					</a>                          	
                            	</c:if>
                            </td>
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
				<td><%= writing[book.getWriting()] %></td>
                        	<td><%= discolor[book.getPageDiscoloration()] %></td>
                        	<td><%= pageDamage[book.getPageDamage()] %></td>
                        	<td><%= coverDamage[book.getCoverDamage()] %></td>
                            </tr>
                        </tbody>
                    </table>

                    <!-- 책 상세 보기 -->
                    <br><br>
                    <h3><b>책 설명</b></h3>
                    <textarea name="content" cols="50" rows="8" readonly>${book.description}</textarea>
                </div>
            </div>
	    
		<c:if test="${userId eq book.getUserId()}">
			<a class="detail-btn-primary" 
			href="<c:url value='/book/update' >
			     <c:param name='bookId' value='${book.bookId}'/>
				  </c:url>">수정</a>
		<a class="detail-btn-warning" 
				href="<c:url value='/book/delete'>
				 <c:param name='bookId' value='${book.bookId}'/>
				 <c:param name='userId' value='${user.userId}' />
			      </c:url>" onclick="return bookRemove();">삭제</a>
		</c:if>
	</section>
	<br><br><br><br><br>
</body>
