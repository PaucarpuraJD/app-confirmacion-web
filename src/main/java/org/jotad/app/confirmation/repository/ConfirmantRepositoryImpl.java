package org.jotad.app.confirmation.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jotad.app.confirmation.configs.MysqlConn;
import org.jotad.app.confirmation.models.Confirmant;
import org.jotad.app.confirmation.models.Family;
import org.jotad.app.confirmation.models.Sacrament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConfirmantRepositoryImpl implements CrudRepository<Confirmant> {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Confirmant> findAll(String text) throws SQLException {
        List<Confirmant> confirmants = new ArrayList<>();
        String sql = "SELECT c.*, s.name as sacrament, f.mother_name, f.mother_phone " +
                "FROM confirmants AS c " +
                "INNER JOIN sacraments AS s ON c.sacrament_id=s.id " +
                "INNER JOIN families AS f ON c.family_id=f.id WHERE c.name LIKE ?";
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setString(1, "%" + text + "%");
            try (ResultSet rs = psmt.executeQuery()){
                while (rs.next()){
                    Confirmant confirmant = getConfirmant(rs);
                    confirmants.add(confirmant);
                }
            }
        }
        return confirmants;
    }

    @Override
    public Confirmant byId(Integer id) throws SQLException {
        Confirmant confirmant = null;
        String sql = "SELECT c.*, s.name as sacrament, f.mother_name, f.mother_phone " +
                "FROM confirmants AS c " +
                "INNER JOIN sacraments AS s ON c.sacrament_id=s.id " +
                "INNER JOIN families AS f ON c.family_id=f.id WHERE c.id=?";
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setInt(1, id);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    confirmant = getConfirmant(rs);
                }
            }
        }
        return confirmant;
    }

    @Override
    public void save(Confirmant confirmant) throws SQLException {
        String sql;
        if (confirmant.getId() != 0 && confirmant.getId() > 0){
            sql = "UPDATE confirmants SET name=?,sacrament_id=?,family_id=?,address=?,phone=?,birthdate=? WHERE id=?";
        }else {
            sql = "INSERT INTO confirmants(name,sacrament_id,family_id,address,phone,birthdate) VALUES(?,?,?,?,?,?)";
        }
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setString(1, confirmant.getName());
            psmt.setInt(2,confirmant.getSacrament().getId());
            psmt.setInt(3, confirmant.getFamily().getId());
            psmt.setString(4, confirmant.getAddress());
            psmt.setString(5, confirmant.getPhone());
            psmt.setDate(6, Date.valueOf(confirmant.getBirthDate()));
            if (confirmant.getId() != 0 && confirmant.getId() > 0){
                psmt.setInt(7, confirmant.getId());
            }
            psmt.executeUpdate();
        }
    }

    public static Confirmant getConfirmant(ResultSet rs) throws SQLException{
        Confirmant confirmant = new Confirmant();
        confirmant.setId(rs.getInt("id"));
        confirmant.setName(rs.getNString("name"));
        Sacrament sacrament = new Sacrament();
        sacrament.setId(rs.getInt("sacrament_id"));
        sacrament.setName(rs.getString("sacrament"));
        confirmant.setSacrament(sacrament);
        Family family = new Family();
        family.setId(rs.getInt("family_id"));
        family.setMotherName(rs.getString("mother_name"));
        family.setMotherPhone(rs.getString("mother_phone"));
        confirmant.setFamily(family);
        confirmant.setAddress(rs.getString("address"));
        confirmant.setPhone(rs.getString("phone"));
        confirmant.setBirthDate(rs.getDate("birthdate").toLocalDate());
        return confirmant;
    }
}
