package com.example.lesson10tasks.servlets.admin;

import com.example.lesson10tasks.dao.RegionDAO;
import com.example.lesson10tasks.entity.Region;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "com.example.lesson10tasks.servlets.admin.RegionCreateServlet",
        value = "/admin/create/region"
)
@MultipartConfig
public class RegionCreateServlet extends HttpServlet {
    private final RegionDAO regionDAO = new RegionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/region_create.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Map<String, String> errors = new HashMap<>();
        if (name.isBlank()) {
            errors.put("error_name", "Name is null");
        }
        if (!errors.isEmpty()) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/region_create.jsp");
            errors.forEach(req::setAttribute);
            requestDispatcher.forward(req, resp);
        }
        Region region = Region.childBuilders()
                .name(name)
                .build();
        regionDAO.save(region);
        resp.sendRedirect("/region/list");
    }
}
