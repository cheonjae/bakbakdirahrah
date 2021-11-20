<%-- <% response.sendRedirect(request.getContextPath() + "/book/bookRegisterForm"); %> --%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%
	String str1 = "없음";
	String str2 = "없음";
	String str3 = "없음";
	String str4 = "깨끗함";
	
	//기존 책 데이터
	Book book = (Book)request.getAttribute("book");
	//업뎃 내용 넘겨줄 객체
	
%>

	
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>동네북</title>
    <!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel=stylesheet href="<c:url value='/css/detail.css' />" type="text/css">
	<script>
function userCreate() {
	form.submit();
}

function userList(targetUri) {
	form.action = targetUri;
	form.submit();
}

</script> 
</head>
<body style="margin: 0 auto">
<%@include file="/WEB-INF/navbar.jsp" %> 
	<section>
			<div id="detail">
				<div align="left"><span id="page-info">&nbsp;&nbsp;&nbsp;판매 등록 &nbsp;</span></div>
				<br><br>
				<div id="book">
					<form name="book-update" method="POST"
						action="<c:url value='/book/update' />">
						<input type="hidden" name="bookId" value="${book.bookId}"/>	  //조팔..
						<h3>기본 정보 입력</h3>
						<table class="info">
							<tr>
								<td>제목</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									${book.title}
								</td>
							</tr>
							<tr>
								<td>저자</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									${book.author}
								</td>
							</tr>
							<tr>
								<td>출판사</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									${book.publisher}
								</td>
							</tr>
							<tr>
								<td>가격</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="price" value='${book.price}'>원
								</td>
							</tr>
							<tr>
								<td>카테고리</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">							
								${book.cateId}
								</td>
							</tr>
						</table>
						<br><br>
						<div>
							<h3>필기 흔적</h3>
							<%
                            	if(book.getWriting() == 1) {
                            		str1 = "연필/샤프";
                            	} else if(book.getWriting() == 2) {
                            		str1 = "볼펜/형광펜";
                            	}
                            
                            	if(book.getPageDiscoloration() == 1) {
                            		str2 = "있음";
                            	}
                            	
                            	if(book.getPageDamage() == 1) {
                            		str3 = "있음";
                            	}
                            	
                            	if(book.getCoverDamage() == 1) {
                            		str4 = "깨끗하지않음";
                            	}
                            %>
                            	<td><%=str1%></td>
                                <td><%=str2%></td>
                                <td><%=str3%></td>
                                <td><%=str4%></td>
						</div>
						<br><br>
						<div>
							<h3>책 설명</h3>
							<textarea name="description" cols="50" rows="8">${book.description}</textarea>
						</div>
						<br><br>
						<div>
						<input type="radio" name = "sold" value="0" checked="checked">판매중
						<input type="radio" name = "sold" value="1">판매완료
						</div>
						<br><br><br><br>
						<table style="width: 100%">
							<tr>
								<td align="left">
									<input type="button" value="취소" onClick="userList('<c:url value='/book/bookListView' />')">
								</td>
								<td align="right">
									<input type="submit" value="수정" onClick="userCreate()"> &nbsp;
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
