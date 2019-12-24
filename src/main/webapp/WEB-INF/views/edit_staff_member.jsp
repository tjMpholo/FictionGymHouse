<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/header.jsp"%>

<div class="container-wrapper" style="margin-top:1.2%">
    <div class="container">
        <div class="page-header">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <h3>Fiction Gym Staff members</h3>
                    <p class="lead">Check out our friendly staff members..</p>

                    <form:form action="${pageContext.request.contextPath}/gymStaff/editStaffMemberDetail" method="post" enctype="multipart/form-data" commandName="staffMember" >
                        <form:hidden path="staffMemberId" value="${staffMember.staffMemberId}"/>
                        <form:hidden path="password" value="${staffMember.password}"/>
                        <div class="form-group">
                            <label for="rsaIdNumber" class="control-label">RSA ID number</label>
                            <form:errors path="rsaIdNumber" cssStyle="color:red"/>
                            <form:input path="rsaIdNumber" id="rsaIdNumber" class="form-control input-sm"/>
                        </div>
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <form:errors path="firstName" cssStyle="color:red"/>
                            <form:input path="firstName" id="firstName" class="form-control input-sm"/>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <form:errors path="lastName" cssStyle="color: red"/>
                            <form:input path="lastName" id="lastName" class="form-control input-sm"/>
                        </div>
                        <div class="form-group">
                            <label for="workTitle">Title</label>
                            <form:errors path="workTitle" cssStyle="color: red"/>
                            <form:select path="workTitle" class="form-control input-sm">
                                <form:option value="Manager"/>
                                <form:option value="Sales Manager"/>
                                <form:option value="Instructor"/>
                                <form:option value="General Worker"/>
                                <form:option value="Sales Person"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="section">Section</label>
                            <form:errors path="section" cssStyle="color: red"/>
                            <form:input path="section" id="section" class="form-control input-sm" />
                        </div>
                        <div class="form-group">
                            <label for="staffMemberPicture" class="control-label">Profile Picture</label>
                            <form:input path="staffMemberPicture" id="staffMemberPicture" type="file" class="form-control input-sm" />
                        </div>

                        <input type="submit" value="submit" class="btn btn-sm btn-primary">
                    </form:form>
                </div>
                <div class="col-md-4"></div>
            </div>

        </div>
        </div>



<%@include file="template/footer.jsp"%>