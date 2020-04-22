package the.school.learning.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final String FULL_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd";


    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FULL_TIME_FORMATTER));
    }
}
