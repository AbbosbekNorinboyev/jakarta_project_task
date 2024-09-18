<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Region Create JSP</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-3">
            <h1>Create Region</h1>
            <form action="/admin/create/region" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input id="name" type="text" name="name" class="form-control"/>
                    <c:if test="${error_name != null}">
                        <span class="text-danger">${error_name}</span>
                    </c:if>
                </div>
                <div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="/region/list" class="btn btn-danger">Back</a>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
