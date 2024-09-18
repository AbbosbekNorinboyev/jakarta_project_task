package com.example.lesson10tasks.servlets.auth;

import com.example.lesson10tasks.dao.AuthUserDAO;
import com.example.lesson10tasks.entity.AuthUser;
import com.example.lesson10tasks.utils.PasswordUtils;
import com.example.lesson10tasks.utils.StringUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson10tasks.servlets.auth.RegisterServlet",
        value = "/auth/register"
)
public class RegisterServlet extends HttpServlet {
    private static final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");
        if (!StringUtils.validEmail(email)) {
            errors.put("email_error", "Email is invalid");
        } else {
            authUserDAO.findByEmail(email)
                    .ifPresent((authUser -> errors.put("email_error", "Email already taken")));
        }
        if (email.isBlank()) {
            errors.put("email_error", "Email is null");
        }
        if (password.isBlank() || confirmPassword.isBlank()) {
            errors.put("password_error", "Password is null");
        }
        if (!Objects.equals(password, confirmPassword)) {
            errors.put("password_error", "Password is invalid");
        }
        if (!errors.isEmpty()) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/authuser/login.jsp");
            errors.forEach(req::setAttribute);
            requestDispatcher.forward(req, resp);
        }
        AuthUser authUser = AuthUser.childBuilder()
                .email(email)
                .password(PasswordUtils.encode(password))
                .role("ADMIN")
                .status(AuthUser.Status.ACTIVE)
                .build();
        authUserDAO.save(authUser);
        resp.sendRedirect("/auth/login");
    }
}
