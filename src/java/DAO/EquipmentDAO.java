package DAO;

import Models.Equipment;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class EquipmentDAO {
    private final DBConnection db;

    public EquipmentDAO(DBConnection db) {
        this.db = db;
    }

    private static final String INSERT_SQL =
        "INSERT INTO Equipment " +
        "(equipment_id, name, equipment_type, image_url, rental_price, damage_fee, status, description) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public boolean addEquipment(Equipment eq) {
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, eq.getName());
            ps.setString(3, eq.getEquipmentType());
            ps.setString(4, eq.getImageUrl());
            ps.setFloat(5, eq.getRentalPrice());
            ps.setFloat(6, eq.getDamageFee());
            ps.setString(7, eq.getStatus());
            ps.setString(8, eq.getDescription());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
