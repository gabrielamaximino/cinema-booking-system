package helpers;

import java.util.Arrays;

public class Parser {

    public static  int size = 30;

    public static boolean[] parseToArray(int input){

        boolean[] bits = new boolean[Parser.size];

        System.out.println(input);

        for (int i = Parser.size; i >= 1; i--) {
            System.out.println((input & (1 << i-1)));
            bits[Parser.size-i] = (input & (1 << i-1)) != 0;
        }

        return bits;

    }

    public static int parseToInt(boolean[] bits){

        int n = 0;
        for (int i = 0; i < Parser.size; ++i) {
            n = (n << 1) + (bits[i] ? 1 : 0);
        }

        return n;

    }


}
