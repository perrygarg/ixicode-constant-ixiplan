package com.ixicode.constant.ixiplan.common.validation;

/**
 * Created by jaswinder.singh on 12/9/2016.
 */

public class ValidationUtil
{
    public static boolean isValidEmail(String email)
    {
        return email.matches(ValidationConstants.REGEX_EMAIL_VALID);
    }
}
