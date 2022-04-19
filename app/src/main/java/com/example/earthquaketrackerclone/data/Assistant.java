package com.example.earthquaketrackerclone.data;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class Assistant {

    //Assistant method, helps us to get a string resource without needing to context or activity
    public static String getStringResource (int stringId){
        return App.getContext().getResources().getString(stringId);
    }

    //Assistant method, helps us to get get a date after or before specific date
    public static Date getDateAfterDays(Date startDate, int noDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE,noDays);
        return calendar.getTime();
    }

    //Assistant method, helps us to get get a date in string with specific dateFormat after or before specific date
    //make sure that minus (-) and (+) is important
    public static String getFormatDateAfterDays(int noDaysFromToday, String dateFormat) {
        return new SimpleDateFormat(dateFormat, Locale.US).format(Assistant.getDateAfterDays(new Date(), noDaysFromToday));
    }

    public static String getFormattedDate(Date date, String dateFormat) {
        return new SimpleDateFormat(dateFormat, Locale.US).format(date);
    }


    public static long subtractDays(Date date){
        return Math.round((new Date().getTime()-date.getTime())/1000/60/60/24);
    }
}

