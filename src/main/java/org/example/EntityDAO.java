package org.example;


import com.crudapp.model.Entity;

import java.ql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityDAO {
    private static final String URL = "jdbc:sqlite:crudapp.db";

    public EntityDAO() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS entities (" +
                        "id TEXT PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "description TEXT," +
                        "createdAt TEXT," +
                        "updatedAt TEXT)";
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableSQL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEntity(Entity entity) {
        String sql = "INSERT INTO entities (id, name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entity.getId().toString());
            pstmt.setString(2, entity.getName());
            pstmt.setString(3, entity.getDescription());
            pstmt.setString(4, entity.getCreatedAt());
            pstmt.setString(5, entity.getUpdatedAt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entity> getAllEntities() {
        List<Entity> entities = new ArrayList<>();
        String sql = "SELECT * FROM entities";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String name = rs.getString("name");
                String description = rs.getString("description");
                String createdAt = rs.getString("createdAt");
                String updatedAt = rs.getString("updatedAt");
                entities.add(new Entity(id, name, description, createdAt, updatedAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public void updateEntity(Entity entity) {
        String sql = "UPDATE entities SET name = ?, description = ?, updatedAt = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getDescription());
            pstmt.setString(3, entity.getUpdatedAt());
            pstmt.setString(4, entity.getId().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEntity(UUID id) {
        String sql = "DELETE FROM entities WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
