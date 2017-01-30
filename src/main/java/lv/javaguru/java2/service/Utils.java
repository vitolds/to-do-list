package lv.javaguru.java2.service;


import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    static final Timestamp NULL_TIMESTAMP = Timestamp.valueOf("2038-01-01 00:00:00.0");

    public static Timestamp convertStringToTimestamp(String stringToConvert,
                                                     String formatString) {

        SimpleDateFormat format = new SimpleDateFormat(formatString);

        try {
            Date date = format.parse(stringToConvert);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        } catch (ParseException e) {
            return NULL_TIMESTAMP;
        }
    }
}
