package org.jotad.app.confirmation.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jotad.app.confirmation.configs.MysqlConn;
import org.jotad.app.confirmation.models.Family;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FamilyRepositoryImpl implements CrudRepository<Family> {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Family> findAll(String text) throws SQLException {
        List<Family> families = new ArrayList<>();
        try (PreparedStatement psmt = conn.prepareStatement("SELECT * FROM families WHERE mother_name LIKE ?")){
            psmt.setString(1, "%" + text + "%");
            try (ResultSet rs = psmt.executeQuery()){
                while (rs.next()){
                    Family family = getFamily(rs);
                    families.add(family);
                }
            }
        }
        return families;
    }

    @Override
    public Family byId(Integer id) throws SQLException {
        Family family = null;
        try (PreparedStatement psmt = conn.prepareStatement("SELECT * FROM families WHERE id=?")){
            psmt.setInt(1, id);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    family = getFamily(rs);
                }
            }
        }
        return family;
    }

    @Override
    public void save(Family family) throws SQLException {
        String sql;
        if (family.getId() != 0 && family.getId() > 0){
            sql = "UPDATE families SET father_name=?, father_phone=?, mother_name=?, mother_phone=? WHERE id=?";
        }else {
            sql = "INSERT INTO families (father_name, father_phone, mother_name, mother_phone) VALUES (?,?,?,?)";
        }
        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setString(1, family.getFatherName());
            psmt.setString(2, family.getFatherPhone());
            psmt.setString(3, family.getMotherName());
            psmt.setString(4, family.getMotherPhone());
            if (family.getId() != 0 && family.getId() > 0){
                psmt.setInt(5, family.getId());
            }
            psmt.executeUpdate();
        }
    }

    private static Family getFamily(ResultSet rs) throws SQLException {
        Family family = new Family();
        family.setId(rs.getInt("id"));
        family.setFatherName(rs.getString("father_name"));
        family.setFatherPhone(rs.getString("father_phone"));
        family.setMotherName(rs.getString("mother_name"));
        family.setMotherPhone(rs.getString("mother_phone"));
        return family;
    }
}
