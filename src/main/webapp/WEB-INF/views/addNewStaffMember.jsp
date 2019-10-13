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
                    <form:form action="${pageContext.request.contextPath}/gymstaff/addNewStaffMember" method="post" commandName="staffMember" >
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <form:input path="firstName" id="firstName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <form:input path="lastName" id="lastName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="workTitle">Title</label>
                            <form:input path="workTitle" id="workTitle" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="section">Section</label>
                            <form:input path="section" id="section" class="form-control" />
                        </div>
                        <input type="submit" value="submit" class="btn btn-default">
                    </form:form>
                </div>
                <div class="col-md-4"></div>
            </div>

        </div>



<%@include file="template/footer.jsp"%>