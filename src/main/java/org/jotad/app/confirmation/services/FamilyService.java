package org.jotad.app.confirmation.services;

import org.jotad.app.confirmation.models.Family;

import java.util.List;
import java.util.Optional;

public interface FamilyService {
    List<Family> findAll(String text);
    Optional<Family> byId(Integer id);
    void save(Family family);
}
