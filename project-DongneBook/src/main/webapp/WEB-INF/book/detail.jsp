<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	String str1 = "없음";
	String str2 = "없음";
	String str3 = "없음";
	String str4 = "깨끗함";
	String cateName = "";
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
	@SuppressWarnings("unchecked") 
	Book book = (Book)request.getAttribute("book");
	HttpSession session1 = request.getSession();	
	String userId = (String) session.getAttribute("userId");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>동네북</title>
    <!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel=stylesheet href="<c:url value='/css/detail.css' />" type="text/css">
	<script>
function userRemove() {
	if(confirm("정말 삭제하시겠습니까?")) {
		if(userId != book.getUserId()) {
			alert('타인의 어쩌고.');
		} else {
			alert('삭제 되었습니다.');
		}
	} else {
		alert('취소되었습니다');
	}	
}
</script>

</head>
<body class="detail-body">
<%@include file="/WEB-INF/navbar.jsp" %> 
    <section class="detail-section">
            <div id="detail">
                <div id="book">
                    <!-- 책 정보 -->
                    <br><br>
                    <%
                    %>
                    <b>국내도서/<%= Catename(book.getCateId()) %></b>
                    <br>
                    <h3><b>${book.title}</b></h3>
                    <table class="detail-info">
                        <tr>
                        
                            <td rowspan="5"><img src="${pageContext.request.contextPath}/${book.image}" width="200" height="250"></td>
                            <td>저자</td>
                            <td> |&ensp;${book.author} </td>
                        </tr>
                        <tr>
                            <td>출판사</td>
                            <td> |&ensp;${book.publisher}</td>
                        </tr>
                        <tr>
                            <td>가격</td>
                            <td> |&ensp;${book.price}</td>
                        </tr>
                        <tr>
                            <td >판매자</td>
                            <td> |&ensp;${book.userId}</td>
                        </tr>
                        <tr>
                            <td>
                                <button class="detail-button">
                                    <span class="detail-button-text">찜</span>
                                </button>
                            </td>
                            <td>
                                <button class="detail-button">
                                    <span class="detail-button-text">채팅</span>
                                </button>
                            </td>
                        </tr>
                    </table>

                    <!-- 책 상태 -->
                    <br><br>
                    <h3><b>책 상태</b></h3>
                    <table class="detail-info2">
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

                    <!-- 책 상세 보기 -->
                    <br><br>
                    <h3><b>책 설명</b></h3>
                    <textarea name="content" cols="50" rows="8" readonly>${book.description}</textarea>
                </div>
            </div>
    <%
    	if(userId.equals(book.getUserId())) {
    %>
     <a class="detail-btn-primary" 
    	href="<c:url value='/book/update' >
     		     <c:param name='bookId' value='${book.bookId}'/>
		 	  </c:url>">수정</a>
    <a class="detail-btn-warning" 
   		href="<c:url value='/book/delete'>
		     	 <c:param name='bookId' value='${book.bookId}'/>
		     	 <c:param name='userId' value='${user.userId}' />
	 	      </c:url>" onclick="return userRemove();">삭제</a>
	<% 
		} 
	%>
	   </section>
</body>
