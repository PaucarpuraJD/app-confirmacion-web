package org.jotad.app.confirmation.services;

import org.jotad.app.confirmation.models.Sacrament;

import java.util.List;
import java.util.Optional;


public interface SacramentService {
    List<Sacrament> findAll(String text);
    Optional<Sacrament> byId(Integer id);
    void save(Sacrament sacrament);
}
