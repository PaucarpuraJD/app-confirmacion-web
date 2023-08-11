package org.jotad.app.confirmation.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jotad.app.confirmation.models.Confirmant;
import org.jotad.app.confirmation.models.Family;
import org.jotad.app.confirmation.models.Sacrament;
import org.jotad.app.confirmation.repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ConfirmantServiceImpl implements ConfirmantService{

    @Inject
    private CrudRepository<Confirmant> confirmantRepository;

    @Inject
    private CrudRepository<Sacrament> sacramentRepository;

    @Inject
    private CrudRepository<Family> familyRepository;

    @Override
    public List<Confirmant> findAll(String text) {
        try {
            return confirmantRepository.findAll(text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Confirmant> byId(Integer id) {
        try {
            return Optional.ofNullable(confirmantRepository.byId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Confirmant confirmant) {
        try {
            confirmantRepository.save(confirmant);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Sacrament> findSacrament(String text) {
        try {
            return sacramentRepository.findAll(text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Family> findFamily(String text) {
        try {
            return familyRepository.findAll(text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
