package org.jotad.app.confirmation.services;

import org.jotad.app.confirmation.models.Confirmant;
import org.jotad.app.confirmation.models.Family;
import org.jotad.app.confirmation.models.Sacrament;

import java.util.List;
import java.util.Optional;

public interface ConfirmantService {
    List<Confirmant> findAll(String text);
    Optional<Confirmant> byId(Integer id);
    void save(Confirmant confirmant);
    List<Sacrament> findSacrament(String text);
    List<Family> findFamily(String text);
}
