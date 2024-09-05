package com.wg.dabms.input.handler;
import java.util.Scanner;

import com.wg.dabms.input.validator.PrescriptionValidator;

public class PrescriptionInputHandler {
    private static Scanner scanner = new Scanner(System.in);
    static PrescriptionValidator validator = new PrescriptionValidator();

    public static String getValidatedDescription(String prompt) {
        String description;
        do {
            System.out.print(prompt);
            description = scanner.nextLine();
            if (!validator.validateDescription(description)) {
                System.out.println("Invalid description. Please enter a non-empty description (1-255 characters) with valid characters.");
            }
        } while (!validator.validateDescription(description));
        return description;
    }
}
