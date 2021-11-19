<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Links -->
<a id="logo" style="color:rgb(147, 176, 255); font-size: 30px; font-weight: bold;" href="<c:url value='/' />">
	동네북
</a>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	<c:url value="/user/register" var="searchUrl" />
	<form class="form-inline" style="padding-top:1%" action="location.href='${searchUrl}'; return false;">  
		<input class="form-control" type="text" name="title" id="header-search-input" placeholder="책 이름을 검색해보세요!">  
		<button class="btn btn-success" type="submit" style="background-color:rgb(147, 176, 255); color:white">찾기</button>  
	</form> 
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
  <ul class="navbar-nav">
  	<li class="nav-item">
		<a class="nav-link btn-link" href="<c:url value='/' />">실시간 판매 목록</a>
	</li>
	<li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="<c:url value='/user/main' />" id="navbardrop" data-toggle="dropdown">
       	카테고리
      </a>
      <div class="dropdown-menu">
		<%
			String[] cateName = {"경제 경영", "예술", "어린이", "학술", "만화", "기술",
							"요리", "취미", "교육", "공학", "건강", "역사", "문학", "의학", "미스터리/스릴러",
							"사회 정치", "종교", "과학", "수학", "자기 계발", "스포츠", "청소년", "여행", "잡지", "인문"};
			for(int i = 1; i < 26; i++) {
				String str_i = Integer.toString(i);
		%>
		<a class="dropdown-item" href="<c:url value='/user/main' >
			<c:param name='cateId' value='<%=str_i %>'/>
			</c:url>">
			<%=cateName[i - 1] %></a>	 
		<%
			}
		%>
      </div>
    </li>
	<li class="nav-item dropdown">
		<a class="nav-link dropdown-toggle" href="<c:url value='/' />" id="navbardrop" data-toggle="dropdown">
       		내 정보
		</a>
		<div class="dropdown-menu">
			<c:choose>
				<c:when test="${empty userId}">
					<a class="dropdown-item" href="<c:url value='/user/login/form' />">로그인</a>
					<a class="dropdown-item" href="<c:url value='/user/register' />">회원가입</a>
				</c:when>
				<c:otherwise>
					<a class="dropdown-item" href="<c:url value='/user/myPage' />">마이페이지</a>
					<a class="dropdown-item" href="<c:url value='/user/logout'/>">${userId}(로그아웃)</a>
				</c:otherwise>
			</c:choose> 
		</div>
    </li>
  
	<li class="nav-item">
		<a class="nav-link btn-link" href="<c:url value='/book/register' />">판매 등록</a>
	</li>
  </ul>
</nav>
