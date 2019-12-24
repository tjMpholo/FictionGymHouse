<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@include file="template/header.jsp"%>

<div class="container-wrapper" style="margin-top:1.7%">
    <div class="container">
        <div class="page-header">
        <div class="row">
            <div class="col-md-12">
                <h3>Registered Fiction gym members</h3>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Names</th>
                            <th>Cellphone Number</th>
                            <th>Email</th>
                            <th>Gender</th>
                        </tr>
                    </thead>
                    <spring:forEach items="${memberList}" var="gymMember">
                        <tr>
                            <td>
                                <img src="<spring:url value="/resources/images/${gymMember.imagePath}.jpg"/>" width="100" class="img-responsive img-rounded" alt="No Profile">
                            </td>
                            <td>${gymMember.memberIdentifier}</td>
                            <td>${gymMember.firstName} ${gymMember.lastName}</td>
                            <td>${gymMember.cellphone}</td>
                            <td>${gymMember.emailAddress}</td>
                            
                            <spring:if test="${gymMember.gender == 'M'}">
                                <td>Male</td>
                            </spring:if>

                            <spring:if test="${gymMember.gender == 'F'}">
                                <td>Female</td>
                            </spring:if>

                            <spring:if test="${gymMember.gender == 'O'}">
                                <td>Other</td>
                            </spring:if>

                            <td>
                                <a href="<spring:url value="/gymMember/GymMemberDetailed/${gymMember.defaultId}"/>">
                                    <span class="glyphicon glyphicon-th"></span>
                                </a>
                                &nbsp;
                                <a href="<spring:url value="/gymMember/editGymMemberDetail/${gymMember.defaultId}"/>">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </a>
                                &nbsp;
                                <a href="<spring:url value="/gymMember/deleteMemberDetail/${gymMember.defaultId}"/>">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </a>
                            </td>
                        </tr>
                    </spring:forEach>
                </table>

                <a href="<c:url value="/gymMember/addNewGymMember"/>" class="btn btn-primary btn-sm">Add new Member</a>
            </div>
        </div>
    </div>
    </div>
    </div>

<%@include file="template/footer.jsp"%>
