<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	String str1 = "없음";
	String str2 = "없음";
	String str3 = "없음";
	String str4 = "깨끗함";
%>
<%
	@SuppressWarnings("unchecked") 
	Book book = (Book)request.getAttribute("book");
	
%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>동네북</title>
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel=stylesheet href="<c:url value='/css/listview.css' />" type="text/css">
</head>

<body>
<%@include file="/WEB-INF/navbar.jsp" %>
    <section>
    	<center>
            <div id="detail">
                <div id="book">
                    <!-- 책 정보 -->
                    <h3>${book.title}</h3>
                    <table class="info">
                        <tr>
                        
                            <td rowspan="5"><img src="${pageContext.request.contextPath}/${book.image}" width="150" height="200"></td>
                            <td class="info-header">저자</td>
                            <td> | ${book.author} </td>
                        </tr>
                        <tr>
                            <td class="info-header">출판사</td>
                            <td> | ${book.publisher}</td>
                        </tr>
                        <tr>
                            <td class="info-header">가격</td>
                            <td> | ${book.price}</td>
                        </tr>
                        <tr>
                            <td class="info-header">판매자</td>
                            <td> | ${book.userId}</td>
                        </tr>
                        <tr>
                            <td style="text-align:left;">
                                <button class="detail-button">
                                    <span class="button-text">찜</span>
                                </button>
                            </td>
                            <td style="text-align:left;">
                                <button class="detail-button">
                                    <span class="button-text">채팅</span>
                                </button>
                            </td>
                        </tr>
                    </table>

                    <!-- 책 상태 -->
                    <h3>책 상태</h3>
                    <table class="info2">
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
                    <h3>책 상세 보기</h3>
                    <textarea name="content" cols="50" rows="8" readonly>${book.description}</textarea>
                </div>
            </div>
    </section>
  
    <a href="<c:url value='/book/delete'>
			<c:param name='bookId' value='${book.bookId}'/>
			<c:param name='userId' value='${user.userId}'/>
		</c:url>" class="btn btn-info" role="button" >삭제</a>
</body>
