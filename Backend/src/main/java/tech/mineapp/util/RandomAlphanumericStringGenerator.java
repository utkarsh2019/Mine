package tech.mineapp.util;

import java.util.Random;

/**
 * Generate an alphanumeric string to be
 * used for Ids and tokens and such
 *
 * @author amolmoses
 */
public class RandomAlphanumericStringGenerator {

    private final static String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private final static Random random = new Random();

    /**
     * Generate an alphanumeric string of a specified length
     */
    public static String generateAlphanumericString(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0;i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(
                    random.nextInt(ALPHANUMERIC.length())
            ));
        }

        return sb.toString();
    }
}
