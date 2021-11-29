<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<link rel=stylesheet href="<c:url value='/css/navbar.css' />" type="text/css">
</head>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<a id="logo" style="color:rgb(147, 176, 255); font-size: 30px; font-weight: bold;" href="<c:url value='/user/main' />">
		동네북
	</a>

	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;      
	<c:url value="/book/search" var="searchUrl" />
	<form class="d-flex" style="padding-top:1%; padding-bottom:1%;" action="${searchUrl}">
		<div class="col-md-push-3" style="width:350px;">
			<input class="form-control" type="search" name="title" aria-label="Search" 
			    <c:if test="${empty searchWord}">
    				placeholder="책 제목을 입력해주세요"
				</c:if>
				value="${searchWord}">
		</div>
		<button class="btn btn-outline-success" type="submit" style="background-color:rgb(147, 176, 255); color:white">
			검색
		</button>
	</form>
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
  <ul class="navbar-nav" style="float:right;" >
  	<li style="visibility: hidden;" class="nav-item">
		<a class="nav-link btn-link" href="<c:url value='/user/main' />">실시간 판매 목록</a>
	</li>
  </ul>
	<button style="visibility: hidden;" class="btn btn-success ml-auto mr-1">히든 버튼</button>
    	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
        <span class="navbar-toggler-icon"></span>
    </button>
    <c:if test="${not empty userId}">
	<a href="<c:url value='/chat/list' />" class="btn btn-info" role="button" >채팅</a>
    	&nbsp;
    	<a href="<c:url value='/book/register/form' />" class="btn btn-info" role="button" >판매 등록</a>
    </c:if>
    <div class="collapse navbar-collapse flex-grow-0" id="navbarSupportedContent">
    	
        <ul class="navbar-nav text-right">
			<c:choose>
				<c:when test="${empty userId}">
					<li class="nav-item active">
						<a class="nav-link" href="<c:url value='/user/login/form' />">로그인</a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="<c:url value='/user/register' />">회원가입</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="nav-item dropdown">
              					<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">마이페이지</a>
              					<div class="dropdown-menu">
							<a class="dropdown-item" 
								href="<c:url value='/user/view' >
								<c:param name='userId' value='${userId}'/>
								</c:url>">회원 정보 수정</a>
                					<a class="dropdown-item" href="<c:url value='/user/wishlist' />">위시 리스트</a>
                					<a class="dropdown-item" href="<c:url value='/mypage/history' />">거래 내역</a>
              					</div>
            				</li>
					<li class="nav-item active">
						<a class="nav-link" href="<c:url value='/user/logout'/>">${userId}(로그아웃)</a>
					</li>
				</c:otherwise>
			</c:choose> 
        </ul>
    </div>
</nav>
<body>
	<div id="article" style="float:left;">
            <aside id="left">
	            <ul>
	            	<li class="cat-header"><b>카테고리</b></li>
				     <%
						String[] categoryName = {"건강", "경제/경영", "공학", "과학", "교육", "기술",
								"만화", "문학", "미스터리/스릴러", "사회/정치", "수학", "스포츠", "어린이", "여행", "역사",
								"예술", "요리", "의학", "인문", "자기계발", "잡지", "종교", "청소년", "취미", "학술"};
	
						 for(int i = 1; i < 26; i++) {
							 String str_i = Integer.toString(i);
					 %>	
							 <li>
								<a href="<c:url value='/book/cate'>
									<c:param name='cateId' value='<%=str_i %>'/>
									</c:url>">
									<%=categoryName[i - 1] %></a>
							 </li>		 
					 <%
					 	}
					 %>
                </ul>
            </aside>
        </div>
</body>
