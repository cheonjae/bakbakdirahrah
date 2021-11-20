<%-- <% response.sendRedirect(request.getContextPath() + "/book/bookRegisterForm"); %> --%>

<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>동네북</title>
	<link rel=stylesheet href="<c:url value='/css/bookRegisterForm.css' />" type="text/css">
</head>
<body style="margin: 0 auto">
	<header>
		<title></title>
		<div align=center>
			<form name="frm1">
				<table class="headtable">
					<!--보더 삭제-->
					<!--총 11칸-->
					<tr>
						<td rowspan="2" colspan="2"><a id="logo">동네북</a> <!--로고 이미지-->
						</td>
						<td colspan="6">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
						<td>로그인 <!--@@님 반갑습니다!-->
						</td>
						<td>회원가입 <!--마이페이지-->
						</td>
						<td>마이페이지 <!--로그아웃-->
						</td>
					</tr>
					<tr>
						<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
						<td colspan="5">
							<form name="search" method="get" action="a.jsp"
								onsubmit="return check()">
								<section id="fixed-bar-search">
									<div class="search-input-wrap">
										<span class="sr-only">검색</span> <input type="text"
											name="header-search-input" id="header-search-input"
											class="fixed-search-input" placeholder="책 이름을 검색해보세요!">
										<button id="header-search-button">
											<img class="fixed-search-icon" alt="Search"
												src="https://d1unjqcospf8gs.cloudfront.net/assets/home/base/header/search-icon-7008edd4f9aaa32188f55e65258f1c1905d7a9d1a3ca2a07ae809b5535380f14.svg">
										</button>
									</div>
								</section>
							</form>
						</td>
						<td colspan="3">&emsp;&emsp;&emsp;&emsp;&emsp;</td>
					</tr>
				</table>
				<table class="headtable">
					<!--보더 삭제-->
					<!--총 11칸-->
					<tr>
						<td style="text-align: left;">
							<button class="newlist-button">
								<span class="button-text">실시간 판매 목록</span>
							</button>
						</td>
						<td colspan="9">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
						<td style="text-align: right;">
							<button class="sales-button">
								<span class="button-text">판매 등록</span>
							</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</header>

	<!-- 카테고리: 예스24 베스트셀러 참고 -->
	<section>
		<div id="article">
			<aside id="left">
				<h5 style="margin-left: 50px;">카테고리</h5>
				<ul>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					<li><a href="./computer.html" target="iframe1">경제/경영</a></li>
					
				</ul>
			</aside>

			<div id="detail">
				<div align="left"><span id="page-info">&nbsp;&nbsp;👉&nbsp;판매 등록 &nbsp;</span></div>
				<br><br>
				<div id="book">
					<form name="book-regi" method="POST"
						action="<c:url value='/book/register' />">
						<c:if test="${registerFailed}">
							<font color="red"><c:out value="${exception.getMessage()}" /></font>
						</c:if>
						<h3>기본 정보 입력</h3>
						<table class="info">
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
										<%-- <c:forEach var="cate" items="${cateList}}">
											<option value="${cate.cateId}" ${cate.cateId == user.cateId ? 'selected="selected"' : '' }>${cate.cateName}</option>
										</c:forEach> --%>
										<option value="1">경제/경영</option>
										<option value="2">예술</option>
										<option value="3">어린이</option>
										<option value="4">학술</option>
										<option value="5">만화</option>
										<option value="6">기술</option>
										<option value="7">요리</option>
										<option value="8">취미</option>
										<option value="9">교육</option>
										<option value="10">공학</option>
										<option value="11">건강</option>
										<option value="12">역사</option>
										<option value="13">문학</option>
										<option value="14">의학</option>
										<option value="15">미스터리/스릴러</option>
										<option value="16">사회/정치</option>
										<option value="17">종교</option>
										<option value="18">과학</option>
										<option value="19">수학</option>
										<option value="20">자기계발</option>
										<option value="21">스포츠</option>
										<option value="22">청소년</option>
										<option value="23">여행</option>
										<option value="24">잡지</option>
										<option value="25">인문</option>
									</select>			
								</td>
							</tr>
						</table>
						<br><br>
						<div>
							<h3>필기 흔적</h3>
							<input type="radio" name="writing" value="0">없음 <input
								type="radio" name="writing" value="1">연필/샤프 <input
								type="radio" name="writing" value="2">볼펜/형광펜 <br>
							<br>
							<h3>페이지 변색</h3>
							<input type="radio" name="pageDiscoloration" value="0">없음
							<input type="radio" name="pageDiscoloration" value="1">있음
							<br>
							<br>
							<h3>페이지 훼손</h3>
							<input type="radio" name="pageDamage" value="0">없음 <input
								type="radio" name="pageDamage" value="1">있음 <br>
							<br>
							<h3>겉표지</h3>
							<input type="radio" name="coverDamage" value="0">깨끗함 <input
								type="radio" name="coverDamage" value="1">깨끗하지 않음 <br>
						</div>
						<br><br>
						<div>
							<h3>책 설명</h3>
							<textarea name="description" cols="50" rows="8">이 책은 4번 정도 본 것 외에 하자가 없습니다.</textarea>
						</div>
						<br><br>
						<div>
							<h3>사진 등록</h3>
							<input type="file" name="picture" accept="image/*" size="20"/>
						</div>
						<br><br><br><br>
						<table style="width: 100%">
							<tr>
								<td align="left">
									<input type="button" value="취소" onClick="userList('<c:url value='/book/bookListView' />')">
								</td>
								<td align="right">
									<input type="submit" value="등록" onClick="userCreate()"> &nbsp;
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
