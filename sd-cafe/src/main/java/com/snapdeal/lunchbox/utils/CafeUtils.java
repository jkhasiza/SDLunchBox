/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author himani
 */
public class CafeUtils {

private static char[] numArr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

// Implementing Fisher–Yates shuffle
static void shuffleArray(char[] ar) {
    Random rnd = new Random();
    for (int i = ar.length - 1; i > 0; i--) {
        int index = rnd.nextInt(i + 1);
        // Simple swap
        char a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
    }
}
    public static String randomString(char[] characterSet, int length) {
        Random random = new SecureRandom();
        shuffleArray(characterSet);
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            // picks a random index out of character set > random character
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }

    public static String generateOTP() {
        String randomStr = randomString(numArr, 4);
        String systemTime = System.nanoTime() % 100 + "";
        String randomOTP = randomStr + systemTime;
        return randomOTP;
    }

   

}
