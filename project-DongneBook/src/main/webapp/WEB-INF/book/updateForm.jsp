<%-- <% response.sendRedirect(request.getContextPath() + "/book/bookRegisterForm"); %> --%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%!
public String Catename(int cateId) {
	String cateN = " ";
	switch(cateId) {

	case 1:  cateN = "건강";
		break;
	case 2:
		cateN = "경제/경영";
		break;
	case 3:
		cateN = "공학";
		break;
	case 4:
		cateN = "과학";
		break;
	case 5:
		cateN = "교육";
		break;
	case 6:
		cateN = "기술";
		break;
	case 7:
		cateN = "만화";
		break;
	case 8:
		cateN = "문학";
		break;
	case 9:
		cateN = "미스터리/스릴러";
		break;
	case 10:
		cateN = "사회/정치";
		break;
	case 11:
		cateN = "수학";
		break;
	case 12:
		cateN = "스포츠";
		break;
	case 13:
		cateN = "어린이";
		break;
	case 14:
		cateN = "여행";
		break;
	case 15:
		cateN = "역사";
		break;
	case 16:
		cateN = "예술";
		break;
	case 17:
		cateN = "요리";
		break;
	case 18:
		cateN = "의학";
		break;
	case 19:
		cateN = "인문";
		break;
	case 20:
		cateN = "자기계발";
		break;
	case 21:
		cateN = "잡지";
		break;
	case 22:
		cateN = "종교";
		break;
	case 23:
		cateN = "청소년";
		break;
	case 24:
		cateN = "취미";
		break;
	case 25:
		cateN = "학술";
		break;
}
	return cateN;
}
%>
<%
	String str1 = "없음";
	String str2 = "없음";
	String str3 = "없음";
	String str4 = "깨끗함";
	
	//기존 책 데이터
	Book book = (Book)request.getAttribute("book");	
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
	<link rel=stylesheet href="<c:url value='/css/update.css' />" type="text/css">
</head>
<body style="margin: 0 auto">
<%@include file="/WEB-INF/navbar.jsp" %> 
	<section class="update-section">
			<div id="detail">
				<br><br>
				<div id="book">
					<form name="book-update" method="POST" action="<c:url value='/book/update' />">
						<input type="hidden" name="bookId" value="${book.bookId}"/>	  
						<h3><b>책 정보 수정</b></h3>
						<table class="update-info">
							<tr>
								<td>제목</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10"> |&ensp;${book.title} </td>
							</tr>
							<tr>
								<td>저자</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10"> |&ensp;${book.author} </td>
							</tr>
							<tr>
								<td>출판사</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10"> |&ensp;${book.publisher} </td>
							</tr>
							<tr>
								<td>가격</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10"> |&ensp;
									<input type="text" style="width: 240;" name="price" value='${book.price}'> 원
								</td>
							</tr>
							<tr>
								<td>카테고리</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10"> |&ensp; <%= Catename(book.getCateId()) %> </td>
							</tr>
						</table>
						<br><br>
						<div>
							<h3><b>책 상태</b></h3>
							<table class="update-info2">
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
								    </tr>
								</tbody>
							</table>
						</div>
						<br><br>
						<div>
							<h3><b>책 설명</b></h3>
							<textarea name="description" cols="50" rows="8">${book.description}</textarea>
						</div>
						<br>
						<div>
						<input type="radio" name = "sold" value="0" checked="checked">판매중
						<input type="radio" name = "sold" value="1">판매완료
						</div>
						<br>
						<table style="width: 100%">
							<tr>
								<td>
									<a href="<c:url value='/user/main' />" class="btn btn-primary"" role="button" >취소</a> 
									<input type="submit" class="btn btn-primary" value="수정" onClick="userCreate()"> &nbsp;
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</section>
	<br><br><br><br><br>
</body>
</html>
