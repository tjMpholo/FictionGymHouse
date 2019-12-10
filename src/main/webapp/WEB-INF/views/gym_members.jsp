<%--
  Created by IntelliJ IDEA.
  User: tumis
  Date: 2019/12/09
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <h3>Registered Fiction gym members</h3>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID Number</th>
                            <th>Names</th>
                            <th>Cellphone Number</th>
                            <th>Email</th>
                            <th>Gender</th>
                        </tr>
                    </thead>

                    <spring:forEach items="${memberList}" var="gymMember">
                        <tr>
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
                                <a href="<spring:url value="/gym_members/view_gym_member_details/${gymMember.defaultId}"/>">
                                    <span class="glyphicon glyphicon-th"></span>
                                </a>
                                <a href="<spring:url value="/gym_members/edit_gym_member_details/${gymMember.defaultId}"/>">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </a>
                            </td>
                        </tr>
                    </spring:forEach>
                </table>

                <a href="<c:url value="/gym_members/addNewGymMember"/>" class="btn btn-success btn-sm">Add new Member</a>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
    </div>
    </div>

</body>
</html>

<%@include file="template/footer.jsp"%>
