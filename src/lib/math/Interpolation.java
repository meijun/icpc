package lib.math;

/**
 * Interpolation of integer.
 * Created by meijun on 6/5/2016.
 */
public class Interpolation {
    private long[] y;
    private long[] a;
    private long mod;

    public Interpolation(long[] y, long mod) {
        this(y, mod, Factorial.invTable(y.length, mod));
    }

    public Interpolation(long[] y, long mod, long[] revFact) {
        this.y = y.clone();
        this.mod = mod;
        a = new long[y.length];
        for (int i = 0; i < y.length; i++) {
            a[i] = y[i] * revFact[i] % mod * revFact[y.length - i - 1] % mod;
            if (((y.length - i - 1) & 1) == 1) {
                a[i] = mod - a[i];
            }
        }
    }

    public long f(long x) {
        x %= mod;
        if (x < 0) {
            x += mod;
        }
        if (x < y.length) {
            return y[(int) x];
        }
        long prod = 1;
        for (int j = 0; j < y.length; j++) {
            prod *= x - j;
            prod %= mod;
        }
        long res = 0;
        for (int i = 0; i < y.length; i++) {
            res += a[i] * prod % mod * ModInv.inv(x - i, mod) % mod;
        }
        return res % mod;
    }
}
