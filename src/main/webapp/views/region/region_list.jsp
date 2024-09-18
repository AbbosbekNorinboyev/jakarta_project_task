<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Region List JSP</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-10 offset-1">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Created date</th>
                    <th scope="col">Updated date</th>
                    <th scope="col">Region name</th>
                    <c:if test="${authuser.role == 'ADMIN'}">
                        <th scope="col">Actions</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${regions}" var="region">
                    <tr>
                        <td>${region.id}</td>
                        <td>${region.created_date}</td>
                        <td>${region.updated_date}</td>
                        <td>${region.name}</td>
                        <c:if test="${authuser.role == 'ADMIN'}">
                            <td>
                                <a href="/admin/delete/region/${region.id}" class="btn btn-danger">Delete</a>
                                <a href="/admin/update/region/${region.id}" class="btn btn-warning">Update</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
