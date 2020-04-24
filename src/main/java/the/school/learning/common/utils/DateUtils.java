package the.school.learning.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateUtils {
    public static final String FULL_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd";


    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FULL_TIME_FORMATTER));
    }

    //计算出时间的秒数
    public static Long calTimeInterval(Date first, Date end) {
        return (end.getTime() - first.getTime()) / 1000;
    }

    //str -> Date
    public static Date parseDate(String dateStr, String dateFormat) {
        if (Objects.isNull(dateStr)) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(dateStr);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, FULL_TIME_FORMATTER);
    }
}
