package com.wg.dabms.input.handler;

import java.util.Scanner;

import com.wg.dabms.input.validator.NotificationValidator;

public class NotificationInputHandler {
	private static Scanner scanner = new Scanner(System.in);
	private static NotificationValidator validator = new NotificationValidator();

	public static String getValidatedTitle(String prompt) {
		String title;
		do {
			System.out.print(prompt);
			title = scanner.nextLine();
			if (!validator.validateTitle(title)) {
				System.out.println(
						"Invalid title. Please enter a non-empty title (1-100 characters) with valid characters.");
			}
		} while (!validator.validateTitle(title));
		return title;
	}

	public static String getValidatedDescription(String prompt) {
		String description;
		do {
			System.out.print(prompt);
			description = scanner.nextLine();
			if (!validator.validateDescription(description)) {
				System.out.println(
						"Invalid description. Please enter a non-empty description (1-255 characters) with valid characters.");
			}
		} while (!validator.validateDescription(description));
		return description;
	}
}
