package lib.math;

/**
 * Rand utils
 * Created by meijun on 6/4/2016.
 */
public class Rand {
    private static int a = 314159265;
    private static int b = 358979323;
    private static int c = 846264338;
    private static int d = 327950288;

    public static int rand() {
        int t = a ^ a << 11;
        a = b;
        b = c;
        c = d;
        return d = d ^ d >>> 19 ^ t ^ t >>> 8;
    }

    public static double rand1() {
        return (rand() & 0x7fffffff) * 0x1p-31;
    }

    public static int rand(int n) {
        return (int) (rand1() * n);
    }

    public static int rand(int l, int r) {
        return rand(r - l) + l;
    }

    public static boolean randBoolean() {
        return rand() < 0;
    }

    public static long randLong() {
        return (((long) rand()) << 32) | rand();
    }
}
