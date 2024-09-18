<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>District Create JSP</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<form method="post" class="mt-5 mb-3 m-5">
    <h1>Create District</h1>
    <div>
        <label for="name" class="form-label">District Name</label>
        <input id="name" type="text" name="name" class="form-control"/>
        <c:if test="${error_name != null}">
            <span class="text-danger">${error_name}</span>
        </c:if>
    </div>
    <div class="mt-3">
        <label for="regionId">Regions</label>
        <select id="regionId" type="button" name="regionId" required>
            <c:forEach items="${regions}" var="region">
                <option value="${region.id}">${region.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mt-3">
        <button type="submit" class="btn btn-success">Save</button>
        <a href="/district/list" class="btn btn-danger">Back</a>
    </div>
</form>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
