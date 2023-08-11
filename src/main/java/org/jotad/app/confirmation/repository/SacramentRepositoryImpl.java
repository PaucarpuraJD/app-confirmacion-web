package org.jotad.app.confirmation.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jotad.app.confirmation.configs.MysqlConn;
import org.jotad.app.confirmation.models.Sacrament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SacramentRepositoryImpl implements CrudRepository<Sacrament> {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Sacrament> findAll(String text) throws SQLException {
        List<Sacrament> sacraments = new ArrayList<>();
            try (PreparedStatement psmt = conn.prepareStatement("SELECT * FROM sacraments WHERE name LIKE ?")){
               psmt.setString(1, "%" + text + "%");
               try (ResultSet rs = psmt.executeQuery()){
                   while (rs.next()){
                       Sacrament sacrament = getSacrament(rs);
                       sacraments.add(sacrament);
                   }
               }
            }
        return sacraments;
    }

    @Override
    public Sacrament byId(Integer id) throws SQLException {
        Sacrament sacrament = null;
        try (PreparedStatement psmt = conn.prepareStatement("SELECT * FROM sacraments WHERE id=?")){
            psmt.setInt(1, id);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    sacrament = getSacrament(rs);
                }
            }
        }
        return sacrament;
    }

    @Override
    public void save(Sacrament sacrament) throws SQLException {
        String sql;
        if (sacrament.getId() != null && sacrament.getId() > 0){
            sql= "UPDATE sacraments SET name=? WHERE id=?";
        }else {
            sql= "INSERT INTO sacraments(name) VALUES (?)";
        }

        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setString(1, sacrament.getName());
            if (sacrament.getId() != null && sacrament.getId() > 0){
                psmt.setInt(2, sacrament.getId());
            }
            psmt.executeUpdate();
        }
    }

    public static Sacrament getSacrament(ResultSet rs) throws SQLException{
        Sacrament sacrament = new Sacrament();
        sacrament.setId(rs.getInt("id"));
        sacrament.setName(rs.getString("name"));
        return sacrament;
    }
}
