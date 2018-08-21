<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>students list</title>
</head>
<body>
<h1>students list</h1>
<hr>
<a href="addStudent">add Student</a>
<table border="1">
    <thead>
    <tr>
        <th><spring:message code="student.form.index"/></th>
        <th><spring:message code="student.form.id"/></th>
        <th><spring:message code="student.form.headImg"/></th>
        <th><spring:message code="student.form.username"/></th>
        <th><spring:message code="student.form.age"/></th>
        <th><spring:message code="student.form.clazz"/></th>
        <th><spring:message code="student.form.birthday"/></th>
        <th><spring:message code="student.form.createTime"/></th>
        <th><spring:message code="student.form.modifyTime"/></th>
        <th><spring:message code="student.form.addrName"/></th>
        <th colspan="2"><spring:message code="student.form.operation"/></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="results" value="${searchResults.results }"></c:set>
    <c:set var="pagination" value="${searchResults.pagination }"></c:set>

    <c:forEach items="${results }" var="stu" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${stu.id}</td>
                <%--从本地读取图片--%>
            <td>
                <c:url var="imgUrl" value="/student/showPic"/>
                <img style="width:80px;height: 100px" src="${imgUrl}?context=${stu.headImg}"/>
            </td>
                <%--从Apache服务器读取图片--%>
                <%--<td><img style="width:80px;height: 100px" src="http://localhost:80/img/${stu.headImg}"/></td>--%>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.clazz}</td>
            <td><fmt:formatDate value="${stu.birthday}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${stu.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${stu.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${stu.addressData.provName}&nbsp;${stu.addressData.cityName}&nbsp;${stu.addressData.areaName}&nbsp;${stu.addressData.addrName}</td>
            <td><a href="/student/loadStudentById?id=${stu.id}"><spring:message code="student.form.update"/></a></td>
            <td><a href="/student/deleteStudentById?id=${stu.id}"><spring:message code="student.form.delete"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:set var="currentPage" value="${pagination.currentPage }"></c:set>
<c:if test="${currentPage != 1 }">
    <a href="studentList?currentPage=1"><spring:message code="first.page"/></a>
</c:if>
<c:if test="${currentPage>1 }">
    <a href="studentList?currentPage=${currentPage-1 }"><spring:message code="pre.page"/></a>&nbsp;
</c:if>
<c:if test="${currentPage>=1 && currentPage<=pagination.totalPage }">
    <a href="studentList?currentPage=1">1</a>&nbsp;
</c:if>
<c:if test="${currentPage>=1 && currentPage<=pagination.totalPage }">
    <a href="studentList?currentPage=2">2</a>&nbsp;
</c:if>
<c:if test="${currentPage>=1 && currentPage<=pagination.totalPage }">
    <a href="studentList?currentPage=3">3</a>&nbsp;
</c:if>
<c:if test="${currentPage>=1 && currentPage<=pagination.totalPage }">
    <a href="studentList?currentPage=4">4</a>&nbsp;
</c:if>
<c:if test="${currentPage>=1 && currentPage<=pagination.totalPage }">
    <a href="studentList?currentPage=5">5</a>&nbsp;
</c:if>
<%--${currentPage}&nbsp;--%>
<c:if test="${currentPage<pagination.totalPage }">
    <a href="studentList?currentPage=${currentPage+1 }"><spring:message code="next.page"/></a>
</c:if>
<c:if test="${currentPage ne pagination.totalPage }">
    <a href="studentList?currentPage=${pagination.totalPage}"><spring:message code="last.page"/></a>
</c:if>
</body>
</html>