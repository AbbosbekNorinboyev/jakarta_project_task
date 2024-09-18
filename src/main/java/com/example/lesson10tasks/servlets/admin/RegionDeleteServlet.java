package com.example.lesson10tasks.servlets.admin;

import com.example.lesson10tasks.dao.RegionDAO;
import com.example.lesson10tasks.entity.Region;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson10tasks.servlets.admin.RegionDeleteServlet",
        urlPatterns = "/admin/delete/region/*"
)
public class RegionDeleteServlet extends HttpServlet {
    private final RegionDAO regionDAO = new RegionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/region_delete.jsp");
        req.setAttribute("region", regionDAO.findById(id));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        Region region = regionDAO.findById(id);
        if (Objects.isNull(region)) {
            resp.sendError(404, "Region '%s' not found".formatted(id));
        }
        regionDAO.deleteById(id);
        resp.sendRedirect("/region/list");
    }
}
