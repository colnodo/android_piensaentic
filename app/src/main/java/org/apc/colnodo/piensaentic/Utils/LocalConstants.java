package org.apc.colnodo.piensaentic.Utils;

import android.media.ExifInterface;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/7/16.
 */

public class LocalConstants {

    //Basic String


    //Intro pages tittles
    public static final String INTRO_ONE = "INTRO_ONE";
    public static final String INTRO_TWO = "INTRO_TWO";

    //Regular Expresions
    public static final String DATE_REGEX = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!$%@#£€*?&]{6,}$";
    public static final String SELECTION_SEPARATOR = ",";

    //Shared Preferences
    public static final String SHARED_PREFERENCE_CONTIGOAPP = "org.apc.colnodo.piensaentic";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_NICK_NAME = "USER_NICK_NAME";
    public static final String USER_BIRTHDATE = "USER_BIRTHDATE";
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String USER_PASS = "USER_PASS";
    public static final String DATA_TREATMENT_ACCEPTED = "DATA_TREATMENT_ACCEPTED";
    public static final String GENERATE_PASSWORD_ACCEPTED = "GENERATE_PASSWORD_ACCEPTED";
    public static final String INTRO_VIEWED = "INTRO_VIEWED";
    public static final String PHOTO_PATH = "PHOTO_PATH";
    public static final String POCKET_SELECTION = "POCKET_SELECTION";
    public static final String PHONE_SELECTION = "PHONE_SELECTION";
    public static final String IM_SPY_FIELDS_4 = "IM_SPY_FIELDS_4";


    public static final List<String> UNKOWN_CONTACT_FIELDS = new ArrayList<String>(){{
        add("UNKOWN_CONTACT_NAME_1");
        add("UNKOWN_CONTACT_NAME_2");
        add("UNKOWN_CONTACT_NICKNAME_1");
        add("UNKOWN_CONTACT_NICKNAME_2");
    }};

    public static final List<String> IM_SPY_FIELDS_1 = new ArrayList<String>(){{
        add("IM_SPY_FIELDS_1_PLATE");
        add("IM_SPY_FIELDS_1_CONTACT");
    }};

    public static final List<String> IM_SPY_FIELDS_2 = new ArrayList<String>(){{
        add("IM_SPY_FIELDS_2_FRECUENTED_SITES_1");
        add("IM_SPY_FIELDS_2_FRECUENTED_SITES_2");
        add("IM_SPY_FIELDS_2_FRECUENTED_SITES_3");
        add("IM_SPY_FIELDS_2_CONTACT");
    }};

    public static final List<String> IM_SPY_FIELDS_3 = new ArrayList<String>(){{
        add("IM_SPY_FIELDS_3_DNI");
        add("IM_SPY_FIELDS_3_CONTACT");
    }};


    public static final List<String> IM_SPY_FIELDS_TOTAL = new ArrayList<String>(){{
        add(IM_SPY_FIELDS_1.get(0));
        add(IM_SPY_FIELDS_1.get(1));
        add(IM_SPY_FIELDS_2.get(0));
        add(IM_SPY_FIELDS_2.get(1));
        add(IM_SPY_FIELDS_2.get(2));
        add(IM_SPY_FIELDS_2.get(3));
        add(IM_SPY_FIELDS_3.get(0));
        add(IM_SPY_FIELDS_3.get(1));
    }};

    //Activities fields


    //Dialog types
    public static final int TREATMENT_DIALOG = 1;
    public static final int CREATE_PASS_DIALOG = 2;
    public static final int NEEDS_INTERNET_CONECTION = 3;
    public static final int META_TAG_DIALOG = 4;


    //Image MetaTags

    public static final String PHOTO_NAME = "profile.jpg";
    public static final List<Pair<String, String>> META_TAG_LIST =new ArrayList<Pair<String, String>>() {{
        add(new Pair<>(ExifInterface.TAG_DATETIME, "Fecha y hora"));
        add(new Pair<>(ExifInterface.TAG_FLASH,"Flash" ));
        add(new Pair<>(ExifInterface.TAG_GPS_ALTITUDE, "Altitud"));
        add(new Pair<>(ExifInterface.TAG_GPS_LATITUDE, "Latitud"));
        add(new Pair<>(ExifInterface.TAG_GPS_LONGITUDE, "Longitud"));
        add(new Pair<>(ExifInterface.TAG_IMAGE_LENGTH, "Altura"));
        add(new Pair<>(ExifInterface.TAG_IMAGE_WIDTH, "Ancho"));
        add(new Pair<>(ExifInterface.TAG_MODEL, "Modelo"));
        add(new Pair<>(ExifInterface.TAG_ORIENTATION, "Orientación"));
        add(new Pair<>(ExifInterface.TAG_WHITE_BALANCE, "Balance de blancos"));
    }};
}
