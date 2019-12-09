<%--
  Created by IntelliJ IDEA.
  User: tumis
  Date: 2019/12/09
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<%@include file="template/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h2>Fiction Gym members</h2>
            <p class="lead">Register a new gym member</p>

            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form:form action="${pageContext.request.contextPath}/gym_members/addNewGymMember" method="post" enctype="multipart/form-data" commandName="gymMember">
                        <div class="form-group">
                            <label for="memberIdentifier" class="control-label">RSA ID or passport number</label>
                            <form:errors path="memberIdentifier" cssStyle="color:red"/>
                            <form:input path="memberIdentifier" id="memberIdentifier" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="firstName" class="control-label">First name</label>
                            <form:errors path="firstName" cssStyle="color: red"/>
                            <form:input path="firstName" id="firstName" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="lastName" class="control-label">Last name</label>
                            <form:errors path="lastName" cssStyle="color:red"/>
                            <form:input path="lastName" id="lastName" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="cellphone" class="control-label">Cellphone number</label>
                            <form:errors path="cellphone" cssStyle="color:red"/>
                            <form:input path="cellphone" id="cellphone" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="emailAddress" class="control-label">Email address</label>
                            <form:errors path="emailAddress" cssStyle="color:red;"/>
                            <form:input path="emailAddress" id="emailAddress" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="physicalAddress" class="control-label">Address</label>
                            <form:errors path="physicalAddress" cssStyle="color: red"/>
                            <form:input path="physicalAddress" id="physicalAddress" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="dateOfBirth" class="control-label">Date of birth</label>
                            <form:errors path="dateOfBirth" cssStyle="color: red"/>
                            <form:input path="dateOfBirth" id="dateOfBirth" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="gender" class="control-label">Gender</label>
                            <form:errors path="gender" cssStyle="color: red"/>
                            <form:select path="gender" id="gender" class="form-control">
                                <form:option value="U">Select  your gender</form:option>
                                <form:option value="M">Male</form:option>
                                <form:option value="F">Female</form:option>
                                <form:option value="O">Other</form:option>
                            </form:select>
                        </div>

                        <input type="submit" value="Register new member" class="btn btn-sm btn-success"/>
                    </form:form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </div>
</body>
</html>
