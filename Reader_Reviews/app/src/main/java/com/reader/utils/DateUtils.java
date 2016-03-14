package com.reader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yurik on 26.02.16.
 */
public class DateUtils {

    public static final String DATE_SCHEME = "yyyy-MM-d";

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_SCHEME, Locale.getDefault());
        return dateFormat.format(date);
    }
}
