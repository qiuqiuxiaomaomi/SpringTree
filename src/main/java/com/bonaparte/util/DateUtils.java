package com.bonaparte.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangmingquan on 2018/8/29.
 */
public class DateUtils {
    public static final String MONTH_FORMAT = "yyyy-MM";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String NUMBER_DATETIME_FORMAT = "yyyyMMddHHmmssSSS";

    public static final String NUMBER_DATE = "yyyyMMdd";

    public static final long MONTH_LONG = 2651224907L;

    public static String date2string(Date date, String format) {
        if ((date == null) || (format == null))
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static Date string2date(String date, String... patterns) {
        try {
            return org.apache.commons.lang.time.DateUtils.parseDate(date, patterns);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date string2date(String date, String format) throws Exception {
        if ((date == null) || (format == null))
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }

    public static Date adjustDate(Date date, int days, int hours, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    public static Date adjustNullZeroDateField(Date date, Integer days, Integer hours, Integer minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (days != null)
            cal.add(Calendar.DAY_OF_MONTH, days);
        if (hours == null)
            cal.set(Calendar.HOUR_OF_DAY, 0);
        else
            cal.add(Calendar.HOUR_OF_DAY, hours);
        if (minutes == null)
            cal.set(Calendar.MINUTE, 0);
        else
            cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }
}
