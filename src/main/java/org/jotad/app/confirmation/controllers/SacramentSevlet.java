package org.jotad.app.confirmation.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.app.confirmation.models.Sacrament;
import org.jotad.app.confirmation.services.SacramentService;

import java.io.IOException;
import java.util.List;

@WebServlet({"/sacraments", "/index.html"})
public class SacramentSevlet extends HttpServlet {

    @Inject
    private SacramentService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Sacrament> sacraments = service.findAll("");
        req.setAttribute("sacraments", sacraments);
        getServletContext().getRequestDispatcher("/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Sacrament> sacraments = service.findAll(name);
        req.setAttribute("sacraments", sacraments);
        getServletContext().getRequestDispatcher("/list.jsp").forward(req,resp);
    }
}
