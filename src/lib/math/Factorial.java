package lib.math;

/**
 * Factorial.
 * Created by meijun on 6/6/2016.
 */
public class Factorial {

    public static long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++)
            res *= i;
        return res;
    }

    public static long factorial(int n, long mod) {
        long res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i % mod;
        return res % mod;
    }

    public static int factorial(int n, int mod) {
        long res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i % mod;
        return (int) (res % mod);
    }

    public static int[] table(int n, int mod) {
        int[] f = new int[n];
        if (n > 0) {
            f[0] = 1;
        }
        for (int i = 1; i < n; i++) {
            f[i] = (int) ((long) f[i - 1] * i % mod);
        }
        return f;
    }

    public static long[] table(int n, long mod) {
        long[] f = new long[n];
        if (n > 0) {
            f[0] = 1;
        }
        for (int i = 1; i < n; i++) {
            f[i] = f[i - 1] * i % mod;
        }
        return f;
    }

    public static long[] invTable(int n, long mod) {
        long[] f = ModInv.table(n, mod);
        if (n > 0) {
            f[0] = 1;
        }
        for (int i = 1; i < n; i++) {
            f[i] = f[i] * f[i - 1] % mod;
        }
        return f;
    }

    /**
     * n! = a * p<sup>e</sup>
     * <p>O(p + log n)</p>
     *
     * @return a modPrime p
     */
    public static int coefficient(int n, int p) {
        return coefficient(n, p, table(p, p));
    }

    /**
     * n! = a * p<sup>e</sup>
     * <p>O(log n)</p>
     *
     * @param fact {@code Factorial.table(p, p)}
     * @return a modPrime p
     */
    public static int coefficient(int n, int p, int[] fact) {
        if (n == 0) return 1;
        return coefficient(n / p, p) * (n / p % 2 != 0 ? p - fact[n % p] : fact[n % p]) % p;
    }

    /**
     * n! = a * m<sup>e</sup>
     * <p>O(log n)</p>
     *
     * @return e
     */
    public static int exponent(int n, int m) {
        int res = 0;
        for (int mm = m; mm <= n; mm *= m) {
            res += n / mm;
        }
        return res;
    }
}
