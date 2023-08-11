package org.jotad.app.confirmation.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository <T>{
    List<T> findAll(String text) throws SQLException;
    T byId(Integer id) throws SQLException;
    void save(T t) throws SQLException;
}
