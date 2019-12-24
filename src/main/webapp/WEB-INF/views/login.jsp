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
                    <form action="#" method="post" >
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" id="username" name="username" class="form-control input-sm" />
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="text" id="password" name="password" class="form-control input-sm" />
                        </div>

                        <input type="submit" name="" id="" value="Login" class="btn btn-primary btn-sm">
                        <a href="#" class="btn btn-primary btn-sm">Forgot password</a>
                    </form>
                </div>
                <div class="col-md-2">

                </div>
                </div>

<%@include file="template/footer.jsp"%>