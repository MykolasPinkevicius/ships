package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateStringFormatterTest {

    @Test
    void dateStringFormatter() {

        Date date = new GregorianCalendar(2019,8-1,9).getTime();
        Assertions.assertEquals("2019-08-09", DateStringFormatter.dateToStringWithFormat(date));
    }

}
