<%@page contentType="text/html; charset=utf-8" %>
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
    <meta charset="UTF-8">
    <title>동네북</title>
</head>

<body>
    <!-- home.html의 헤더 재사용 -->
    <header>
        
    </header>

    <section>
            <div id="detail">
                <div id="book">
                    <!-- 책 정보 -->
                    <h3>${book.title}</h3>
                    <table class="info">
                        <tr>
                            <td rowspan="4"><img src="${book.image}" width="150" height="200"></td>
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
                    <textarea name="content" cols="50" rows="8">${book.description}</textarea>
                </div>
            </div>
    </section>
</body>

