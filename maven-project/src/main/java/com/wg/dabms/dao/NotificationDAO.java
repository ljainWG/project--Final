package com.wg.dabms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.dabms.model.Notification;

public class NotificationDAO extends GenericDAO<Notification> {

    @Override
    protected Notification mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Notification notification = new Notification();
        notification.setUuid(resultSet.getString("uuid"));
        notification.setGeneratorId(resultSet.getString("generator_id"));
        notification.setReceiverId(resultSet.getString("receiver_id"));
        notification.setTitle(resultSet.getString("title"));
        notification.setDescription(resultSet.getString("description"));
        notification.setGeneratedAt(resultSet.getTimestamp("generated_at").toLocalDateTime());
        notification.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return notification;
    }

    public boolean createNotification(Notification notification) throws SQLException {
        String query = "INSERT INTO Notification (uuid, generator_id, receiver_id, title, description, generated_at, updated_at) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(query, notification.getUuid(), notification.getGeneratorId(), notification.getReceiverId(),
                             notification.getTitle(), notification.getDescription(),
                             java.sql.Timestamp.valueOf(notification.getGeneratedAt()),
                             java.sql.Timestamp.valueOf(notification.getUpdatedAt()));
    }

    public boolean updateNotification(Notification notification) throws SQLException {
        String query = "UPDATE Notification SET generator_id = ?, receiver_id = ?, title = ?, description = ?, updated_at = ? WHERE uuid = ?";
        return executeUpdate(query, notification.getGeneratorId(), notification.getReceiverId(), notification.getTitle(),
                             notification.getDescription(), java.sql.Timestamp.valueOf(notification.getUpdatedAt()), notification.getUuid());
    }

    public boolean deleteNotification(String uuid) throws SQLException {
        String query = "DELETE FROM Notification WHERE uuid = ?";
        return executeDelete(query, uuid);
    }

    public Notification getNotificationById(String uuid) throws SQLException {
        String query = "SELECT * FROM Notification WHERE uuid = ?";
        return executeGetQuery(query, uuid);
    }

    public List<Notification> getAllNotifications() throws SQLException {
        String query = "SELECT * FROM Notification";
        return executeGetAllQuery(query);
    }
    public List<Notification> getAllNotificationsByReceiverId(String receiverId) throws SQLException {
        String query = "SELECT * FROM Notification WHERE receiver_id = ?";
        return executeGetAllQuery(query,receiverId);
    }
    
    public List<Notification> findByReceiverId(String receiverId) throws SQLException {
        String query = "SELECT * FROM Notification WHERE receiver_id = ?";
        return executeGetAllQuery(query, receiverId);
    }

    public List<Notification> findByGeneratorId(String generatorId) throws SQLException {
        String query = "SELECT * FROM Notification WHERE generator_id = ?";
        return executeGetAllQuery(query, generatorId);
    }
    public List<Notification> findNotificationsByGeneratorIdAndReceiverId(String generatorId, String receiverId) throws SQLException {
        String query = "SELECT * FROM Notification WHERE generator_id = ? AND receiver_id = ?";
        List<Notification> notifications = executeGetAllQuery(query, generatorId, receiverId);
        return notifications;
    }
}
