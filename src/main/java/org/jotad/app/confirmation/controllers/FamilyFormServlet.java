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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/families/form")
public class FamilyFormServlet extends HttpServlet {

    @Inject
    private FamilyService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0;
        }
        Family family = new Family();
        if (id > 0){
            Optional<Family> o = service.byId(id);
            if (o.isPresent()){
                family = o.get();
            }
        }
        req.setAttribute("family", family);
        getServletContext().getRequestDispatcher("/formFamily.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0;
        }
        String motherName = req.getParameter("motherName");
        String motherPhone = req.getParameter("motherPhone");
        String fatherName = req.getParameter("fatherName");
        String fatherPhone = req.getParameter("fatherPhone");

        Map<String, String> errors = new HashMap<>();
        if (motherName == null || motherName.isBlank()){
            errors.put("motherName", "motherName id Required");
        }
        if (motherPhone == null || motherPhone.isBlank()){
            errors.put("motherPhone", "motherPhone id Required");
        }

        Family family = new Family();
        family.setId(id);
        family.setMotherName(motherName);
        family.setMotherPhone(motherPhone);
        family.setFatherName(fatherName);
        family.setFatherPhone(fatherPhone);

        if (errors.isEmpty()){
            service.save(family);
            resp.sendRedirect(req.getContextPath() + "/families");
        }else {
            req.setAttribute("family", family);
            req.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/formFamily.jsp").forward(req,resp);
        }
    }
}
