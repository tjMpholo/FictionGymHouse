<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/header.jsp"%>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Fiction Gym Staff members</h1>
            <p class="lead">Check out our friendly staff members..</p>
        </div>

        <table class="table table-striped">
            <tr>
                <thead>
                <th>Staff Profile</th>
                <th>Staff member name</th>
                <th>Surname</th>
                <th>Duty</th>
                <th>Section</th>
                <th>Contact number</th>
                <th></th>
                </thead>
            </tr>

            <spring:forEach items="${staffMembers}" var="staffMember">
                <tr>
                    <td>
                        <img src="<spring:url value="/resources/images/${staffMember.imagePath}.jpg"/>" width="100" class="img-responsive img-rounded" alt="No Profile">
                    </td>
                    <td>${staffMember.firstName}</td>
                    <td>${staffMember.lastName}</td>
                    <td>${staffMember.workTitle}</td>
                    <td>${staffMember.section}</td>
                    <td>${staffMember.workTitle}</td>
                    <td>
                        <a href="<spring:url value="/gymStaff/staffDetailed/${staffMember.staffMemberId}"/>">
                            <span class="glyphicon glyphicon-share"></span>
                        </a>
                        &nbsp;
                        <a href="<spring:url value="/gymStaff/editStaffMemberDetail/${staffMember.staffMemberId}"/>">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </a>
                        &nbsp;
                        <a href="<spring:url value="/gymStaff/deleteStaffDetail/${staffMember.staffMemberId}"/>">
                            <span class="glyphicon glyphicon-remove"></span>
                        </a>
                    </td>
                </tr>
            </spring:forEach>
        </table>

        <a href="<spring:url value="/gymStaff/addNewStaffMember"/>" class="btn btn-sm btn-primary">New staff Member</a>

<%@include file="template/footer.jsp"%>