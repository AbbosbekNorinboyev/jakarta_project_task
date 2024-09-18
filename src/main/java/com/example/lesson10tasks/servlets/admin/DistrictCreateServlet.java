package com.example.lesson10tasks.servlets.admin;

import com.example.lesson10tasks.dao.DistrictDAO;
import com.example.lesson10tasks.dao.RegionDAO;
import com.example.lesson10tasks.entity.District;
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
        name = "com.example.lesson10tasks.servlets.admin.DistrictCreateServlet",
        value = "/admin/create/district"
)
@MultipartConfig
public class DistrictCreateServlet extends HttpServlet {
    private final DistrictDAO districtDAO = new DistrictDAO();
    private final RegionDAO regionDAO = new RegionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/district_create.jsp");
        req.setAttribute("regions", regionDAO.findAll());
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        String name = req.getParameter("name");
        int regionId = Integer.parseInt(req.getParameter("regionId"));
        System.out.println("regionId: " + regionId);
        Region region = regionDAO.findById(regionId);
        if (name.isBlank()) {
            errors.put("error_name", "Name is null");
        }
        if (!errors.isEmpty()) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/district_create.jsp");
            errors.forEach(req::setAttribute);
            requestDispatcher.forward(req, resp);
        }
        District district = District.childBuilder()
                .name(name)
                .region(region)
                .build();
        districtDAO.save(district);
        resp.sendRedirect("/district/list");
    }
}
