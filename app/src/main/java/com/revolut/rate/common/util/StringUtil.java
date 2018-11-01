package com.revolut.rate.common.util;

public class StringUtil {

    private StringUtil() {}

    public static String getValueWithoutLeadingZeroes(String value) {
        int idx = 0;

        while(idx < value.length() && value.charAt(idx) == '0') {
            idx++;
        }

        return (idx == value.length()) ? "0" :
                value.substring(getFirstDigitIndexBeforeDot(value, idx));
    }

    static int getFirstDigitIndexBeforeDot(String value, int idx) {
        if (value.charAt(idx) == '.') {
            return idx - 1;
        }

        return idx;
    }
}
