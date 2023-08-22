package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataGenerate {
    public static String randomAlphabets(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static int randomNumbers(int length) {
        return Integer.parseInt(RandomStringUtils.randomNumeric(length));
    }

    public static String getRunDatetime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YY-HH-mm-ss-SSS");
        String testRunDateTime = dateFormat.format(new Date());
        return testRunDateTime;
    }
}
