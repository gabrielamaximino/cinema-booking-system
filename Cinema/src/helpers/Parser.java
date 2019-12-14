package helpers;

import java.util.Arrays;

public class Parser {

    public static boolean[] parseToArray(int input){

        boolean[] bits = new boolean[30];

        for (int i = 30; i >= 1; i--) {
            bits[30-i] = (input & (1 << i-1)) != 0;
        }

        return bits;

    }

    public static int parseToInt(boolean[] bits){

        int n = 0;
        for (int i = 0; i < 30; ++i) {
            n = (n << 1) + (bits[i] ? 1 : 0);
        }

        return n;

    }

}
