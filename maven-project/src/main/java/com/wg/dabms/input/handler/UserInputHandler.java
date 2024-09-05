package com.wg.dabms.input.handler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.wg.dabms.input.validator.UserValidator;
import com.wg.dabms.model.enums.Department;
import com.wg.dabms.model.enums.Gender;
import com.wg.dabms.model.enums.Role;

public class UserInputHandler {
	private static Scanner scanner = new Scanner(System.in);
	private static UserValidator validator = new UserValidator();
	
	public static String getValidatedUsername(String prompt) {
		String username;
		do {
			System.out.print(prompt);
			username = scanner.nextLine();
			if (!validator.validateUsername(username)) {
				System.out.println("Invalid username. Please enter a valid username.");
			}
		} while (!validator.validateUsername(username));
		return username;
	}

	public static String getValidatedPassword(String prompt) {
		String password;
		do {
			System.out.print(prompt);
			password = scanner.nextLine();
			if (!validator.validatePassword(password)) {
				System.out.println("Invalid password. Please enter a valid password.");
			}
		} while (!validator.validatePassword(password));
		return password;
	}

	public static String getValidatedEmail(String prompt) {
		String email;
		do {
			System.out.print(prompt);
			email = scanner.nextLine();
			if (!validator.validateEmail(email)) {
				System.out.println("Invalid email. Please enter a valid email address.");
			}
		} while (!validator.validateEmail(email));
		return email;
	}

	public static BigDecimal getInputBigDecimal(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return new BigDecimal(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}
	}

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

	public static Gender getInputGender(String prompt) {
		while (true) {
			System.out.print(prompt + " (MALE, FEMALE, OTHER): ");
			try {
				return Gender.valueOf(scanner.nextLine().toUpperCase());
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid gender. Please enter MALE, FEMALE, or OTHER.");
			}
		}
	}

	public static Role getInputRole(String prompt) {
		while (true) {
			System.out.print(prompt + " (DOCTOR, RECEPTIONIST, ADMIN, PATIENT): ");
			try {
				return Role.valueOf(scanner.nextLine().toUpperCase());
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid role. Please enter DOCTOR, RECEPTIONIST, ADMIN, PATIENT.");
			}
		}
	}

	public static Department getInputDepartment(String prompt) {
		while (true) {
			System.out.print(prompt
					+ " (CARDIOLOGY, NEUROLOGY, ORTHOPEDICS, PEDIATRICS, GENERAL_MEDICINE, DERMATOLOGY, GYNECOLOGY, ADMINISTRATION,\r\n"
					+ "	HOSPITALITY, NONE): ");
			try {
				return Department.valueOf(scanner.nextLine().toUpperCase().trim());
			} catch (IllegalArgumentException e) {
				System.out.println(
						"Invalid department. Please enter CARDIOLOGY, NEUROLOGY, ORTHOPEDICS, PEDIATRICS, GENERAL_MEDICINE, DERMATOLOGY, GYNECOLOGY, ADMINISTRATION,\r\n"
								+ "	HOSPITALITY, NONE");
			}
		}
	}

	public static String getInputPhone(String prompt) {
        System.out.println(prompt);
        while (true) {
            String input = scanner.nextLine();
            if (validator.isValidPhone(input)) {
                return input;
            } else {
                System.out.println("Invalid phone number format. Please enter in the format (123) 456-7890:");
            }
        }
    }

    public static String getInputAddress(String prompt) {
        System.out.println(prompt);
        while (true) {
            String input = scanner.nextLine();
            if (validator.isValidAddress(input)) {
                return input;
            } else {
                System.out.println("Invalid address. Please try again:");
            }
        }
    }

    public static BigDecimal getInputExperience(String prompt) {
        System.out.println(prompt);
        while (true) {
            if (scanner.hasNextBigDecimal()) {
                BigDecimal experience = scanner.nextBigDecimal();
                if (validator.isValidExperience(experience)) {
                    return experience;
                } else {
                    System.out.println("Experience must be a positive number. Please try again:");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value for experience:");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}
