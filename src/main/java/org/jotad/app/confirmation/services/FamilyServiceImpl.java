package org.jotad.app.confirmation.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jotad.app.confirmation.models.Family;
import org.jotad.app.confirmation.repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FamilyServiceImpl implements FamilyService{

    @Inject
    private CrudRepository<Family> repository;

    @Override
    public List<Family> findAll(String text) {
        try {
            return repository.findAll(text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Family> byId(Integer id) {
        try {
            return Optional.ofNullable(repository.byId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Family family) {
        try {
            repository.save(family);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
