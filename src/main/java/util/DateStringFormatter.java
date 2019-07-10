package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringFormatter {
    public static String dateToStringWithFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
