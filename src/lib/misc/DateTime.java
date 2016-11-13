package lib.misc;

import java.util.GregorianCalendar;

/**
 * DateTime Utils.
 * Created by meijun on 9/6/2014.
 */
public class DateTime {

    public static int[] ds = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static int days(int y, int m, int d) {
        m = (m + 9) % 12;
        y = y - m / 10;
        return 365 * y + y / 4 - y / 100 + y / 400 + (m * 306 + 5) / 10 + (d - 1);
    }

    public static int[] nextDay(int y, int m, int d) {
        if (d < ds[m]) return new int[]{y, m, d + 1};
        if (d == 28 && m == 2 && isLeapYear(y)) return new int[]{y, 2, 29};
        m++;
        if (m == 13) {
            m = 1;
            y++;
        }
        return new int[]{y, m, 1};
    }

    public static boolean isLeapYear(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }
}
