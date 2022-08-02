package com.canban.auth.cnst;

public class RegexConst {

    public static final String VALIDATE_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'* +/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static final String VALIDATE_PASSWORD = "^(?=.*?[\\W\\d\\p{IsAlphabetic}]).{8,}$";

}
