package com.wg.dabms.input.handler;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.wg.dabms.model.enums.AppointmentStatus;
import com.wg.dabms.model.enums.TimeSlot;

public class AppointmentInputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static LocalDate getInputDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in the format YYYY-MM-DD.");
            }
        }
    }

    public static AppointmentStatus getInputAppointmentStatus(String prompt) {
        while (true) {
            System.out.print(prompt + " (SCHEDULED, COMPLETED, CANCELED, NO_SHOW_PATIENT, NO_SHOW_DOCTOR): ");
            try {
                return AppointmentStatus.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please enter SCHEDULED, COMPLETED, CANCELED, NO_SHOW_PATIENT, NO_SHOW_DOCTOR.");
            }
        }
    }

    public static TimeSlot getInputTimeSlot(String prompt) {
        while (true) {
            System.out.print(prompt + " (SLOT_8AM_830AM(1), SLOT_830AM_9AM(2), SLOT_9AM_930AM(3), SLOT_930AM_10AM(4), SLOT_10AM_1030AM(5),\r\n"
            		+ "	SLOT_1030AM_11AM(6), SLOT_11AM_1130AM(7), SLOT_1130AM_12PM(8), SLOT_12PM_1230PM(9), SLOT_1230PM_1PM(10),\r\n"
            		+ "	SLOT_2PM_230PM(11), SLOT_230PM_3PM(12), SLOT_3PM_330PM(13), SLOT_330PM_4PM(14), SLOT_4PM_430PM(15),\r\n"
            		+ "	SLOT_430PM_5PM(16), SLOT_5PM_530PM(17), SLOT_530PM_6PM(18), SLOT_6PM_630PM(19), SLOT_630PM_7PM(20)): ");
            try {
                return TimeSlot.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid time slot. Please enter SLOT_8AM_830AM(1), SLOT_830AM_9AM(2), SLOT_9AM_930AM(3), SLOT_930AM_10AM(4), SLOT_10AM_1030AM(5),\r\n"
                		+ "	SLOT_1030AM_11AM(6), SLOT_11AM_1130AM(7), SLOT_1130AM_12PM(8), SLOT_12PM_1230PM(9), SLOT_1230PM_1PM(10),\r\n"
                		+ "	SLOT_2PM_230PM(11), SLOT_230PM_3PM(12), SLOT_3PM_330PM(13), SLOT_330PM_4PM(14), SLOT_4PM_430PM(15),\r\n"
                		+ "	SLOT_430PM_5PM(16), SLOT_5PM_530PM(17), SLOT_530PM_6PM(18), SLOT_6PM_630PM(19), SLOT_630PM_7PM(20).");
            }
        }
    }
}
