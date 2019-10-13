<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="template/header.jsp"%>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Fiction Gym Staff Detail</h1>
            <p class="lead">Here is the detailed information of ${staffMember.firstName}.</p>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <img src="#" alt="image" style="width: 100%; height: 300px">
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
                    <p>
                        <strong>Contact Number</strong> : ${staffMember.shortCell}
                    </p>
                    <a href="<c:url value="/gymstaff"/>" class="btn btn-success btn-sm">Go Back</a>
                </div>
            </div>
        </div>

<%@include file="template/footer.jsp"%>