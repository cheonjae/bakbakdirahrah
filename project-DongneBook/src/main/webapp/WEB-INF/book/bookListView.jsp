<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<% Book book = (Book)request.getAttribute("book"); %>
<%
	int currentPage = 1, countPerPage = 10;
	String currentPageStr = request.getParameter("currentPage");
	if( (currentPageStr != null) &&  (!currentPageStr.equals("")) )
		currentPage = Integer.parseInt(currentPageStr);
	
	BookManager manager = BookManager.getInstance();
	List<Book> bookList = manager.mainBookList(currentPage, countPerPage);
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bookListView</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/listview.css' />" type="text/css">
</head>
<body>
<table class="tg">


<c:forEach var="user" items="${userList}">
  <tr>
    <td class="tg-0lax">
    <!--  이미지 일단 보류 -->
    ${book.title}
    ${book.price}
    </td>
  </tr>
  </c:forEach>
</table>

</body>
</html>
