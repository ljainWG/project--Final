package com.wg.dabms.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wg.dabms.constant.Constant;
import com.wg.dabms.input.choice.ChoiceInputHandler;
import com.wg.dabms.input.handler.UserInputHandler;
import com.wg.dabms.menu.AdminLauncher;
import com.wg.dabms.menu.DoctorLauncher;
import com.wg.dabms.menu.PatientLauncher;
import com.wg.dabms.menu.ReceptionistLauncher;
import com.wg.dabms.model.User;
import com.wg.dabms.model.enums.Department;
import com.wg.dabms.model.enums.Gender;
import com.wg.dabms.model.enums.Role;
import com.wg.dabms.service.UserService;
import com.wg.dabms.util.password.PasswordUtil;

public class UserController {

	public static UserService userService = new UserService();

	public static void LogIn() {

		String email = UserInputHandler.getValidatedEmail("Enter email");
		String password = UserInputHandler.getValidatedPassword("Enter password");

		User user = userService.findByEmail(email);

		if (user != null) {
			if (PasswordUtil.verifyPassword(password, user.getSalt(), user.getPassword())) {

				switch (user.getRole()) {
				case ADMIN:
					AdminLauncher.launchMenu(user);
					break;
				case PATIENT:
					PatientLauncher.launchMenu(user);
					break;
				case DOCTOR:
					DoctorLauncher.launchMenu(user);
					break;
				case RECEPTIONIST:
					ReceptionistLauncher.launchMenu(user);
					break;
				}
			}
		} else {
			System.out.println("Enter the valid credentials");
		}
	}

	public static void registerUser(User currentUser) {

		String name = UserInputHandler.getValidatedUsername("Enter your name");
		String salt = PasswordUtil.generateSalt();
		String hashedPassword = PasswordUtil.hashPassword(UserInputHandler.getValidatedPassword("Enter password"),
				salt);

		String email = UserInputHandler.getValidatedEmail("Enter email");

		LocalDate dob = UserInputHandler.getInputDate("Enter dob in format(yyyy-MM-dd)");

		Gender gender = UserInputHandler.getInputGender("Enter gender");
		Role role = Role.ADMIN;
		if (currentUser != null && currentUser.getRole() == Role.ADMIN) {
			role = UserInputHandler.getInputRole("Enter the role for new user");
		}
		Department deptt = role.equals(Role.DOCTOR) ? UserInputHandler.getInputDepartment("Enter the department")
				: (role.equals(Role.RECEPTIONIST) ? Department.HOSPITALITY
						: (role.equals(Role.ADMIN) ? Department.ADMINISTRATION : Department.NONE));
		String phoneNo = UserInputHandler.getInputPhone("Enter the phone number");
		String Address = UserInputHandler.getInputAddress("Enter the address");
		BigDecimal experience = null;
		BigDecimal noOfreviews = null;
		if (role.equals(Role.DOCTOR)) {
			experience = UserInputHandler.getInputExperience("Enter experience");
			noOfreviews = BigDecimal.ZERO;
		}

		User newUser = new User();
		newUser.setUuid(UUID.randomUUID().toString());
		newUser.setUsername(name);
		newUser.setSalt(salt);
		newUser.setPassword(hashedPassword);
		newUser.setEmail(email);
		newUser.setDob(dob);
		newUser.setGender(gender);
		newUser.setRole(role);
		newUser.setDeptartment(deptt);
		newUser.setPhoneNo(phoneNo);
		newUser.setAddress(Address);
		newUser.setExperience(experience);
		newUser.setNoOfReview(noOfreviews);
		LocalDateTime time = LocalDateTime.now();
		newUser.setCreatedAt(time);
		newUser.setUpdatedAt(time);

		userService.createUser(newUser);

	}

	public void deleteUser(User currentUser) {

		if (currentUser == null)
			return;
		if (currentUser.getRole().equals(Role.DOCTOR) || currentUser.getRole().equals(Role.RECEPTIONIST)) {
			System.out.println("You are not authorized to delete your account");
			return;
		}
		User toBeDeleted = null;
		if (currentUser.getRole().equals(Role.ADMIN)) {
			List<User> list = userService.findAllUsers();
			if (!list.isEmpty()) {
				printUserList(list);
				int choice = ChoiceInputHandler.getIntChoice(Constant.ENTER_THE_INDEX_OF_USER_YOU_WANT_TO_UPDATE, 1,
						list.size());
				toBeDeleted = list.get(choice - 1);
				if (toBeDeleted.getRole().equals(Role.ADMIN)) {
					System.out.println("ADMIN cannot be deleted");
				} else {
					userService.deleteUser(toBeDeleted.getUuid());
				}
			} else {
				System.out.println(Constant.NO_USER_FOUND);
			}

		} else if (currentUser.getRole().equals(Role.PATIENT)) {
			toBeDeleted = currentUser;
			userService.deleteUser(toBeDeleted.getUuid());
		}
	}

