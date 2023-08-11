package org.jotad.app.confirmation.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.app.confirmation.models.Confirmant;
import org.jotad.app.confirmation.services.ConfirmantService;

import java.io.IOException;
import java.util.List;

@WebServlet("/confirmants")
public class ConfirmantServlet extends HttpServlet {

    @Inject
    private ConfirmantService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Confirmant> confirmants = service.findAll("");
        req.setAttribute("confirmants",confirmants);
        getServletContext().getRequestDispatcher("/listConfirmant.jsp").forward(req,resp);
   }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Confirmant> confirmants = service.findAll(name);
        req.setAttribute("confirmants", confirmants);
        getServletContext().getRequestDispatcher("/listConfirmant.jsp").forward(req,resp);
    }
}
