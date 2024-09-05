package com.wg.dabms.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.wg.dabms.dao.AppointmentDAO;
import com.wg.dabms.input.choice.ChoiceInputHandler;
import com.wg.dabms.input.handler.AppointmentInputHandler;
import com.wg.dabms.input.handler.UserInputHandler;
import com.wg.dabms.model.Appointment;
import com.wg.dabms.model.User;
import com.wg.dabms.model.enums.AppointmentStatus;
import com.wg.dabms.model.enums.Department;
import com.wg.dabms.model.enums.Role;
import com.wg.dabms.model.enums.TimeSlot;
import com.wg.dabms.service.AppointmentService;
import com.wg.dabms.service.UserService;

public class AppointmentController {

	private static Scanner scanner = new Scanner(System.in);
	private AppointmentService appointmentService = new AppointmentService();
	private UserService userService = new UserService();
	private AppointmentDAO appointmentDAO = new AppointmentDAO();

	public void bookAppointment(User currentUser) {
		if (currentUser == null) {
			System.out.println("No user logged in.");
			return;
		}

		String patientId = currentUser.getRole() == Role.PATIENT ? currentUser.getUuid()
				: userService.findByEmail(UserInputHandler.getValidatedEmail("Enter patient email:")).getUuid();
		
		Department department = UserInputHandler.getInputDepartment("Enter the department you are looking for:");
		List<User> doctors = userService.findUserByDepartment(department);
		if (doctors.isEmpty()) {
			System.out.println("No doctors found in this department.");
			return;
		}

		UserController.printUserList(doctors);
		int doctorIndex = ChoiceInputHandler.getIntChoice("Enter the index number", 1, doctors.size());
		User doctor = doctors.get(doctorIndex-1);
		String doctorId = doctor.getUuid();
		LocalDate scheduledDate = AppointmentInputHandler.getInputDate("Enter scheduled date (yyyy-MM-dd):");
		if (scheduledDate == null)
			return;

		TimeSlot slotNumber = AppointmentInputHandler.getInputTimeSlot("Enter time slot number:");

		Appointment appointment = new Appointment();
		appointment.setUuid(UUID.randomUUID().toString());
		appointment.setDoctorId(doctorId);
		appointment.setPatientId(patientId);
		appointment.setStatus(AppointmentStatus.SCHEDULED);
		appointment.setScheduledDate(scheduledDate);
		appointment.setSlotNo(slotNumber);
		LocalDateTime time = LocalDateTime.now();
		appointment.setBookingDate(time);
		appointment.setStatusUpdationDate(time);

		appointmentService.createAppointment(appointment);
		System.out.println("Appointment booked successfully.");
	}
	
	public void cancelAppointment(User currentUser) {
	    if (currentUser == null) {
	        System.out.println("No user logged in.");
	        return;
	    }

	    List<Appointment> appointmentsToCancel = new ArrayList<>();

	    // For Patient or Doctor
	    if (currentUser.getRole() == Role.PATIENT || currentUser.getRole() == Role.DOCTOR) {
	        appointmentsToCancel = appointmentService.findAppointmentsByUserId(currentUser.getUuid(), AppointmentStatus.SCHEDULED);
	        if (appointmentsToCancel.isEmpty()) {
	            System.out.println("No scheduled appointments found.");
	            return;
	        }
	        printAppointmentList(appointmentsToCancel);
	        int appointmentIndex = ChoiceInputHandler.getIntChoice("Enter your choice",1, appointmentsToCancel.size());
	        Appointment appointment = appointmentsToCancel.get(appointmentIndex-1);
	        String appointmentId = appointment.getUuid();

	        appointmentService.cancelAppointment(appointmentId);

	    } else if (currentUser.getRole() == Role.RECEPTIONIST || currentUser.getRole() == Role.ADMIN) {
	        // Search for patients or doctors
	        String name = UserInputHandler.getValidatedUsername("Enter the name of the patient or doctor:");
	        List<User> users = userService.findUsersByName(name);
	        if (users.isEmpty()) {
	            System.out.println("No users found with that name.");
	            return;
	        }
	        UserController.printUserList(users);

	        int userIndex = ChoiceInputHandler.getIntChoice("Enter index of user",1,users.size());
	        User selectedUser = users.get(userIndex-1);
	        appointmentsToCancel = appointmentService.findAppointmentsByUserId(selectedUser.getUuid(), AppointmentStatus.SCHEDULED);
	        if (appointmentsToCancel.isEmpty()) {
	            System.out.println("No scheduled appointments found for this user.");
	            return;
	        }
	        printAppointmentList(appointmentsToCancel);
	        int appointmentIndex = ChoiceInputHandler.getIntChoice("Enter the appointment Index",1,appointmentsToCancel.size());
	        Appointment appointment = appointmentsToCancel.get(appointmentIndex-1);
	        String appointmentId = appointment.getUuid();
	        appointmentService.cancelAppointment(appointmentId);
	    }
	}

	private void printAppointmentList(List<Appointment> appointmentsToCancel) {
		System.out.printf("%-40s %-15s %-15s %-15s %-15s %-15s %-20s %-20s%n",
                "UUID", "Doctor ID", "Patient ID", "Status", "Scheduled Date", "Slot No", "Booking Date", "Status Update Date");

        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        // Loop through the list of appointments and print each appointment's details
        for (Appointment appointment : appointmentsToCancel) {
            System.out.printf("%-40s %-15s %-15s %-15s %-15s %-15s %-20s %-20s%n",
                    appointment.getUuid(), appointment.getDoctorId(), appointment.getPatientId(),
                    appointment.getStatus().name(), appointment.getScheduledDate(),
                    appointment.getSlotNo().name(), appointment.getBookingDate(), appointment.getStatusUpdationDate());
        }
	}
	
	public void viewAllAppointments() throws SQLException {
        List<Appointment> appointments = appointmentDAO.getAllAppointments();
        System.out.println("All Appointments:");
        printAppointmentList(appointments);
    }

    // Function to view all scheduled appointments
    public void viewScheduledAppointments() throws SQLException {
        List<Appointment> scheduledAppointments = appointmentDAO.findByUserId(null, AppointmentStatus.SCHEDULED);
        System.out.println("Scheduled Appointments:");
        printAppointmentList(scheduledAppointments);
    }

    // Function to view all cancelled appointments
    public void viewCancelledAppointments() throws SQLException {
        List<Appointment> cancelledAppointments = appointmentDAO.findByUserId(null, AppointmentStatus.CANCELED);
        System.out.println("Cancelled Appointments:");
        printAppointmentList(cancelledAppointments);
    }

}