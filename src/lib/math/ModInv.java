package lib.math;

import lib.misc.$;

/**
 * ModInv.
 * Created by meijun on 6/6/2016.
 */
public class ModInv {

    public static long inv(long a, long mod) {
        long[] g = GCD.exGcd(a, mod);
        if (g[2] != 1) {
            throw new RuntimeException("GCD != 1: (a, mod, gcd) = " + a + ", " + mod + ", " + g[2]);
        }
        return (g[0] % mod + mod) % mod;
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
