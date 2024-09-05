package com.wg.dabms.menu;

import com.wg.dabms.constant.Constant;
import com.wg.dabms.controller.AppointmentController;
import com.wg.dabms.controller.NotificationController;
import com.wg.dabms.controller.PrescriptionController;
import com.wg.dabms.controller.ReviewController;
import com.wg.dabms.controller.UserController;
import com.wg.dabms.input.choice.ChoiceInputHandler;
import com.wg.dabms.model.User;

public class DoctorLauncher {
	private static UserController userController = new UserController();
	private static NotificationController notificationController = new NotificationController();
	private static AppointmentController appointmentController = new AppointmentController();
	private static PrescriptionController prescriptionController = new PrescriptionController();
	private static ReviewController reviewController = new ReviewController();

	public static void launchMenu(User user) {
		boolean condition = true;
		while (condition) {
			int choice = ChoiceInputHandler.getIntChoice(Constant.doctorMenu, 1, Constant.doctorMenuSize);
			switch (choice) {
			case 1:
				notificationController.readNotification(user);
				break;
			case 2:
				DoctorAppointmentLaunchMenu(user);
				break;
			case 3:
				DoctorPrescriptionLaunchMenu(user);
				break;
			case 4:
				DoctorReviewLaunchMenu(user);
				break;
			case 5:
				DoctorProfilelaunchMenu(user);
				break;
			case 6:
				condition = false;
				break;
			case 7:
				System.exit(0);
				break;
			}
		}
	}

	public static void DoctorAppointmentLaunchMenu(User user) {
		appointmentController.cancelAppointment(user);
	}

	public static void DoctorPrescriptionLaunchMenu(User user) {
		prescriptionController.createPrescription(user);
	}

	public static void DoctorReviewLaunchMenu(User user) {

	}

	public static void DoctorProfilelaunchMenu(User user) {

	}

}
