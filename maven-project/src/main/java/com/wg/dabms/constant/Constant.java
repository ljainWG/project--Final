package com.wg.dabms.constant;

public class Constant {

	public static final String launchingMenu = """
			1. LogIn
			2. Register new patient
			3. Exit
			""";
	public static int launchingMenuSize = 3;

	public static final String adminMenu = """
			1. Manage notifications
			2. Manage Appointments
			3. Manage Prescriptions
			4. Manage Review
			5. Manage Users
			6. Manage Profile
			7. Logout
			8. Exit
			""";
	public static int adminMenuSize = 8;

	public static final String patientMenu = """
			1. View notifications
			2. Manage Appointments
			3. View Prescriptions
			4. Manage Review
			5. Manage Profile
			6. Delete Account
			7. Logout
			8. Exit
			""";
	public static int patientMenuSize = 8;

	public static final String doctorMenu = """
			1. View notifications
			2. Manage Appointments
			3. Manage Prescriptions
			4. Manage Review
			5. Manage Profile
			6. Logout
			7. Exit
			""";
	public static int doctorMenuSize = 7;

	public static final String receptionistMenu = """
			1. Manage notifications
			2. Manage Appointments
			3. View Prescriptions
			4. Manage Review
			5. Manage Profile
			6. Logout
			7. Exit
			""";
	public static int receptionistMenuSize = 7;
	
	public static final String adminNoticationMenu = """

			""";
	public static int adminNoticationMenuSize;
	
	public static final String receptionistNoticationMenu = """

			""";
	public static int receptionistNoticationMenuSize;
	
	public static final String adminAppointmentMenu = """
			1. Book Appointment
			2. Cancel Appointment
			3. View All Appointment
			4. View All Scheduled Appointment
			5. View All Cancelled Appointment
			6. View Appointment by User Name
			6. Go Back
			""";
	public static int adminAppointmentMenuSize = 6;
	
	public static final String patientAppointmentMenu = """
			1. Book Appointment
			2. Cancel Appointment
			3. View Scheduled Appointment
			4. View Cancelled Appointment
			5. View All Appointment
			6. Go Back
			""";
	public static int patientAppointmentMenuSize = 5;
	
	public static final String doctorAppointmentMenu = """

			""";
	public static int doctorAppointmentMenuSize;
	
	public static final String receptionistAppointmentMenu = """

			""";
	public static int receptionistAppointmentMenuSize;
	
	public static final String adminPrescriptionMenu = """

			""";
	public static int adminPrescriptionMenuSize;
	
	public static final String doctorPrescriptionMenu = """

			""";
	public static int doctorPrescriptionMenuSize;
	
	public static final String adminReviewtMenu = """

			""";
	public static int adminReviewMenuSize;
	
	public static final String patientReviewMenu = """

			""";
	public static int patientReviewMenuSize;
	
	public static final String doctorReviewMenu = """

			""";
	public static int doctorReviewMenuSize;
	
	public static final String receptionistReviewMenu = """

			""";
	public static int receptionistReviewMenuSize;

	public static final String allUsersManageProfileMenu = """
			1. See your profile
			2. Update your profile
			3. Go back
			4. Exit
			""";
	public static int allUsersManageProfileMenuSize = 4;

	public static final String ENTER_THE_INDEX_OF_USER_YOU_WANT_TO_UPDATE = "Enter the index of user you want to update";
	public static final String NO_USER_FOUND = "No user found";

}
