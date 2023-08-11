package org.jotad.app.confirmation.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.app.confirmation.models.Confirmant;
import org.jotad.app.confirmation.models.Family;
import org.jotad.app.confirmation.models.Sacrament;
import org.jotad.app.confirmation.services.ConfirmantService;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/confirmants/form")
public class ConfirmantFormServlet extends HttpServlet {

    @Inject
    private ConfirmantService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0;
        }
        Confirmant confirmant = new Confirmant();
        confirmant.setSacrament(new Sacrament());
        confirmant.setFamily(new Family());

        if (id > 0){
            Optional<Confirmant> o = service.byId(id);
            if (o.isPresent()){
                confirmant = o.get();
            }
        }
        req.setAttribute("sacraments", service.findSacrament(""));
        req.setAttribute("families", service.findFamily(""));
        req.setAttribute("confirmant", confirmant);
        getServletContext().getRequestDispatcher("/formConfirmant.jsp").forward(req,resp);

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
        Integer sacramentId;
        try{
            sacramentId = Integer.valueOf(req.getParameter("sacrament"));
        }catch (NumberFormatException e){
            sacramentId = 0;
        }
        Integer familyId;
        try {
            familyId = Integer.valueOf(req.getParameter("family"));
        }catch (NumberFormatException e){
            familyId = 0;
        }
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String birthdateStr = req.getParameter("birthDate");

        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()){
            errors.put("name", "Name is required");
        }
        if (familyId.equals(0)){
            errors.put("family", "Family is required");
        }
        if (address == null || address.isBlank()){
            errors.put("address", "Address is required");
        }
        if (phone == null || phone.isBlank()) {
            errors.put("phone", "Phone is required");
        }
        if (birthdateStr == null || birthdateStr.isBlank()){
            errors.put("birthDate", "BirthDate is required");
        }

        LocalDate birthdat;
        try {
            birthdat = LocalDate.parse(birthdateStr,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (DateTimeException e){
            birthdat = null;
        }

        Confirmant confirmant = new Confirmant();
        confirmant.setId(id);
        confirmant.setName(name);
        Sacrament sacrament = new Sacrament();
        sacrament.setId(sacramentId);
        confirmant.setSacrament(sacrament);
        Family family = new Family();
        family.setId(familyId);
        confirmant.setFamily(family);
        confirmant.setAddress(address);
        confirmant.setPhone(phone);
        confirmant.setBirthDate(birthdat);

        if (errors.isEmpty()){
            service.save(confirmant);
            resp.sendRedirect(req.getContextPath() + "/confirmants");
        }else {
            req.setAttribute("errors", errors);
            req.setAttribute("sacraments", service.findSacrament(""));
            req.setAttribute("families", service.findFamily(""));
            req.setAttribute("confirmant", confirmant);
            getServletContext().getRequestDispatcher("/formConfirmant.jsp").forward(req,resp);
        }
    }
}
