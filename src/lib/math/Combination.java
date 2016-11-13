package lib.math;

/**
 * Combination.
 * Created by meijun on 6/6/2016.
 */
public class Combination {

    /**
     * C(n, k) % p
     * <p> O(p + log n)</p>
     */
    public static int modPrime(int n, int k, int p) {
        return modPrime(n, k, p, Factorial.table(p, p));
    }

    /**
     * C(n, k) % p
     * <p> O(log n)</p>
     *
     * @param fact {@code Factorial.table(p, p)}
     */
    public static int modPrime(int n, int k, int p, int[] fact) {
        if (n < 0 || k < 0 || n < k) return 0;
        int e1 = Factorial.exponent(n, p), e2 = Factorial.exponent(k, p), e3 = Factorial.exponent(n - k, p);
        if (e1 > e2 + e3) return 0;
        int a1 = Factorial.coefficient(n, p, fact), a2 = Factorial.coefficient(k, p, fact), a3 = Factorial.coefficient(n - k, p, fact);
        return a1 * (int) ModInv.inv(a2 * a3 % p, p) % p;
    }

    public static long comb(int n, int m, long mod) {
        if (m < 0 || m > n)
            return 0;
        if (2 * m > n)
            m = n - m;
        long res = 1;
        for (int i = n - m + 1; i <= n; i++)
            res = res * i % mod;
        return res * ModInv.inv(Factorial.factorial(m, mod), mod) % mod;
    }

    public static long[][] table(int n) {
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][0] = 1;
            for (int j = 1; j <= i; j++)
                res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
        }
        return res;
    }

    public static long[][] table(int n, long mod) {
        long[][] res = new long[n][n];
        if (mod == 1)
            return res;
        for (int i = 0; i < n; i++) {
            res[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
                if (res[i][j] >= mod)
                    res[i][j] -= mod;
            }
        }
        return res;
    }

    public static double[][] doubleTable(int n) {
        double[][] res = new double[n][n];
        for (int i = 0; i < n; i++) {
            res[i][0] = 1;
            for (int j = 1; j <= i; j++)
                res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
        }
        return res;
    }

    public static long[] row(int n, long mod) {
        long[] res = ModInv.table(n, mod);
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * (n - i + 1) % mod * res[i] % mod;
        }
        return res;
    }

    public static double[] doubleRow(int n) {
        double[] res = new double[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * (n - i + 1) / i;
        }
        return res;
    }

    public static double[] doubleLogRow(int n) {
        double[] res = new double[n];
        res[0] = Math.log(1);
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + Math.log(n - i + 1) - Math.log(i);
        }
        return res;
    }
}
