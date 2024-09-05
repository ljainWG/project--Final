package com.wg.dabms.input.validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
	private static final String USERNAME_REGEX = "^[a-zA-Z\\s]+$";
	private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$*]).{8,}$";
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|watchguard\\.com|outlook\\.com)$";
	private static final String PHONE_REGEX = "^\\+?[0-9]{10}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s]*$");

	public boolean validateUsername(String username) {
		return Pattern.matches(USERNAME_REGEX, username);
	}

	public boolean validatePassword(String password) {
		return Pattern.matches(PASSWORD_REGEX, password);
	}

	public boolean validateEmail(String email) {
		return Pattern.matches(EMAIL_REGEX, email);
	}

	public boolean validatePhoneNo(String phoneNo) {
		return Pattern.matches(PHONE_REGEX, phoneNo);
	}

	public boolean isValidPhone(String phone) {
		Matcher matcher = PHONE_PATTERN.matcher(phone);
		return matcher.matches();
	}

	public boolean isValidAddress(String address) {
		// Simple validation: non-empty and reasonable length
		return address.length() >= 5 && address.length() <= 255 && ADDRESS_PATTERN.matcher(address).matches();
	}

	public boolean isValidExperience(BigDecimal experience) {
		return experience != null && experience.compareTo(BigDecimal.ZERO) > 0;
	}
}
