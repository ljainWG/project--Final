package com.wg.dabms.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.wg.dabms.input.choice.ChoiceInputHandler;
import com.wg.dabms.input.handler.NotificationInputHandler;
import com.wg.dabms.input.handler.UserInputHandler;
import com.wg.dabms.model.Notification;
import com.wg.dabms.model.User;
import com.wg.dabms.model.enums.Role;
import com.wg.dabms.service.NotificationService;
import com.wg.dabms.service.UserService;

public class NotificationController {

    private UserService userService = new UserService();
    private NotificationService notificationService = new NotificationService();
    private Scanner scanner = new Scanner(System.in);

    private String getUserIdByName(String prompt) {
        String username = UserInputHandler.getValidatedUsername(prompt);

        List<User> users = userService.findUsersByName(username);

        if (users.isEmpty()) {
            System.out.println("No users found with the given username.");
            return null;
        }

        // Print the list of users with the same name
        System.out.println("Select the user from the list below by index:");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println(i + ": " + user.getUsername() + " (ID: " + user.getUuid() + ")");
        }

        // Prompt user to select an index
        int index = ChoiceInputHandler.getIntChoice("Enter the index of the user you want to select: ", 1, users.size());

        return users.get(index - 1).getUuid();
    }

    private Notification createNotificationFromInput(User currentUser) {
        String generatorId = currentUser.getUuid();

        String receiverId = getUserIdByName("Enter receiver's username: ");
        if (receiverId == null) {
            System.out.println("User doesn't exist.");
            return null;
        }

        String title = NotificationInputHandler.getValidatedTitle("Enter notification title: ");
        String description = NotificationInputHandler.getValidatedDescription("Enter notification description: ");

        Notification notification = new Notification();
        notification.setUuid(java.util.UUID.randomUUID().toString());
        notification.setGeneratorId(generatorId);
        notification.setReceiverId(receiverId);
        notification.setTitle(title);
        notification.setDescription(description);
        notification.setGeneratedAt(java.time.LocalDateTime.now());
        notification.setUpdatedAt(notification.getGeneratedAt());

        return notification;
    }

    public void createNotification(User currentUser) {
        if (currentUser == null) return;
        if (currentUser.getRole().equals(Role.DOCTOR) || currentUser.getRole().equals(Role.PATIENT)) {
            System.out.println("You are not authorized to create notifications.");
            return;
        }

        if (currentUser.getRole().equals(Role.RECEPTIONIST) || currentUser.getRole().equals(Role.ADMIN)) {
            System.out.println("Creating a new notification:");

            Notification notification = createNotificationFromInput(currentUser);
            if (notification == null) return;

            boolean success = notificationService.createNotification(notification);
            System.out.println(success ? "Notification created successfully." : "Failed to create notification.");
        }
    }

    public void updateNotification(User currentUser) {
        String generatorId = getUserIdByName("Enter generator's username: ");
        if (generatorId == null) return;

        String receiverId = getUserIdByName("Enter receiver's username: ");
        if (receiverId == null) return;

        try {
            List<Notification> notifications = notificationService.getNotificationsByGeneratorIdAndReceiverId(generatorId, receiverId);
            if (notifications.isEmpty()) {
                System.out.println("No notifications found for the given generator and receiver.");
                return;
            }

            // Print notifications for user to choose which to update
            System.out.println("Select the notification to update by index:");
            for (int i = 0; i < notifications.size(); i++) {
                Notification notification = notifications.get(i);
                System.out.println(i + ": " + notification.getTitle() + " (ID: " + notification.getUuid() + ")");
            }

            int index = ChoiceInputHandler.getIntChoice("Enter the index of the notification you want to update: ", 0, notifications.size() - 1);
            Notification notification = notifications.get(index);

            System.out.print("Enter new title (leave blank to keep current): ");
            String title = scanner.nextLine();
            if (!title.isBlank()) {
                notification.setTitle(title);
            }

            System.out.print("Enter new description (leave blank to keep current): ");
            String description = scanner.nextLine();
            if (!description.isBlank()) {
                notification.setDescription(description);
            }

            notification.setUpdatedAt(java.time.LocalDateTime.now());

            boolean success = notificationService.updateNotification(notification);
            System.out.println(success ? "Notification updated successfully." : "Failed to update notification.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while updating the notification.");
        }
    }

    public void deleteNotification(User currentUser) {
        if (currentUser == null || !currentUser.getRole().equals(Role.ADMIN)) {
            System.out.println("Only admin can delete notifications.");
            return;
        }

        String generatorId = getUserIdByName("Enter generator's username: ");
        if (generatorId == null) return;

        String receiverId = getUserIdByName("Enter receiver's username: ");
        if (receiverId == null) return;

        try {
            List<Notification> notifications = notificationService.getNotificationsByGeneratorIdAndReceiverId(generatorId, receiverId);
            if (notifications.isEmpty()) {
                System.out.println("No notifications found for the given generator and receiver.");
                return;
            }

            // Print notifications for user to choose which to delete
            System.out.println("Select the notification to delete by index:");
            for (int i = 0; i < notifications.size(); i++) {
                Notification notification = notifications.get(i);
                System.out.println(i + ": " + notification.getTitle() + " (ID: " + notification.getUuid() + ")");
            }

            int index = ChoiceInputHandler.getIntChoice("Enter the index of the notification you want to delete: ", 0, notifications.size() - 1);
            Notification notification = notifications.get(index);

            boolean success = notificationService.deleteNotification(notification.getUuid());
            System.out.println(success ? "Notification deleted successfully." : "Failed to delete notification.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while deleting the notification.");
        }
    }

    public void getNotificationsByReceiverId(User currentUser) {
        String receiverId = getUserIdByName("Enter receiver's username: ");
        if (receiverId == null) return;

        List<Notification> notifications = notificationService.getNotificationsByReceiverId(receiverId);
		if (notifications.isEmpty()) {
		    System.out.println("No notifications found for the receiver.");
		    return;
		}
		printNotifications(notifications);
    }

    public void getNotificationsByGeneratorId(User currentUser) {
        String generatorId = getUserIdByName("Enter generator's username: ");
        if (generatorId == null) return;

        List<Notification> notifications = notificationService.getNotificationsByGeneratorId(generatorId);
		if (notifications.isEmpty()) {
		    System.out.println("No notifications found for the generator.");
		    return;
		}
		printNotifications(notifications);
    }

    private void printNotifications(List<Notification> notifications) {
        System.out.printf("%-36s %-36s %-36s %-20s %-20s %-20s %-20s\n", "UUID", "Generator ID", "Receiver ID", "Title", "Description", "Generated At", "Updated At");
        for (Notification notification : notifications) {
            System.out.printf("%-36s %-36s %-36s %-20s %-20s %-20s %-20s\n",
                    notification.getUuid(), notification.getGeneratorId(), notification.getReceiverId(),
                    notification.getTitle(), notification.getDescription(),
                    notification.getGeneratedAt(), notification.getUpdatedAt());
        }
    }
}
