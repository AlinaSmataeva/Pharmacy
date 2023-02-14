package org.smataeva.finalproject.validation;

public class ValidationReg {
    public static final String REG_USERNAME = "(?=^.{4,15}$)^([a-zA-Z]+)$";
    public static final String REG_EMAIL = "(?=^.{5,25}$)^([a-zA-Z]+@[a-zA-Z]+.[a-zA-Z]+)$";
    public static final String REG_PHONE = "^\\+375 \\((17|29|33|44)\\) [0-9]{3}-[0-9]{2}-[0-9]{2})$";
    public static final String REG_AMOUNT = "(?=^.{1,}$)^([0-9]+)$";
    public static final String REG_PASSWORD = "(?=^.{6,}$)^([0-9]+)$^([A-Za-z0-9]+)$";
}