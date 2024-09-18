package com.example.lesson10tasks.servlets.admin;

import com.example.lesson10tasks.dao.DistrictDAO;
import com.example.lesson10tasks.entity.District;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson10tasks.servlets.admin.DistrictDeleteServlet",
        urlPatterns = "/admin/delete/district/*"
)
public class DistrictDeleteServlet extends HttpServlet {
    private final DistrictDAO districtDAO = new DistrictDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/district_delete.jsp");
        req.setAttribute("district", districtDAO.findById(id));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        District district = districtDAO.findById(id);
        if (Objects.isNull(district)) {
            resp.sendError(404, "District '%s' not found".formatted(id));
        }
        districtDAO.deleteById(id);
        resp.sendRedirect("/district/list");
    }
}
