package org.charlestech.fin.utils;

/*
 * @Description Number conversion utilities
 * @author Charles Chen
 * @date 14-1-23
 * @version 1.0
 */
public class NumberUtils {
    /* Define simplified and traditional Chinese number */
    public final static String SIM[] = {"零", "一", "二", "三", "四", "五", "六",
            "七", "八", "九"};
    public final static String TRD[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆",
            "柒", "捌", "玖"};
    public final static String[] SIM_LEVEL = new String[]{"亿", "万", "千", "百",
            "十"};
    public final static String[] TRD_LEVEL = new String[]{"億", "萬", "仟", "百",
            "十"};

	/* Define simplified and traditional Chinese number */

    /**
     * Convert number into Chinese in every bit
     *
     * @param num
     * @param type 0:Simplified Chinese,1:Traditional Chinese
     * @return Chinese number string
     */
    public static String numBitToCN(long num, int type) {
        if (type < 0 || type > 1) {
            return "Invalid Type!";
        }
        char[] nChars = String.valueOf(num).toCharArray();
        StringBuilder sb = new StringBuilder();
        // Convert number into Chinese in specified type
        switch (type) {
            case 0:
                for (char nChar : nChars)
                    sb.append(SIM[Integer.parseInt(String.valueOf(nChar))]);
                break;
            case 1:
                for (char nChar : nChars)
                    sb.append(TRD[Integer.parseInt(String.valueOf(nChar))]);
                break;
        }
        return sb.toString();
    }

    /**
     * Convert number into Chinese
     *
     * @param num
     * @param type 0:Simplified Chinese,1:Traditional Chinese
     * @return
     */
    public static String numToCN(long num, int type) {
        // Number must be less than 12 bits
        if (String.valueOf(Math.abs(num)).length() > 12)
            return "Too long!";
        String sym = ""; // The final Chinese number
        if (num < 0)
            sym += "负";
        num = Math.abs(num); // Absolute number
        // Use different method according to the length
        String numCN;
        if (String.valueOf(num).length() <= 4)
            numCN = numToCN_4(num, type);
        else
            numCN = numToCN_12(num, type);

        // Clear the 0 in the begining
        if (numCN.startsWith(SIM[0]) || numCN.startsWith(TRD[0]))
            numCN = numCN.substring(1);
        return sym + numCN;
    }

    /**
     * Convert number less than or equals 4 bits into Chinese
     *
     * @param num
     * @param type 0:Simplified Chinese,1:Traditional Chinese
     * @return
     */
    private static String numToCN_4(long num, int type) {
        if (type < 0 || type > 1)
            return "Invalid Type!";
        int len = String.valueOf(num).length();
        if (len > 4)
            return "Too Long!";

        StringBuilder sb = new StringBuilder(); // The final string builder
        String[] level;
        switch (type) {
            case 0:
                level = SIM_LEVEL;
                break;
            case 1:
                level = TRD_LEVEL;
                break;
        }
        boolean flag = true;
        // Conversion
        if (num / 1000 != 0) {
            sb.append(numBitToCN(num / 1000, type) + SIM_LEVEL[2]);
            num = num % 1000;
        }
        if (num / 100 != 0) {
            sb.append(numBitToCN(num / 100, type) + SIM_LEVEL[3]);
            num = num % 100;
        } else {
            sb.append(SIM[0]);
            flag = false;
        }
        if (num / 10 != 0) {
            sb.append(numBitToCN(num / 10, type) + SIM_LEVEL[4]);
            num = num % 10;
        } else if (flag) {
            sb.append(SIM[0]);
        }
        if (num != 0)
            sb.append(numBitToCN(num, type));
        return sb.toString();
    }

    /**
     * Convert number less than or equals 12 bits into Chinese
     *
     * @param num
     * @param type 0:Simplified Chinese,1:Traditional Chinese
     * @return
     */
    private static String numToCN_12(long num, int type) {
        if (type < 0 || type > 1)
            return "Invalid Type!";
        int len = String.valueOf(num).length();
        if (len > 12)
            return "Too Long!";
        // If length is less than 4, convert by numToCN_4
        if (len <= 4)
            return numToCN_4(num, type);

        StringBuilder sb = new StringBuilder(); // The final string builder
        int base = 1, level;
        long high = 0, low = 0;
        if (len > 4 && len <= 8) {
            base = 10000;
            level = 1;
        } else {
            base = 100000000;
            level = 0;
        }
        high = num / base; // High bit number
        low = num % base; // Low bit number

        switch (type) {
            case 0:
                sb.append(numToCN_4(high, type));
                sb.append(SIM_LEVEL[level]);
                sb.append(numToCN_12(low, type));
                break;
            case 1:
                sb.append(numToCN_4(high, type));
                sb.append(TRD_LEVEL[level]);
                sb.append(numToCN_12(low, type));
                break;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int num = -112121;
        System.out.println(numToCN(num, 0));
    }

}