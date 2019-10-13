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
                    <td>${staffMember.firstName}</td>
                    <td>${staffMember.lastName}</td>
                    <td>${staffMember.workTitle}</td>
                    <td>${staffMember.section}</td>
                    <td>${staffMember.workTitle}</td>
                    <td>
                        <a href="<spring:url value="/gymstaff/staff_detailed/${staffMember.staffId}"/>">
                            <span class="glyphicon glyphicon-oil"></span>
                        </a>
                    </td>
                </tr>
            </spring:forEach>
        </table>

<%@include file="template/footer.jsp"%>