package utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

public class Utils {

    public static final long EPOCH_FROM = 1420074000; // January 1, 2015 1:00:00 AM
    public static final long EPOCH_FROM_SECOND = 1483232400; // January 1, 2017 1:00:00 AM
    public static final int DIFFERENCE = 212976000;
    public static final int TWO_WEEKS = 1209600;

    public static String getAlphaNumericString(int n) {
        // lower limit for LowerCase Letters
        int lowerLimit = 97;

        // lower limit for LowerCase Letters
        int upperLimit = 122;

        Random random = new Random();

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer(n);

        for (int i = 0; i < n; i++) {

            // take a random value between 97 and 122
            int nextRandomChar = lowerLimit
                    + (int) (random.nextFloat()
                    * (upperLimit - lowerLimit + 1));

            // append a character at the end of bs
            r.append((char) nextRandomChar);
        }

        // return the resultant string
        return r.toString();
    }


    public static Timestamp getRandomTimestamp() {
        var random = new Random();
        return Timestamp.from(Instant.ofEpochSecond(Utils.EPOCH_FROM + random.nextInt(Utils.DIFFERENCE)));
    }

    public static Timestamp getRandomTimestampFromSecondHalf() {
        var random = new Random();
        return Timestamp.from(Instant.ofEpochSecond(Utils.EPOCH_FROM_SECOND + random.nextInt(Utils.DIFFERENCE)));
    }

}
