package io.github.meijun.math;

/**
 * Mod Inverse
 * Created by meijun on 6/6/2016.
 */
public class Inverse {

    public static long inv(long a, long mod) {
        a %= mod;
        if (a < 0) a += mod;
        return invRec(a, mod);
    }

    private static long invRec(long a, long mod) {
        if (a == 1) return 1;
        return invRec(mod % a, mod) * (mod - mod / a) % mod;
    }

    public static long[] table(int n, long mod) {
        long[] res = new long[n];
        if (n > 1) {
            res[1] = 1;
        }
        for (int i = 2; i < n; i++) {
            res[i] = (mod - mod / i * res[((int) (mod % i))] % mod) % mod;
        }
        return res;
    }
}
