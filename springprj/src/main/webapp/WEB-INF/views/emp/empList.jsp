<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script>
	 $(function(){
		$(".getEmp").on("click", function(){
			var empid = $(this).html();
			//$("#getEmpDiv").load("getEmp/" + empid)
			
			/* $.getJSON("getEmpAjax", {employeeId:empid}, function(result){
				$(".row").find(".col").eq(0).html(result.employeeId);
				$(".row").find(".col").eq(1).html(result.firstName);
				$(".row").find(".col").eq(2).html(result.email);
			}) *///url, parameter, 콜백함수
			
			$.ajax({
				url : "getEmpAjax",
				method : "get",
				dataType : "json",//받아오는 결과타입
				data : {employeeId:empid} //넘겨주는 함수
				/* ,success : function(result){
					$(".row").find(".col").eq(0).html(result.employeeId);
					$(".row").find(".col").eq(1).html(result.firstName);
					$(".row").find(".col").eq(2).html(result.email);
				},
				error : function(){
					alert("error");
				},
				async : false,
				cache : false */
			}).done(function(result){
				$(".row").find(".col").eq(0).html(result.employeeId);
				$(".row").find(".col").eq(1).html(result.firstName);
				$(".row").find(".col").eq(2).html(result.email);
			}).fail(function(){
				alert("error")
			})
			.always(function(){})
		});
	}); 
	
</script>
</head>
<body>
	<h3>사원목록</h3>
	<div id="getEmpDiv">
		<div class="row">
			<div class="col"></div>
			<div class="col"></div>
			<div class="col"></div>
		</div>
	</div>
	
	<c:forEach items="${empList}" var="emp">
		${emp.firstName} ${emp.lastName} -
		<div class="getEmp">${emp.employeeId}</div> 
		<img src="download?name=${emp.profile}" style="width:80px;" onerror="this.src='images/Chrysanthemum.jpg'">
		<br>
	</c:forEach>
	<a href="report.do" target="_blank">pdf</a>
</body>
</html>