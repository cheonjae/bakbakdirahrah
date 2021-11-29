<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>사용자 관리 - 회원 가입</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function userModify() {
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.name.focus();
		return false;
	}
	if (form.password.value.length < 6) {
		alert("비밀번호가 너무 짧습니다. 6자리 이상 입력해주세요.");
		form.name.focus();
		return false;
	}
	if (form.password.value.length > 13) {
		alert("비밀번호가 너무 깁니다. 13자리 이하로 입력해주세요.");
		form.name.focus();
		return false;
	}
	if (form.name.value == "") {
		alert("이름을 입력하십시오.");
		form.name.focus();
		return false;
	}
	if (form.name.value.length < 2) {
		alert("이름 형식이 올바르지 않습니다.");
		form.name.focus();
		return false;
	}
	if (form.name.value.length > 10) {
		alert("이름이 너무 깁니다. 10자리 이하로 입력해주세요.");
		form.name.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	if(form.email.value.length < 5) {
		alert("이메일이 너무 짧습니다.");
		form.email.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	if(phoneExp.test(form.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form.phone.focus();
		return false;
	}
	form.submit();
}
</script>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>

<div class="container">  
	<br>
	<h4>회원 정보 수정</h4>
	<br>
	<!-- Update Form  -->
	<form name="form" method="POST" action="<c:url value='/user/update' />">
		<input type="hidden" name="userId" value="${user.userId}"/>	  
		<div class="form-group row">   
	        <label class="col-lg-2 col-form-label">사용자 ID</label>
	        <div class="col-lg-10">
	        	<p class="form-control-static">${user.userId}</p> 
	        </div>
	    </div>       
	    <div class="form-group row">   
	        <label for="password" class="col-lg-2 col-form-label">비밀번호</label>
	        <div class="col-lg-10">
	            <input type="password" name="password" class="form-control" value="${user.password}"> 
	        </div>
	    </div>       
	    <div class="form-group row">  
	        <label for="password2" class="col-lg-2 col-form-label">비밀번호 확인</label>
	        <div class="col-lg-10">
	        	<input type="password" name="password2" class="form-control" value="${user.password}">
	        </div> 
	    </div> 
		<div class="form-group row">   
	        <label for="name" class="col-lg-2 col-form-label">이름</label>
	        <div class="col-lg-10">
	        	<input type="text" name="name" class="form-control" value="${user.name}">
	        </div>
	    </div>		    
	   	<div class="form-group row">   
	        <label for="location" class="col-lg-2 col-form-label">주소</label>
	        <div class="col-lg-10">
				<select name="location" id="location" class="form-control">
				<% 
					String[] region = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", 
									"금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", 
									"성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};
				
					for(int i = 0; i < 25; i++) {
				%>
						<c:set var="currLo" value="<%=region[i] %>" />
						<option value="<%=region[i] %>"
							<c:if test="${currLo eq user.location}">selected="selected"</c:if>
							><%=region[i] %></option>	
				<% 
					}
				%>	
				</select>
	    	</div>
	    </div>
		<div class="form-group row">  
	        <label for="phone" class="col-lg-2 col-form-label">전화번호</label>
	        <div class="col-lg-10">
	        	<input type="text" name="phone" class="form-control" value="${user.phone}">
	        </div>
	    </div>  
	    <div class="form-group row">  
	        <label for="email" class="col-lg-2 col-form-label">이메일 주소</label>
	        <div class="col-lg-10">
	        	<input type="text" name="email" class="form-control" value="${user.email}">
	        </div>
	    </div> 
	   	<br>
		<div class="form-group">
			&nbsp;&nbsp;      
			<input type="button" class="btn btn-primary" value="수정" onClick="userModify()">
			<a class="btn btn-primary" 
				href="<c:url value='/user/view' >
				<c:param name='userId' value='${user.userId}'/>
			</c:url>">취소</a>  		     
		</div>   
	</form>
</div>
</body>
</html>
