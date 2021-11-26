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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<section>
		<div id="article" style="margin: 0 15% 5%; text-align: center;">
			<div id="detail" style="text-align: center;">
				<br><br>
				<div id="book" style="display: inline-block;">
					<form name="tran-regi" method="POST"
						action="<c:url value='/transaction/update' /> "enctype="multipart/form-data">
						<c:if test="${registerFailed}">
							<font color="red"><c:out value="${exception.getMessage()}" /></font>
						</c:if>
						<h2>[ 거래 양식 ]</h2><br><br>
						<table class="info" style=" margin-left:auto; margin-right:auto;">
							<tr>
								<td><h4>최종 가격</h4></td>
							</tr>
							<tr>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="lastPrice">
								</td>
							</tr>
							<tr>
								<td><br><h4>만나는 날짜</h4></td>
							</tr>
							<tr>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="meetingDate">
								</td>
							</tr>
							<tr>
								<td><br><h4>만나는 장소</h4></td>
							</tr>
							<tr>
								<td width="250" bgcolor="ffffff" style="padding-left: 10">
									<input type="text" style="width: 240;" name="meetingPlace">
								</td>
							</tr>
							<tr>
								<td><br><h4>메모</h4></td>
							</tr>
							<tr>	
								<td>
								<textarea name="meetingMemo" cols="50" rows="8"></textarea>
								</td>
							</tr>
							<tr>
								<td><br><h4>수락 체크박스</h4></td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" name="buyerId" value="${transaction.buyderId}"> ${transaction.buyderId}
									<input type="checkbox" name="sellerId" value="${transaction.sellerId}"> ${transaction.sellerId}
								</td>
							</tr>
						</table>
						<br>
						<table style="width: 100%">
							<tr>
								<td>
									<input type="submit" class="btn btn-primary" value="등록"> &nbsp;
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