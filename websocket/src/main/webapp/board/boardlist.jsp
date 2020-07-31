<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<h3>공지사항</h3>
<table border='1'>
	<tr><td>1</td><td>title </td></tr>
	<tr><td>2</td><td>title </td></tr>
	<tr><td>3</td><td>title </td></tr>
</table>

<sec:authorize access="hasAuthority('ROLE_ADMIN')">
	<a href="insert.jsp">등록</a>
</sec:authorize>	

			
</body>
</html>