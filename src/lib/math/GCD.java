package lib.math;

/**
 * GCD and LCM.
 * Created by meijun on 6/7/2014.
 */
public class GCD {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int c = a;
            a = b;
            b = c % b;
        }
        if (a < 0) a = -a;
        return a;
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long c = a;
            a = b;
            b = c % b;
        }
        if (a < 0) a = -a;
        return a;
    }

    public static int[] exGcd(int x, int y) {
        int a0 = 1, a1 = 0, b0 = 0, b1 = 1, t;
        while (y != 0) {
            t = a0 - x / y * a1; a0 = a1; a1 = t;
            t = b0 - x / y * b1; b0 = b1; b1 = t;
            t = x % y; x = y; y = t;
        }
        if (x < 0) {
            a0 = -a0;
            b0 = -b0;
            x = -x;
        }
        return new int[]{a0, b0, x};
    }

    public static long[] exGcd(long x, long y) {
        long a0 = 1, a1 = 0, b0 = 0, b1 = 1, t;
        while (y != 0) {
            t = a0 - x / y * a1; a0 = a1; a1 = t;
            t = b0 - x / y * b1; b0 = b1; b1 = t;
            t = x % y; x = y; y = t;
        }
        if (x < 0) {
            a0 = -a0;
            b0 = -b0;
            x = -x;
        }
        return new long[]{a0, b0, x};
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
