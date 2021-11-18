<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>동네북</title>
<link rel=stylesheet href="<c:url value='/css/bookRegisterForm.css' />"
	type="text/css">
<link rel="stylesheet" href="../../../css/bookRegisterForm.css">
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
				<ul>
					<li class="cat-header"><b>국내도서</b></li>
					<li><a href="./computer.html" target="iframe1">가정 살림</a></li>
					<li><a href="./clothing.html" target="iframe1">건강 취미</a></li>
					<li><a href="./music.html" target="iframe1">경제 경영</a></li>
					<li><a href="./movie.html" target="iframe1">국어 외국어 사전</a></li>
					<li><a href="./computer.html" target="iframe1">대학교재</a></li>
					<li><a href="./computer.html" target="iframe1">만화/라이트노벨</a></li>
					<li><a href="./computer.html" target="iframe1">사회 정치</a></li>
					<li><a href="./computer.html" target="iframe1">소설/시/희곡</a></li>
					<li><a href="./computer.html" target="iframe1">수험서 자격증</a></li>
					<li><a href="./computer.html" target="iframe1">어린이</a></li>
					<li><a href="./computer.html" target="iframe1">에세이</a></li>
					<li><a href="./computer.html" target="iframe1">여행</a></li>
					<li><a href="./computer.html" target="iframe1">역사</a></li>
					<li><a href="./computer.html" target="iframe1">예술</a></li>
					<li><a href="./computer.html" target="iframe1">유아</a></li>
					<li><a href="./computer.html" target="iframe1">인문</a></li>
					<li><a href="./computer.html" target="iframe1">자기계발</a></li>
					<li><a href="./computer.html" target="iframe1">자연과학</a></li>
					<li><a href="./computer.html" target="iframe1">잡지</a></li>
					<li><a href="./computer.html" target="iframe1">전집</a></li>
					<li><a href="./computer.html" target="iframe1">종교</a></li>
					<li><a href="./computer.html" target="iframe1">청소년</a></li>
					<li><a href="./computer.html" target="iframe1">IT 모바일</a></li>
					<li><a href="./computer.html" target="iframe1">초등참고서</a></li>
					<li><a href="./computer.html" target="iframe1">중고등참고서</a></li>

					<li class="cat-header"><b>해외도서</b></li>
					<li><a href="./computer.html" target="iframe1">ELT 사전</a></li>
					<li><a href="./computer.html" target="iframe1">문학 소설</a></li>
					<li><a href="./computer.html" target="iframe1">경제 경영</a></li>
					<li><a href="./computer.html" target="iframe1">인문 사회</a></li>
					<li><a href="./computer.html" target="iframe1">예술 대중문화</a></li>
					<li><a href="./computer.html" target="iframe1">취미 라이프스타일</a></li>
					<li><a href="./computer.html" target="iframe1">컴퓨터</a></li>
					<li><a href="./computer.html" target="iframe1">자연과학</a></li>
					<li><a href="./computer.html" target="iframe1">대학교재 전문서</a></li>
					<li><a href="./computer.html" target="iframe1">해외잡지</a></li>
					<li><a href="./computer.html" target="iframe1">유아 어린이 청소년</a></li>
					<li><a href="./computer.html" target="iframe1">캐릭터북</a></li>
					<li><a href="./computer.html" target="iframe1">초등코스북</a></li>
					<li><a href="./computer.html" target="iframe1">학습서</a></li>
					<li><a href="./computer.html" target="iframe1">일본도서</a></li>
					<li><a href="./computer.html" target="iframe1">중국도서/<br>기타
							아시아 도서
					</a></li>
					<li><a href="./computer.html" target="iframe1">프랑스도서</a></li>
				</ul>
			</aside>


			<div id="detail">
				<div id="book">
					<form name="book-regi" method="POST" action="<c:url value='/book/register' />">
						<table>
							<tr>
								<td class="title">&nbsp;&nbsp;<b>판매할 책 정보 입력</b>&nbsp;&nbsp;</td>
							</tr>
						</table>
						<c:if test="${registerFailed}">
							<font color="red"><c:out value="${exception.getMessage()}" /></font>
						</c:if>
						<br>
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
									<input type="text" style="width: 240;" name="price">
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
