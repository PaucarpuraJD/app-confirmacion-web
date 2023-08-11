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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/sacraments/form")
public class SacramentFormServlet extends HttpServlet {

    @Inject
    private SacramentService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0;
        }
        Sacrament sacrament = new Sacrament();
        if (id > 0){
            Optional<Sacrament> o = service.byId(id);
            if (o.isPresent()){
                sacrament = o.get();
            }
        }
        req.setAttribute("sacrament", sacrament);
        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0;
        }
        String name = req.getParameter("name");

        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()){
            errors.put("name", "Name is required");
        }
        Sacrament sacrament = new Sacrament();
        sacrament.setId(id);
        sacrament.setName(name);

        if (errors.isEmpty()) {
            service.save(sacrament);
            resp.sendRedirect(req.getContextPath() + "/sacraments");
        }else {
            req.setAttribute("sacrament", sacrament);
            req.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
        }
    }
}
