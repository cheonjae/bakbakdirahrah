<%-- <% response.sendRedirect(request.getContextPath() + "/book/bookRegisterForm"); %> --%>

<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>동네북</title>
	<link rel=stylesheet href="<c:url value='/css/bookRegisterForm.css' />"
		type="text/css">
	<style>
		@charset "UTF-8";

		#article {
            margin: 0 15% 5%;
            font-family: 'Malgun Gothic';
            text-align: center;
        }

        #logo {
            color:rgb(147, 176, 255);
            font-size: 30px;
            font-weight: bold;
        }

        /* 헤더 테이블 */
        .headtable {
            width: 1000px;
            font-size: 14px;
            border-spacing: 5px;
        }

        /*헤더 검색창, 버튼*/
        .newlist-button, .sales-button {
            margin: 0;
            width: 160px;
            height: 40px;
            border: 1px solid #999999; /*보더 삭제*/
            box-sizing: border-box;
            border-radius: 6px;
            font-size: 0;

            -webkit-appearance: none;
            border-radius: 6px;
            box-sizing: border-box;
            border: 1px solid #CED4DA;
            color: #212529;
            text-align: center;
            font-weight: 700;
            background-color: #fff;
            margin: 1.6rem 0;
            text-decoration: none;
        }
        .button-text {
            color: #4D5159;
            font-size: 16px;
        }
        #fixed-bar-search {
            left: 150px;
            top: 16px;
            border-radius: 5px;
            border: solid 3px #e9ecef;
            text-decoration: none;
            padding: 0 1.6rem;
            height: 40px;
            box-sizing: border-box;
        }
        .search-input-wrap {
            display: flex;
                    justify-content: space-between;
        }

        .sr-only {
            position: absolute;
            width: 1px;
                height: 1px;
                padding: 0;
                margin: -1px;
                overflow: hidden;
                clip: rect(0 0 0 0);
                border: 0;
        }

        .fixed-search-input {
            display: block;
            width: 300px;
            border: none;
            padding: 0;
            margin: 0;
            font-size: 16px;
            color: #212529;
            background-color: transparent;
        }

        #header-search-button {
            border: none;
            margin: 0;
            background-color: transparent;
        }
        /*헤더 끝*/

        /* 카테고리 */
        aside#left {
            float: left;
            width: 12em;
            margin-right: 1em;
            text-align: left;
        }

        aside#left ul {
            list-style: none
        }

        aside#left ul li {
            font-size: 14px;
            background-color: white;
            padding: 5px 10px;
            border-bottom: 1px solid black;
        }

        aside#left ul li a {
            color: black;
            text-decoration: none;
        }

        aside#left ul li:hover {
            background-color: rgb(255, 245, 190);
        }

        .cat-header {
            pointer-events: none;
        }

        aside#main {
            float: left;
        }

        button.btn1 {
            position: absolute;
            top: 24%;
            left: 42%;
        }

        button.btn2 {
            position: absolute;
            top: 24%;
            left: 53%;
        }

        button.btn3 {
            position: absolute;
            top: 24%;
            left: 64.5%;
        }

        button.btn4 {
            position: absolute;
            top: 24%;
            left: 77%;
        }

        button.btn5 {
            position: absolute;
            top: 57.5%;
            left: 42%;
        }

        button.btn6 {
            position: absolute;
            top: 57.5%;
            left: 53%;
        }

        button.btn7 {
            position: absolute;
            top: 57.5%;
            left: 64.5%;
        }

        button.btn8 {
            position: absolute;
            top: 57.5%;
            left: 77%;
        }

        button.btn9 {
            position: absolute;
            top: 91%;
            left: 42%;
        }

        button.btn10 {
            position: absolute;
            top: 91%;
            left: 53%;
        }

        button.btn11 {
            position: absolute;
            top: 91%;
            left: 64.5%;
        }

        button.btn12 {
            position: absolute;
            top: 91%;
            left: 77%;
        }
        
        /* 책 정보 입력 */
        #page-info {
        	text-align: left;
        	font-size: 18pt; 
        	font-weight: bold;
			color: white; 
			background-color: rgb(147, 176, 255);
        }
        #detail {
            text-align: center;
        }
        
        #book {
        	display: inline-block;
        }
	</style>
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
				<div align="left"><span id="page-info">&nbsp;&nbsp;👉&nbsp;판매 등록 &nbsp;</span></div>
				<br>
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
										<option value="0">카테고리를 선택하세요.</option>
										<%-- <c:forEach var="cate" items="${cateList}}">
											<option value="${cate.cateId}"></option>
										</c:forEach> --%>
									</select>			
								</td>
							</tr>
						</table>
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
							<br>
						</div>
						<div>
							<h3>책 설명</h3>
							<textarea name="description" cols="50" rows="8">이 책은 4번 정도 본 것 외에 하자가 없습니다.</textarea>
						</div>
						<!-- 사진 업로드 부분...사진 업로드를 form 태그 이용해서 해야 하는데
						한 페이지에 폼태그는 하나밖에 사용 못 한다고 함...
						그럼..... 어카지
						<div>
							<h3>사진 등록</h3>
							
						</div> -->
						<table style="width: 100%">
							<tr>
								<td align="left">
									<input type="button" value="책 등록" onClick="userCreate()"> &nbsp;
									<input type="button" value="취소" onClick="userList('<c:url value='/user/list' />')">
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
