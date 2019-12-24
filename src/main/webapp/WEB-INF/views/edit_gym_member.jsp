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

<div class="container-wrapper" style="margin-top:1.2%">
    <div class="container">
        <div class="page-header">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <h3>Fiction Gym members</h3>
                <p class="lead">Edit gym member details</p>
                <form:form action="${pageContext.request.contextPath}/gymMember/editStaffMemberDetail" method="post" enctype="multipart/form-data" commandName="gymMember">
                    <form:hidden path="defaultId" value="${gymMember.defaultId}"/>
                    <form:hidden path="password" value="${gymMember.password}"/>
                    <div class="form-group">
                        <label for="memberIdentifier" class="control-label">RSA ID or passport number</label>
                        <form:errors path="memberIdentifier" cssStyle="color:red"/>
                        <form:input path="memberIdentifier" id="memberIdentifier" class="form-control input-sm"/>
                    </div>

                    <div class="form-group">
                        <label for="firstName" class="control-label">First name</label>
                        <form:errors path="firstName" cssStyle="color: red"/>
                        <form:input path="firstName" id="firstName" class="form-control input-sm"/>
                    </div>

                    <div class="form-group">
                        <label for="lastName" class="control-label">Last name</label>
                        <form:errors path="lastName" cssStyle="color:red"/>
                        <form:input path="lastName" id="lastName" class="form-control input-sm"/>
                    </div>

                    <div class="form-group">
                        <label for="cellphone" class="control-label">Cellphone number</label>
                        <form:errors path="cellphone" cssStyle="color:red"/>
                        <form:input path="cellphone" id="cellphone" class="form-control input-sm"/>
                    </div>

                    <div class="form-group">
                        <label for="emailAddress" class="control-label">Email address</label>
                        <form:errors path="emailAddress" cssStyle="color:red;"/>
                        <form:input path="emailAddress" id="emailAddress" class="form-control input-sm"/>
                    </div>

                    <div class="form-group">
                        <label for="physicalAddress" class="control-label">Address</label>
                        <form:errors path="physicalAddress" cssStyle="color: red"/>
                        <form:input path="physicalAddress" id="physicalAddress" class="form-control input-sm"/>
                    </div>

                    <div class="form-group">
                        <label for="dateOfBirth" class="control-label">Date of birth</label>
                        <form:errors path="dateOfBirth" cssStyle="color: red"/>
                        <form:input path="dateOfBirth" id="dateOfBirth" class="form-control input-sm"/>
                    </div>

                    <div class="form-group">
                        <label for="gender" class="control-label">Gender</label>
                        <form:errors path="gender" cssStyle="color: red"/>
                        <form:select path="gender" id="gender" class="form-control input-sm">
                            <form:option value="U">Select  your gender</form:option>
                            <form:option value="M">Male</form:option>
                            <form:option value="F">Female</form:option>
                            <form:option value="O">Other</form:option>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="memberProfilePicture" class="control-label">Profile Picture</label>
                        <form:input path="memberProfilePicture" id="memberProfilePicture" type="file" class="form-control input-sm"/>
                        <form:hidden path="imagePath"  id="imagePath" value="${gymMember.imagePath}"/>
                    </div>

                    <input type="submit" value="Update member details" class="btn btn-sm btn-primary"/>
                </form:form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</div>
</div>

<%@include file="template/footer.jsp"%>

