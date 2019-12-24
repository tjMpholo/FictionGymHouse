<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<%@include file="template/header.jsp"%>

<div class="container-wrapper" style="margin-top:1.7%">
    <div class="container">
        <div class="page-header">
            <h3>Fiction Gym Staff Detail</h3>
            <p class="lead">Here is the detailed information of ${staffMember.firstName} ${staffMember.lastName}.</p>
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
                    <div class="alert alert-success">
                        <spring:if test="${staffMember.lastLoginDate == null}">
                            <strong>Username: </strong> ${staffMember.username}
                            <br>
                            <strong>Temporary Password: </strong> ${staffMember.password}
                        </spring:if>
                        <spring:if test="${staffMember.lastLoginDate != null}">
                            Password has been changed by staff member already!
                        </spring:if>
                    </div>
                    <a href="<c:url value="/gymStaff"/>" class="btn btn-primary btn-sm">Go Back</a>
                </div>
            </div>
        </div>
        </div>

<%@include file="template/footer.jsp"%>