package com.ixicode.constant.ixiplan.common.validation;


public class ValidationUtil
{
    public static boolean isValidEmail(String email)
    {
        return email.matches(ValidationConstants.REGEX_EMAIL_VALID);
    }
}
