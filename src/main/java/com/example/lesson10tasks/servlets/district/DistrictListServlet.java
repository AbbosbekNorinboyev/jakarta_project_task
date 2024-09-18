package com.example.lesson10tasks.servlets.district;

import com.example.lesson10tasks.dao.DistrictDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "com.example.lesson10tasks.servlets.DistrictListServlet",
        value = "/district/list"
)
public class DistrictListServlet extends HttpServlet {
    private final DistrictDAO districtDAO = new DistrictDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/district/district_list.jsp");
        req.setAttribute("districts", districtDAO.findAll());
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
