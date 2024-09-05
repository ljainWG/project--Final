package com.wg.dabms.menu;

import com.wg.dabms.constant.Constant;
import com.wg.dabms.controller.AppointmentController;
import com.wg.dabms.controller.NotificationController;
import com.wg.dabms.controller.PrescriptionController;
import com.wg.dabms.controller.ReviewController;
import com.wg.dabms.controller.UserController;
import com.wg.dabms.input.choice.ChoiceInputHandler;
import com.wg.dabms.model.User;

public class AdminLauncher {
	private static UserController userController = new UserController();
	private static NotificationController notificationController = new NotificationController();
	private static AppointmentController appointmentController = new AppointmentController();
	private static PrescriptionController prescriptionController = new PrescriptionController();
	private static ReviewController reviewController = new ReviewController();

	public static void launchMenu(User user) {
		boolean condition = true;
		while (condition) {
			int choice = ChoiceInputHandler.getIntChoice(Constant.adminMenu, 1, Constant.adminMenuSize);
			switch (choice) {
			case 1:
				AdminNotificationLaunchMenu(user);
				break;
			case 2:
				AdminAppointmentLaunchMenu(user);
				break;
			case 3:
				AdminPrescriptionLaunchMenu(user);
				break;
			case 4:
				AdminReviewLaunchMenu(user);
				break;
			case 5:
				AdminUserManagementLaunchMenu(user);
				break;
			case 6:
				AdminProfilelaunchMenu(user);
				break;
			case 7:
				condition = false;
				break;
			case 8:
				System.exit(0);
				break;
			}
		}
	}

	public static void AdminNotificationLaunchMenu(User user) {

	}

	public static void AdminAppointmentLaunchMenu(User user) {

	}

	public static void AdminPrescriptionLaunchMenu(User user) {

	}

	public static void AdminReviewLaunchMenu(User user) {

	}

	public static void AdminUserManagementLaunchMenu(User user) {

	}

	public static void AdminProfilelaunchMenu(User user) {

	}

}
