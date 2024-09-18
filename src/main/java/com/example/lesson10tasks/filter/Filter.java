package com.example.lesson10tasks.filter;

import com.example.lesson10tasks.dao.AuthUserDAO;
import com.example.lesson10tasks.entity.AuthUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@WebFilter(
        filterName = "com.example.lesson10tasks.filter.Filter",
        urlPatterns = "/*"
)
public class Filter implements jakarta.servlet.Filter {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();
    /*
     * /
     * /auth/login
     * /auth/register
     * /region/list
     * /district/list
     * */
    private static final List<String> OPEN_LIST = List.of(
            "/auth/login",
            "/auth/register"
    );
    private static final Predicate<String> isOpen = OPEN_LIST::contains;
    private static final Predicate<String> urlAdmin = (uri) -> uri.startsWith("/admin");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        if (checkForRememberMe(request)) {
            filterChain.doFilter(request, response);
        } else {
            if (!isOpen.test(requestURI)) {
                HttpSession session = request.getSession();
                Object id = session.getAttribute("id");
                Object role = session.getAttribute("role");
                if (Objects.isNull(id)) {
                    response.sendRedirect("/auth/login");
                } else {
                    if (Objects.equals("USER", role) && urlAdmin.test(requestURI)) {
                        response.sendError(403, "Permission denied");
                        AuthUser user = authUserDAO.findById((Integer) id);
                        System.out.println("user: " + user);
                    } else {
                        filterChain.doFilter(request, response);
                    }
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private boolean checkForRememberMe(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") != null) return true;
        for (Cookie cookie : request.getCookies()) {
            String cookieName = cookie.getName();
            if (cookieName.equals("rememberMe")) {
                AuthUser authUser = authUserDAO.findById(Integer.valueOf(cookie.getValue()));
                System.out.println("authUser: " + authUser);
                session.setAttribute("email", authUser.getEmail());
                session.setAttribute("role", authUser.getRole());
                session.setAttribute("id", authUser.getId());
                return true;
            }
        }
        return false;
    }
}
