<%-- <% response.sendRedirect(request.getContextPath() + "/book/bookRegisterForm"); %> --%>

<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>동네북</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
	function bookCreate() {
		return alert("책이 등록되었습니다.");		
	}
	</script>
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
						&nbsp;&nbsp;👉&nbsp;판매 등록 &nbsp;
					</span>
				</div>
				<br><br>
				<div id="book" style="display: inline-block;">
					<form name="book-regi" method="POST"
						action="<c:url value='/book/register' /> "enctype="multipart/form-data">
						<c:if test="${registerFailed}">
							<font color="red"><c:out value="${exception.getMessage()}" /></font>
						</c:if>
						<h4>기본 정보 입력</h4><br>
						<table class="info" style=" margin-left:auto; margin-right:auto;">
							<tr>
								<td>제목</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="title">
								</td>
							</tr>
							<tr>
								<td>저자</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="author">
								</td>
							</tr>
							<tr>
								<td>출판사</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="publisher">
								</td>
							</tr>
							<tr>
								<td>가격</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="price">원
								</td>
							</tr>
							<tr>
								<td>카테고리</td>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<select name="cateId" style="width: 240">
										<option value="">카테고리를 선택하세요.</option>
										<%
											String[] cName = {"건강", "경제/경영", "공학", "과학", "교육", "기술",
													"만화", "문학", "미스터리/스릴러", "사회/정치", "수학", "스포츠", "어린이", "여행", "역사",
													"예술", "요리", "의학", "인문", "자기계발", "잡지", "종교", "청소년", "취미", "학술"};
											for(int i = 1; i <= 25; i++) {
												String str_i = Integer.toString(i);
										%>
												<option value="<%=str_i%>">
													<%=cName[i - 1] %>
												</option>
										<%
											}
										%>
									</select>			
								</td>
							</tr>
						</table>
						<br><br>
						<div>
							<h4>필기 흔적</h4><br>
							<input type="radio" name="writing" value="0">없음 <input
								type="radio" name="writing" value="1">연필/샤프 <input
								type="radio" name="writing" value="2">볼펜/형광펜 <br>
							<br><br>
							<h4>페이지 변색</h4><br>
							<input type="radio" name="pageDiscoloration" value="0">없음
							<input type="radio" name="pageDiscoloration" value="1">있음
							<br><br>
							<h4>페이지 훼손</h4><br>
							<input type="radio" name="pageDamage" value="0">없음 <input
								type="radio" name="pageDamage" value="1">있음 <br>
							<br><br>
							<h4>겉표지</h4><br>
							<input type="radio" name="coverDamage" value="0">깨끗함 <input
								type="radio" name="coverDamage" value="1">깨끗하지 않음 <br>
						</div>
						<br><br>
						<div>
							<h4>책 설명</h4><br>
							<textarea name="description" cols="50" rows="8"></textarea>
						</div>
						<br><br>
						<div>
							<h4>사진 등록</h4><br>
							<input type="file" name="picture" accept="image/*" size="20"/>
						</div>
						<br><br><br><br>
						<table style="width: 100%">
							<tr>
								<td align="left">
									<a href="<c:url value='/user/main' />" class="btn btn-primary" role="button" >취소</a>  
								</td>
								<td align="right">
									<input type="submit" class="btn btn-primary" value="등록" onClick="userCreate()"> &nbsp;
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
