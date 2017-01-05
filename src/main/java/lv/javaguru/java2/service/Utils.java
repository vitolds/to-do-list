package lv.javaguru.java2.service;


import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    public static Timestamp convertStringToTimestamp(String stringToConvert,
                                                     String formatString) {

        SimpleDateFormat format = new SimpleDateFormat(formatString);

        try {
            Date date = format.parse(stringToConvert);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        } catch (ParseException e) {
            return null;
        }
    }
}
