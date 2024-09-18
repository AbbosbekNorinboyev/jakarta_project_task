package com.example.lesson10tasks.servlets.region;

import com.example.lesson10tasks.dao.AuthUserDAO;
import com.example.lesson10tasks.dao.RegionDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson10tasks.servlets.region.RegionListServlet",
        value = "/region/list"
)
public class RegionListServlet extends HttpServlet {
    private final RegionDAO regionDAO = new RegionDAO();
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = null;
        String id = null;
        for (Cookie cookie : req.getCookies()) {
            name = cookie.getName();
            String cookieName = name;
            id = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals(cookieName))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/region/region_list.jsp");
        req.setAttribute("regions", regionDAO.findAll());
        req.setAttribute("authuser", authUserDAO.findById(Integer.valueOf(id)));
        System.out.println("region list auth user: " + authUserDAO.findById(Integer.valueOf(id)));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(405, "Method not allowed");
    }
}
