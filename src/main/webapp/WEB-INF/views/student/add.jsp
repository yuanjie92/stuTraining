<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<script type="text/javascript" src="/_ui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/_ui/js/add.js"></script>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add student</title>
</head>
<body>
	<h1>add student</h1>

	<form action="addStudent" method="post" enctype="multipart/form-data">
		<input type="file" name="files" id="uploadPic"/><br>
		<spring:message code="student.form.username" />:<input type="text" name="name" ><br>
		<spring:message code="student.form.age" />:<input type="text" name="age" ><br>
		<spring:message code="student.form.clazz" />:<input type="text" name="clazz" ><br>
		<spring:message code="student.form.birthday" />:<input type="date" name="birthday" ><br>
		<select id="prov" name="addressForm.provCode">
			<option value="" >-请选择-</option>
			<c:forEach items="${areas}" var="prov">
				<option value="${prov.code}">${prov.name}</option>
			</c:forEach>
		</select>
		<select id="city" name="addressForm.cityCode">
			<option value="">-请选择-</option>
		</select>
		<select id="area" name="addressForm.areaCode">
			<option value="" >-请选择-</option>
		</select>
		<br>
		<spring:message code="student.form.addrName" />:<input type="text" name = "addressForm.addrName"/>
		<br>

		<input type="hidden" id="provName" name="addressForm.provName" >
		<input type="hidden" id="cityName" name="addressForm.cityName" >
		<input type="hidden" id="areaName" name="addressForm.areaName" >

		<input type="submit" value='<spring:message code="student.form.add" />' id="formSubmit"/>
	</form>
</body>
</html>