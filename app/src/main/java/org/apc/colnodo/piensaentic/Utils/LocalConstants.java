package org.apc.colnodo.piensaentic.Utils;

/**
 * Created by apple on 11/7/16.
 */

public class LocalConstants {

    public static final String DATE_REGEX = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!$%@#£€*?&]{6,}$";

    //Shared Preferences
    public static final String SHARED_PREFERENCE_CONTIGOAPP = "org.apc.colnodo.piensaentic";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_NICK_NAME = "USER_NICK_NAME";
    public static final String USER_BIRTHDATE = "USER_BIRTHDATE";
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String USER_PASS = "USER_PASS";
    public static final String DATA_TREATMENT_ACCEPTED = "DATA_TREATMENT_ACCEPTED";
    public static final String GENERATE_PASSWORD_ACCEPTED = "GENERATE_PASSWORD_ACCEPTED";

    //Activities fields


    //Dialog types
    public static final int TREATMENT_DIALOG = 1;
    public static final int CREATE_PASS_DIALOG = 2;
    public static final int NEEDS_INTERNET_CONECTION = 3;
}
