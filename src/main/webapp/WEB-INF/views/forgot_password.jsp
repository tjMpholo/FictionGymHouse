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


<div class="container-wrapper" style="margin-top:2%">
    <div class="container">
        <div class="page-header">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8 ">
                            <img src="<spring:url value="/resources/images/logo.png" />" alt="" style="height: 170px;" class="img-circle center-block img-responsive">
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>
                    <form name="loginForm" action="<c:url value="/MemberLogin/ForgotPassword"/>" method="post" >
                        <c:if test="${not empty error}">
                            <div class="error" style="color: #ff0000;">${error}</div>
                        </c:if>
                        <div class="form-group">
                            <label for="currentUsername">Current Username</label>
                            <input type="text" id="currentUsername" required name="currentUsername" class="form-control input-sm" />
                        </div>

                        <div class="form-group">
                            <label for="newUsername">New Username</label>
                            <input type="email" id="newUsername" required name="newUsername" class="form-control input-sm" />
                        </div>

                        <div class="form-group">
                            <label for="currentPassword">Current Password</label>
                            <input type="password" id="currentPassword" required name="currentPassword" class="form-control input-sm" />
                        </div>

                        <div class="form-group">
                            <label for="newPassword">New Password</label>
                            <input type="password" id="newPassword" required name="newPassword" class="form-control input-sm" />
                        </div>

                        <div class="form-group">
                            <label for="confirmNewPassword">Confirm New Password</label>
                            <input type="password" id="confirmNewPassword" required name="confirmNewPassword" class="form-control input-sm" />
                        </div>

                        <input type="submit"  value="Change Password" class="btn btn-primary btn-sm">
                    </form>
                </div>
                <div class="col-md-2">

                </div>
            </div>

<%@include file="template/footer.jsp"%>