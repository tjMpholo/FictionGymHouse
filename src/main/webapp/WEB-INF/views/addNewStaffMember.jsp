<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/header.jsp"%>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Fiction Gym Staff members</h1>
            <p class="lead">Check out our friendly staff members..</p>

            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form:form action="${pageContext.request.contextPath}/gymstaff/addNewStaffMember" method="post" enctype="multipart/form-data" commandName="staffMember" >
                        <div class="form-group">
                            <label for="firstName" class="control-label">First Name</label>
                            <form:errors path="firstName" cssStyle="color:red"/>
                            <form:input path="firstName" id="firstName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="control-label">Last Name</label>
                            <form:errors path="lastName" cssStyle="color:red"/>
                            <form:input path="lastName" id="lastName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="workTitle">Title</label>
                            <form:errors path="workTitle" cssStyle="color:red"/>
                            <form:input path="workTitle" id="workTitle" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="section" class="control-label">Section</label>
                            <form:errors path="section" cssStyle="color: red"/>
                            <form:input path="section" id="section" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="staffMemberPicture" class="control-label">Profile Picture</label>
                            <form:input path="staffMemberPicture" id="staffMemberPicture" type="file" class="form-input-large" />
                        </div>

                        <input type="submit" value="submit" class="btn btn-primary">
                    </form:form>
                </div>
                <div class="col-md-4"></div>
            </div>

        </div>



<%@include file="template/footer.jsp"%>