package lib.math;

/**
 * Power.
 * Created by meijun on 6/6/2016.
 */
public class Power {
    public static long pow(long p, int e) {
        long res = 1;
        while (e != 0) {
            if ((e & 1L) != 0) res = res * p;
            p = p * p;
            e >>= 1;
        }
        return res;
    }

    public static long pow(long p, long e, long mod) {
        long res = 1;
        while (e != 0) {
            if ((e & 1L) != 0) res = res * p % mod;
            p = p * p % mod;
            e >>= 1;
        }
        return res % mod;
    }

    public static long[] powTable(long p, int n, long mod) {
        long[] res = new long[n + 1];
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            res[i] = res[i - 1] * p % mod;
        }
        return res;
    }
}
