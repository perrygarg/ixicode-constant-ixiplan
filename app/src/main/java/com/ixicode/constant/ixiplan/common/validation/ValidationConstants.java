package com.ixicode.constant.ixiplan.common.validation;


public interface ValidationConstants
{
    public int VALIDATION_FIELD_EMPTY = 1;
    public int VALIDATION_FIELD_INVALID = 2;

    /*** Regex ***/
    String REGEX_OTP = "([0-9]){6}";
    String REGEX_EMAIL_VALID = "^[A-Z0-9a-z\\._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";

    /*** Length ***/
    int MOBILE_NO_LENGTH = 10;
    int OTP_LENGTH = 6;
    int MIN_PASSWORD_LENGTH = 6;

}
