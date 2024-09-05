package com.wg.dabms.input.choice;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChoiceInputHandler {
	private static Scanner scanner = new Scanner(System.in);

	public static int getIntChoice(String prompt, int start, int end) {
		System.out.println(prompt);
		System.out.println();
		System.out.println("Enter your Choice");
		while (true) {
			int choice;
			try {
				choice = scanner.nextInt();
				if (choice >= start && choice <= end) {
					return choice;
				} else {
					System.out.println(String.format("Enter a valid choice from %d to %d", start, end));
				}
			} catch (InputMismatchException e) {
				System.out.println(String.format("Enter an integer from %d to %d", start, end));
				scanner.next();
			}
		}
	}
}