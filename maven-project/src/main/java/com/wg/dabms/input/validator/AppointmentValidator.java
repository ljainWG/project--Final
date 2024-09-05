package com.wg.dabms.input.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class AppointmentValidator {
    private static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$"; // YYYY-MM-DD
    private static final String DATE_TIME_REGEX = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$"; // YYYY-MM-DDTHH:MM:SS

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public boolean validateScheduledDate(String scheduledDateStr) {
        if (Pattern.matches(DATE_REGEX, scheduledDateStr)) {
            LocalDate scheduledDate = LocalDate.parse(scheduledDateStr, dateFormatter);
            return !scheduledDate.isBefore(LocalDate.now());
        }
        return false;
    }

    public boolean validateBookingDate(String bookingDateStr) {
        if (Pattern.matches(DATE_TIME_REGEX, bookingDateStr)) {
            LocalDateTime bookingDate = LocalDateTime.parse(bookingDateStr, dateTimeFormatter);
            return !bookingDate.isAfter(LocalDateTime.now());
        }
        return false;
    }

    public boolean validateStatusUpdationDate(String statusUpdationDateStr) {
        if (Pattern.matches(DATE_TIME_REGEX, statusUpdationDateStr)) {
            LocalDateTime statusUpdationDate = LocalDateTime.parse(statusUpdationDateStr, dateTimeFormatter);
            return !statusUpdationDate.isAfter(LocalDateTime.now());
        }
        return false;
    }
}
