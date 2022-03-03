package utils;

import java.util.Random;

public class CommonUtils {
    public static String genRandomEmailAddress() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char c = chars[rand.nextInt(chars.length)];
            sb.append(c);
        }
        return "test" + sb.toString() + "@gmail.com";
    }

    public static String generateRandomString(int stringLength) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random rnd = new Random();
        while (stringBuilder.length() <= stringLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }
}
