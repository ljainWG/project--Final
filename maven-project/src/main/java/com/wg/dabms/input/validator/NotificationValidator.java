package com.wg.dabms.input.validator;

import java.util.regex.Pattern;

public class NotificationValidator {
    private static final String TITLE_REGEX = "^[a-zA-Z0-9 ,.?!]{1,100}$"; // Alphanumeric and some punctuation, 1-100 characters
    private static final String DESCRIPTION_REGEX = "^[a-zA-Z0-9 ,.?!]{1,255}$"; // Alphanumeric and some punctuation, 1-255 characters

    public boolean validateTitle(String title) {
        return Pattern.matches(TITLE_REGEX, title);
    }

    public boolean validateDescription(String description) {
        return Pattern.matches(DESCRIPTION_REGEX, description);
    }
}
