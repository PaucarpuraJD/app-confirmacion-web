package org.jotad.app.confirmation.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jotad.app.confirmation.models.Sacrament;
import org.jotad.app.confirmation.repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SacramentServiceImpl implements SacramentService{

    @Inject
    private CrudRepository<Sacrament> repository;

    @Override
    public List<Sacrament> findAll(String text) {
        try {
            return repository.findAll(text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Sacrament> byId(Integer id) {
        try {
            return Optional.ofNullable(repository.byId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Sacrament sacrament) {
        try {
            repository.save(sacrament);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
