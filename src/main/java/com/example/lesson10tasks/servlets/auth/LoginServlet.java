package com.example.lesson10tasks.servlets.auth;

import com.example.lesson10tasks.dao.AuthUserDAO;
import com.example.lesson10tasks.entity.AuthUser;
import com.example.lesson10tasks.utils.PasswordUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson10tasks.servlets.auth.LoginServlet",
        value = "/auth/login"
)
public class LoginServlet extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        authUserDAO.findByEmail(email).ifPresentOrElse(authUser -> {
                    if (!PasswordUtils.check(password, authUser.getPassword())) {
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
                        req.setAttribute("error_message", "Bad credentials password");
                        try {
                            requestDispatcher.forward(req, resp);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (!authUser.getStatus().equals(AuthUser.Status.ACTIVE)) {
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
                        req.setAttribute("error_message", "User not active");
                        try {
                            requestDispatcher.forward(req, resp);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        String rememberMe = Objects.requireNonNullElse(req.getParameter("rememberMe"), "off");
                        HttpSession session = req.getSession();
                        session.setAttribute("email", authUser.getEmail());
                        session.setAttribute("role", authUser.getRole());
                        session.setAttribute("id", authUser.getId());
                        if (rememberMe.equals("on")) {
                            Cookie cookie = new Cookie("rememberMe", String.valueOf(authUser.getId()));
                            cookie.setMaxAge(60 * 60 * 24 * 100);
                            cookie.setPath("/");
                            resp.addCookie(cookie);
                        }
                        try {
                            resp.sendRedirect("/region/list");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                () -> {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
                    req.setAttribute("error_message", "Bad credentials");
                    try {
                        requestDispatcher.forward(req, resp);
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
