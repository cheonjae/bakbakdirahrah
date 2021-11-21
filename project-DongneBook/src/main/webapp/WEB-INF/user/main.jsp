<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<% Book book = (Book)request.getAttribute("book"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>동네북</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/listview.css' />" type="text/css">
<style>
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

</style>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>

<table class="tg" style="width:70%; height:auto; margin-left:auto; margin-right:auto; border-collapse : collapse;">

<%!int count = 0; %>
<% if(count > 16) {count = 0;}  %>
<c:forEach var="book" items="${bookList}">
<% if(count % 4 == 0) { %>
  <tr>
<% } %>
    <td class="tg-0lax" style="width:1%">
	<a href="<c:url value='/book/detail'>
					   <c:param name='bookId' value='${book.bookId}'/>
			 		 </c:url>">
    <!--  이미지 일단 보류 -->
    <img src="${pageContext.request.contextPath}/${book.image}" width="150" height="150">
    <br>
    ${book.title}
    <br>
    ${book.price}원</a>
    <%=count %>
    </td>

<% if(count % 4 == 3){ %>
  </tr>
<% }
    	count++;
    %>
  </c:forEach>
</table>

</body>
</html>
