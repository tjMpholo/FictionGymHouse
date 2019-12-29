<%@include file="template/header.jsp"%>

<div class="container-wrapper" style="margin-top:1.7%">
    <div class="container">
        <div class="page-header">
            <div class="row">
                <div class="col-md-12">
                    <h3>Registered Fiction staff members</h3>
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
                            <spring:choose>
                                <spring:when test="${userRole == 'ROLE_MANAGER'}">
                                    <tr>
                                        <td>
                                            <img src="<spring:url value="/resources/images/${staffMember.imagePath}.jpg"/>" width="100" class="img-responsive img-rounded" alt="No Profile">
                                        </td>
                                        <td>
                                                ${staffMember.firstName}
                                        </td>
                                        <td>${staffMember.lastName}</td>
                                        <td>${staffMember.workTitle}</td>
                                        <td>${staffMember.section}</td>
                                        <td>${staffMember.workTitle}</td>
                                        <td>
                                            <a href="<spring:url value="/gymStaff/staffDetailed/${staffMember.staffMemberId}"/>">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </a>
                                            &nbsp;
                                            <a href="<spring:url value="/gymStaff/editStaffMemberDetail/${staffMember.staffMemberId}"/>">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </a>
                                            &nbsp;
                                            <spring:if test="${pageContext.request.userPrincipal.name != staffMember.username}">
                                                <a href="<spring:url value="/gymStaff/deleteStaffDetail/${staffMember.staffMemberId}"/>">
                                                    <span class="glyphicon glyphicon-remove"></span>
                                                </a>
                                            </spring:if>

                                        </td>
                                    </tr>
                                </spring:when>
                                <spring:when test="${userRole == 'ROLE_SALES_MANAGER'}">
                                    <spring:if test="${staffMember.workTitle == 'Sales Person'}">
                                        <tr>
                                            <td>
                                                <img src="<spring:url value="/resources/images/${staffMember.imagePath}.jpg"/>" width="100" class="img-responsive img-rounded" alt="No Profile">
                                            </td>
                                            <td>
                                                    ${staffMember.firstName}
                                            </td>
                                            <td>${staffMember.lastName}</td>
                                            <td>${staffMember.workTitle}</td>
                                            <td>${staffMember.section}</td>
                                            <td>${staffMember.workTitle}</td>
                                            <td>
                                                <a href="<spring:url value="/gymStaff/staffDetailed/${staffMember.staffMemberId}"/>">
                                                    <span class="glyphicon glyphicon-th"></span>
                                                </a>
                                                &nbsp;
                                                <a href="<spring:url value="/gymStaff/editStaffMemberDetail/${staffMember.staffMemberId}"/>">
                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                </a>
                                                &nbsp;
                                                <spring:if test="${pageContext.request.userPrincipal.name != staffMember.username}">
                                                    <a href="<spring:url value="/gymStaff/deleteStaffDetail/${staffMember.staffMemberId}"/>">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                    </a>
                                                </spring:if>

                                            </td>
                                        </tr>
                                    </spring:if>
                                </spring:when>
                            </spring:choose>

                        </spring:forEach>
                    </table>

                    <a href="<spring:url value="/gymStaff/addNewStaffMember"/>" class="btn btn-sm btn-primary">New staff Member</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp"%>

