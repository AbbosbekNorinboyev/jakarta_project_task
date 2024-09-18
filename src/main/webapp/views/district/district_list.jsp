<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>District List JSP</title>
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
                    <th scope="col">District name</th>
                    <th scope="col">Region(name)</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${districts}" var="district">
                    <tr>
                        <td>${district.id}</td>
                        <td>${district.created_date}</td>
                        <td>${district.updated_date}</td>
                        <td>${district.name}</td>
                        <td>${district.region.name}</td>
                        <th>
                            <a href="/admin/delete/district/${district.id}" class="btn btn-danger">Delete</a>
                            <a href="/admin/update/district/${district.id}" class="btn btn-warning">Update</a>
                        </th>
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
