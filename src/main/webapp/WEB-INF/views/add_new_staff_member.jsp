<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<%@include file="template/header.jsp"%>

<div class="container-wrapper" style="margin-top:1.2%">
    <div class="container">
        <div class="page-header">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <h3>Fiction Gym Staff members</h3>
                    <p class="lead">Register a new staff members..</p>
                    <form:form action="${pageContext.request.contextPath}/gymStaff/addNewStaffMember" method="post" enctype="multipart/form-data" commandName="staffMember" >
                        <div class="form-group">
                            <label for="rsaIdNumber" class="control-label">RSA ID number</label>
                            <c:if test="${not empty idError}">
                                <p class="error center-block" style="color: #ff0000;">${idError}</p>
                            </c:if>
                            <form:errors path="rsaIdNumber" cssStyle="color:red"/>
                            <form:input path="rsaIdNumber" id="rsaIdNumber" class="form-control input-sm"/>
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="control-label">First Name</label>
                            <form:errors path="firstName" cssStyle="color:red"/>
                            <form:input path="firstName" id="firstName" class="form-control input-sm"/>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="control-label">Last Name</label>
                            <form:errors path="lastName" cssStyle="color:red"/>
                            <form:input path="lastName" id="lastName" class="form-control input-sm"/>
                        </div>

                        <div class="form-group">
                            <label for="emailAddress" class="control-label">Email address</label>
                            <c:if test="${not empty emailError}">
                                <p class="error center-block" style="color: #ff0000;">${emailError}</p>
                            </c:if>
                            <form:errors path="emailAddress" cssStyle="color:red;"/>
                            <form:input path="emailAddress" id="emailAddress" class="form-control input-sm"/>
                        </div>

                        <div class="form-group">
                            <label for="workTitle">Title</label>
                            <form:errors path="workTitle" cssStyle="color:red"/>
                            <form:select path="workTitle" class="form-control input-sm">
                                <spring:choose>
                                    <spring:when test="${userRole == 'ROLE_MANAGER'}">
                                        <form:option value="Manager"/>
                                        <form:option value="Sales Manager"/>
                                        <form:option value="Instructor"/>
                                        <form:option value="General Worker"/>
                                        <form:option value="Receptionist"/>
                                    </spring:when>
                                    <spring:when test="${userRole == 'ROLE_SALES_MANAGER'}">
                                        <form:option value="Sales Person" />
                                    </spring:when>
                                </spring:choose>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="section" class="control-label">Section</label>
                            <form:errors path="section" cssStyle="color: red"/>
                            <form:input path="section" id="section" class="form-control input-sm" />
                        </div>
                        <div class="form-group">
                            <label for="staffMemberPicture" class="control-label">Profile Picture</label>
                            <form:input path="staffMemberPicture" id="staffMemberPicture" type="file" class="form-control input-sm" />
                            <form:hidden path="isProfileSet" value="${staffMember.isProfileSet}"/>
                        </div>

                        <input type="submit" value="submit" class="btn btn-primary btn-sm">
                    </form:form>
                </div>
                <div class="col-md-4"></div>
            </div>

        </div>
        </div>



<%@include file="template/footer.jsp"%>