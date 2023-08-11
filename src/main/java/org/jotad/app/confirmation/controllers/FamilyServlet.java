package org.jotad.app.confirmation.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.app.confirmation.models.Family;
import org.jotad.app.confirmation.services.FamilyService;

import java.io.IOException;
import java.util.List;

@WebServlet("/families")
public class FamilyServlet extends HttpServlet {

    @Inject
    private FamilyService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Family> families = service.findAll("");
        req.setAttribute("families", families);
        getServletContext().getRequestDispatcher("/listFamily.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String motherName = req.getParameter("motherName");
        List<Family> families = service.findAll(motherName);
        req.setAttribute("families", families);
        getServletContext().getRequestDispatcher("/listFamily.jsp").forward(req,resp);
    }
}
