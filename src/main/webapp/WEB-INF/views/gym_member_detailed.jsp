<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<%@include file="template/header.jsp"%>
<div class="container-wrapper" style="margin-top:1.7%">
    <div class="container">
        <div class="page-header">
            <h3>Fiction Gym Member Detail</h3>
            <p class="lead">Here is the detailed information of ${gymMember.firstName} ${gymMember.lastName}.</p>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <img src="<spring:url value="/resources/images/${gymMember.imagePath}.jpg" />" alt="Member profile picture" style="width: 400%" class="img-responsive">
                </div>

                <div class="col-md-5">
                    <p>
                        <spring:if test="${gymMember.isRsaCitizen == true}">
                                <strong>RSA Identity number</strong>
                            </spring:if>
                            <spring:if test="${gymMember.isRsaCitizen == false}">
                                <strong>Passport number</strong>
                            </spring:if>: ${gymMember.memberIdentifier}
                    </p>
                    <p>
                        <strong>First Name</strong> : ${gymMember.firstName}
                    </p>
                    <p>
                        <strong>Last Name</strong> : ${gymMember.lastName}
                    </p>
                    <p>
                        <strong>Cellphone</strong> : ${gymMember.cellphone}
                    </p>
                    <p>
                        <strong>Email address</strong> : ${gymMember.emailAddress}
                    </p>
                    <p>
                        <strong>Physical Address</strong> : ${gymMember.physicalAddress}
                    </p>
                    <p>
                        <strong>Gender</strong> :
                        <spring:if  test="${gymMember.gender == 'M'}">
                            Male
                        </spring:if>

                        <spring:if  test="${gymMember.gender == 'F'}">
                            Female
                        </spring:if>

                        <spring:if  test="${gymMember.gender == 'O'}">
                            Other
                        </spring:if>
                    </p>
                    <p>
                        <strong>South African Citizen</strong> :
                        <spring:if test="${gymMember.isRsaCitizen == true}">
                            Yes
                        </spring:if>
                        <spring:if test="${gymMember.isRsaCitizen == false}">
                            No
                        </spring:if>
                    </p>
                    <div class="alert alert-success">
                        <spring:if test="${gymMember.lastLoginDate == null}">
                            <strong>Username: </strong> ${gymMember.username}
                            <br>
                            <strong>Temporary Password: </strong> ${gymMember.password}
                        </spring:if>
                        <spring:if test="${gymMember.lastLoginDate != null}">
                            Login details have been changed by ${gymMember.firstName} already!
                        </spring:if>
                    </div>

                    <a href="<c:url value="/gymMember/All"/>" class="btn btn-primary btn-sm">Go Back</a>
                </div>
            </div>
        </div>
        </div>

<%@include file="template/footer.jsp"%>