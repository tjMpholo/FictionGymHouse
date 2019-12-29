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
                            <th>Monday</th>
                            <th>Tuesday</th>
                            <th>Wednesday</th>
                            <th>Thursday</th>
                            <th>Friday</th>
                            <th>Saturday</th>
                            <th>Sunday</th>
                        </tr>
                        </thead>

                        <spring:forEach var = "i" begin = "1" end = "28">
                            <tr>
                            <spring:forEach var = "i" begin = "1" end = "7">
                                <td></td>
                            </spring:forEach>
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
