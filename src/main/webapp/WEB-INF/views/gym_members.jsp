<%--
  Created by IntelliJ IDEA.
  User: tumis
  Date: 2019/12/09
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/gym_members/addNewGymMember"/>" class="btn btn-link">Add new Member</a>
</body>
</html>

<%@include file="template/footer.jsp"%>
