<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<h1>사원등록</h1>
	<form action="insertEmp" method="post" encType="multipart/form-data">
		firstName<input name="firstName">
		lastName<input name="lastName">
		email<input name="email">
		jobId<input name="jobId">
		hireDate<input name="hireDate">
		<input type="file" name="uploadFile" >
		<button>등록</button>	
	</form>
