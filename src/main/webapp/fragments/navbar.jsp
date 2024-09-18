<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <% if ("ADMIN".equals(session.getAttribute("role"))) { %>
                <li class="nav-item m-1">
                    <a class="btn btn-success" href="/admin/create/region">Region Create</a>
                </li>
                <% } %>
                <li class="nav-item m-1">
                    <a class="btn btn-success" href="/region/list">Region List</a>
                </li>
                <% if ("ADMIN".equals(session.getAttribute("role"))) { %>
                <li class="nav-item m-1">
                    <a class="btn btn-primary" href="/admin/create/district">District Create</a>
                </li>
                <% } %>
                <li class="nav-item m-1">
                    <a class="btn btn-primary" href="/district/list">District List</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <% if (session.getAttribute("id") == null) {%>
                <li class="nav-item m-1">
                    <a href="/auth/login" class="btn btn-success">Login</a>
                </li>
                <li class="nav-item m-1">
                    <a href="/auth/register" class="btn btn-primary">Register</a>
                </li>
                <% } %>
                <% if (session.getAttribute("id") != null) {%>
                <li class="nav-item m-1">
                    <a href="/auth/logout" class="btn btn-danger">Logout</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>