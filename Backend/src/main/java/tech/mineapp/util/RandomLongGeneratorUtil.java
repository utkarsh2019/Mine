package tech.mineapp.util;

import java.util.Random;

/**
 * Generate an alphanumeric string to be
 * used for Ids and tokens and such
 *
 * @author amolmoses, utkarsh
 */
public class RandomLongGeneratorUtil {

	private final static Long lower = Long.parseLong("100000000000000");
	private final static Long upper = Long.parseLong("999999999999999");
    private final static Random random = new Random();

    /**
     * Generate a long of length 15
     */
    public static Long generateRandomLong() {  	
    	Long randomLong;
    	
    	do {
    		randomLong = random.nextLong();
    	} while (randomLong < lower || randomLong > upper);
        return randomLong;
    }
}
