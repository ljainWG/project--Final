package com.wg.dabms.menu;

import com.wg.dabms.constant.Constant;
import com.wg.dabms.controller.AppointmentController;
import com.wg.dabms.controller.NotificationController;
import com.wg.dabms.controller.PrescriptionController;
import com.wg.dabms.controller.ReviewController;
import com.wg.dabms.controller.UserController;
import com.wg.dabms.input.choice.ChoiceInputHandler;
import com.wg.dabms.model.User;

public class PatientLauncher {
	private static UserController userController = new UserController();
	private static NotificationController notificationController = new NotificationController();
	private static AppointmentController appointmentController = new AppointmentController();
	private static PrescriptionController prescriptionController = new PrescriptionController();
	private static ReviewController reviewController = new ReviewController();

	public static void launchMenu(User user) {
		boolean condition = true;
		while (condition) {
			int choice = ChoiceInputHandler.getIntChoice(Constant.patientMenu, 1, Constant.patientMenuSize);
			switch (choice) {
			case 1:
				notificationController.readNotification(user);
				break;
			case 2:
				PatientAppointmentLaunchMenu(user);
				break;
			case 3:
				prescriptionController.readPrescription(user);
				break;
			case 4:
				PatientReviewLaunchMenu(user);
				break;
			case 5:
				PatientProfilelaunchMenu(user);
				break;
			case 6:
				userController.deleteUser(user);
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

	public static void PatientAppointmentLaunchMenu(User user) {
		boolean condition = true;
		while (condition) {
			int choice = ChoiceInputHandler.getIntChoice(Constant.patientAppointmentMenu, 1, Constant.patientAppointmentMenuSize);
			switch (choice) {
			case 1:
				appointmentController.bookAppointment(user);
				break;
			case 2:
				PatientAppointmentLaunchMenu(user);
				break;
			case 3:
				prescriptionController.readPrescription(user);
				break;
			case 4:
				PatientReviewLaunchMenu(user);
				break;
			case 5:
				PatientProfilelaunchMenu(user);
				break;
			case 6:
				userController.deleteUser(user);
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

	public static void PatientReviewLaunchMenu(User user) {

	}

	public static void PatientProfilelaunchMenu(User user) {

	}

}