	public void updateUser(User currentUser) {
		if (currentUser == null)
			return;
		User toBeUpdated = null;
		List<User> list = new ArrayList<User>();
		if (currentUser.getRole().equals(Role.DOCTOR) || currentUser.getRole().equals(Role.RECEPTIONIST)
				|| currentUser.getRole().equals(Role.PATIENT)) {
			toBeUpdated = currentUser;
		} else if (currentUser.getRole().equals(Role.ADMIN)) {
			list = userService.findAllUsers();
			if (!list.isEmpty()) {
				printUserList(list);
				int choice = ChoiceInputHandler.getIntChoice(Constant.ENTER_THE_INDEX_OF_USER_YOU_WANT_TO_UPDATE, 1,
						list.size());
				toBeUpdated = list.get(choice - 1);
				if (toBeUpdated.getRole().equals(Role.ADMIN) && toBeUpdated.getUuid() != currentUser.getUuid()) {
					System.out.println("You cannot modify the admin");
				} else {
					userService.deleteUser(toBeUpdated.getUuid());
				}
			} else {
				System.out.println(Constant.NO_USER_FOUND);
			}
		}

		userService.updateUser(toBeUpdated.getUuid());
	}

//	 this is under working
	/*
	 * public void readUser(User user) { if (user == null) return; int max = 0; int
	 * choice; if (user.getRole().equals(Role.PATIENT)) {
	 * System.out.println(Constant.patientSeeUserMenu); max =
	 * Constant.patientSeeUserMenuSize; choice =
	 * ChoiceInputHandler.getIntChoice("Enter the index of user you want to update",
	 * 1, max); switch (choice) { case 1: break; case 2: break; case 3: break; case
	 * 4: break; case 5: break; } } if (user.getRole().equals(Role.DOCTOR)) {
	 * System.out.println(Constant.doctorSeeUserMenu); max =
	 * Constant.doctorSeeUserMenuSize; choice =
	 * ChoiceInputHandler.getIntChoice("Enter the index of user you want to update",
	 * 1, max); switch (choice) { case 1: break; case 2: break; case 3: break; case
	 * 4: break; case 5: break; case 6: break; } } if
	 * (user.getRole().equals(Role.RECEPTIONIST)) {
	 * System.out.println(Constant.receptionistSeeUserMenu); max =
	 * Constant.receptionistSeeUserMenuSize; choice =
	 * ChoiceInputHandler.getIntChoice("Enter the index of user you want to update",
	 * 1, max); switch (choice) { case 1: break; case 2: break; case 3: break; case
	 * 4: break; case 5: break; case 6: break; case 7: break; } } if
	 * (user.getRole().equals(Role.ADMIN)) {
	 * System.out.println(Constant.adminSeeUserMenu); max =
	 * Constant.adminSeeUserMenuSize; choice =
	 * ChoiceInputHandler.getIntChoice("Enter the index of user you want to update",
	 * 1, max); switch (choice) { case 1: break; case 2: break; case 3: break; case
	 * 4: break; case 5: break; case 6: break; case 7: break; case 8: break; case 9:
	 * break; case 10: break; } }
	 * 
	 * }
	 */
	public static void printUserList(List<User> list) {
		System.out.printf("%-40s %-20s %-20s %-30s %-10s %-15s %-20s %-15s %-20s %-10s %-10s %-10s %-15s %-20s %-20s%n",
				"UUID", "Username", "Salt", "Password", "Email", "Gender", "Role", "Department", "Phone No", "Address",
				"Avg Rating", "Reviews", "Experience", "DOB", "Created At", "Updated At");

		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		// Loop through the list of users and print each user's details
		for (User user : list) {
			System.out.printf(
					"%-40s %-20s %-20s %-30s %-10s %-15s %-20s %-15s %-20s %-10s %-10s %-10s %-15s %-20s %-20s%n",
					user.getUuid(), user.getUsername(), user.getSalt(), user.getPassword(), user.getEmail(),
					user.getGender().name(), user.getRole().name(), user.getDeptartment().name(), user.getPhoneNo(),
					user.getAddress(), user.getAvgRating(), user.getNoOfReview(), user.getExperience(), user.getDob(),
					user.getCreatedAt(), user.getUpdatedAt());
		}
	}
	
	public String getUserIdByName() {		
        
        String username = UserInputHandler.getValidatedUsername("Enter the username: ");
        List<User> users = userService.findUsersByName(username);
		if (users.isEmpty()) {
		    System.out.println("No users found with the given username.");
		    return null;
		}
		
		System.out.println("Select the user from the list below by index:");
		for (int i = 0; i < users.size(); i++) {
		    User user = users.get(i);
		    System.out.println(i+1 + ": " + user.getUsername() + " (ID: " + user.getUuid() + ")");
		}
		int index = ChoiceInputHandler.getIntChoice("Enter the index of the user you want to select: ",1,users.size());
		return users.get(index-1).getUuid();
    }

}