package com.wg.dabms.service;

import java.sql.SQLException;
import java.util.List;

import com.wg.dabms.dao.NotificationDAO;
import com.wg.dabms.model.Notification;

public class NotificationService {

    private NotificationDAO notificationDAO;

    public NotificationService() {
        this.notificationDAO = new NotificationDAO();
    }

    public boolean createNotification(Notification notification) {
        try {
            notificationDAO.createNotification(notification);
            return true;
        } catch (SQLException e) {
        	System.out.println("Failed in createNotification -> NotificationService");
        	return false;
        }
    }

    public boolean updateNotification(Notification notification) {
        try {
			return notificationDAO.updateNotification(notification);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
    }

    public boolean deleteNotification(String uuid) {
        try {
			return notificationDAO.deleteNotification(uuid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
    }

    public List<Notification> getNotificationsByReceiverId(String receiverId) {
        try {
			return notificationDAO.findByReceiverId(receiverId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block			
		}
		return null;
    }

    public List<Notification> getNotificationsByGeneratorId(String generatorId) {
        try {
			return notificationDAO.findByGeneratorId(generatorId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		return null;
    }
    
    public List<Notification> getNotificationsByGeneratorIdAndReceiverId(String generatorId, String receiverId) throws SQLException {
        return notificationDAO.findNotificationsByGeneratorIdAndReceiverId(generatorId, receiverId);
    }
}
