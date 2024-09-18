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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson10tasks.servlets.admin.DistrictUpdateServlet",
        urlPatterns = "/admin/update/district/*"
)
public class DistrictUpdateServlet extends HttpServlet {
    private final DistrictDAO districtDAO = new DistrictDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/district_update.jsp");
        req.setAttribute("district", districtDAO.findById(id));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate localDate = LocalDate.now();
        String name = req.getParameter("name");
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        District district = districtDAO.findById(id);
        if (Objects.isNull(district)) {
            resp.sendError(404, "District '%s' not found".formatted(id));
        }
        district.setName(Objects.requireNonNullElse(name, district.getName()));
        district.setUpdated_date(Objects.requireNonNullElse(localDate, district.getUpdated_date()));
        districtDAO.update(district);
        System.out.println("localDate: " + localDate);
        System.out.println("district: " + district);
        resp.sendRedirect("/district/list");
    }
}
