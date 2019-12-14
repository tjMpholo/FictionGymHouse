<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<%@include file="template/header.jsp"%>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Fiction Gym Staff Detail</h1>
            <p class="lead">Here is the detailed information of ${staffMember.firstName}.</p>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <img src="<spring:url value="/resources/images/${staffMember.imagePath}.jpg"/>" alt="image" class="img-responsive" style="width: 400px">
                </div>

                <div class="col-md-5">
                    <p>
                        <strong>First Name</strong> : ${staffMember.firstName}
                    </p>
                    <p>
                        <strong>Last Name</strong> : ${staffMember.lastName}
                    </p>
                    <p>
                        <strong>Work Duty</strong> : ${staffMember.workTitle}
                    </p>
                    <p>
                        <strong>Section</strong> : ${staffMember.section}
                    </p>
                    <a href="<c:url value="/gymStaff"/>" class="btn btn-primary btn-sm">Go Back</a>
                </div>
            </div>
        </div>

<%@include file="template/footer.jsp"%>