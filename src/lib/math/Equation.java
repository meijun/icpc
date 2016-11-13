package lib.math;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Equation.
 * Created by meijun on 6/6/2016.
 */
public class Equation {
    public static final double EPS = 1e-8;

    /**
     * Ax = B (mod M).
     */
    public static BigInteger[] congruence(BigInteger[] A, BigInteger[] B, BigInteger[] M) {
        BigInteger x = BigInteger.ZERO, m = BigInteger.ONE;
        for (int i = 0; i < A.length; i++) {
            BigInteger a = A[i].multiply(m), b = B[i].subtract(A[i].multiply(x)), d = a.gcd(M[i]);
            if (!b.mod(d).equals(BigInteger.ZERO)) return null;
            x = x.add(m.multiply(b.divide(d).multiply(a.divide(d).modInverse(M[i].divide(d))).mod(M[i].divide(d))));
            m = m.multiply(M[i].divide(d));
        }
        return new BigInteger[]{x.mod(m), m};
    }

    /**
     * Ax = B (mod M).
     */
    public static long[] congruence(long[] A, long[] B, long[] M) {
        long x = 0, m = 1;
        for (int i = 0; i < A.length; i++) {
            long a = A[i] * m, b = B[i] - A[i] * x, d = GCD.gcd(a, M[i]);
            if (b % d != 0) return null;
            x += m * (b / d * ModInv.inv(a / d, M[i] / d) % (M[i] / d));
            m *= M[i] / d;
        }
        return new long[]{(x % m + m) % m, m};
    }

    /**
     * Ax = B (mod M).
     */
    public static long[] congruence(long A, long B, long M) {
        long x = 0, m = 1;
        long a = A * m, b = B - A * x, d = GCD.gcd(a, M);
        if (b % d != 0) return null;
        x += m * (b / d * ModInv.inv(a / d, M / d) % (M / d));
        m *= M / d;
        return new long[]{x % m, m};
    }

    public static double[][] solutionSpace(double[][] A, double[] b) {
        int n = A.length, m = A[0].length;
        double[][] a = new double[n][m + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, a[i], 0, m);
            a[i][m] = b[i];
        }
        int[] id = new int[n + 1]; // 第 i 行的第一个非零元 1 所在的位置是 id[i]
        Arrays.fill(id, -1);
        int pi = 0; // 矩阵 A 的秩
        for (int pj = 0; pi < n && pj < m; pj++) {
            for (int i = pi + 1; i < n; i++) {
                if (Math.abs(a[i][pj]) > Math.abs(a[pi][pj])) {
                    double[] t = a[i];
                    a[i] = a[pi];
                    a[pi] = t;
                }
            }
            if (Math.abs(a[pi][pj]) < EPS) // 当前列已经全零
                continue;
            double inv = 1 / a[pi][pj]; // 如果取模运算，可以用大数模逆
            for (int j = 0; j <= m; j++) // 化主元为 1，可以优化
                a[pi][j] *= inv;
            for (int i = 0; i < n; i++)
                if (i != pi) {
                    double d = a[i][pj];
                    for (int j = 0; j <= m; j++) // 化当前列为 0，可以优化
                        a[i][j] -= d * a[pi][j];
                }
            id[pi++] = pj;
        }
        for (int i = pi; i < n; i++)
            if (Math.abs(a[i][m]) > EPS) // 增广矩阵的秩更大，无解
                return null;
        double[][] X = new double[1 + m - pi][m];
        for (int j = 0, k = 0; j < m; j++) {
            if (id[k] == j)
                X[0][j] = a[k++][m];
            else {
                for (int i = 0; i < k; i++)
                    X[1 + j - k][id[i]] = -a[i][j];
                X[1 + j - k][j] = 1;
            }
        }
        return X;
    }

    public static long[][] solutionSpace(long[][] A, long[] b, long mod) {
        int n = A.length, m = A[0].length;
        long[][] a = new long[n][m + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, a[i], 0, m);
            a[i][m] = b[i];
        }
        int[] id = new int[n + 1]; // 第 i 行的第一个非零元 1 所在的位置是 id[i]
        Arrays.fill(id, -1);
        int pi = 0; // 矩阵 A 的秩
        for (int pj = 0; pi < n && pj < m; pj++) {
            for (int i = pi + 1; i < n; i++) {
                if (Math.abs(a[i][pj]) > Math.abs(a[pi][pj])) {
                    long[] t = a[i];
                    a[i] = a[pi];
                    a[pi] = t;
                }
            }
            if (Math.abs(a[pi][pj]) < EPS) // 当前列已经全零
                continue;
            long inv = ModInv.inv(a[pi][pj], mod);
            for (int j = 0; j <= m; j++) // 化主元为 1，可以优化
                a[pi][j] = (a[pi][j] * inv) % mod;
            for (int i = 0; i < n; i++)
                if (i != pi) {
                    long d = a[i][pj];
                    for (int j = 0; j <= m; j++) // 化当前列为 0，可以优化
                        a[i][j] = (a[i][j] - d * a[pi][j] % mod) % mod;
                }
            id[pi++] = pj;
        }
        for (int i = pi; i < n; i++)
            if (Math.abs(a[i][m]) > EPS) // 增广矩阵的秩更大，无解
                return null;
        long[][] X = new long[1 + m - pi][m];
        for (int j = 0, k = 0; j < m; j++) {
            if (id[k] == j)
                X[0][j] = a[k++][m];
            else {
                for (int i = 0; i < k; i++)
                    X[1 + j - k][id[i]] = -a[i][j];
                X[1 + j - k][j] = 1;
            }
        }
        return X;
    }

    public static boolean[][] solutionSpace(boolean[][] A, boolean[] b) {
        int n = A.length, m = A[0].length;
        boolean[][] a = new boolean[n][m + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, a[i], 0, m);
            a[i][m] = b[i];
        }
        int[] id = new int[n + 1]; // 第 i 行的第一个非零元 1 所在的位置是 id[i]
        Arrays.fill(id, -1);
        int pi = 0; // 矩阵 A 的秩
        for (int pj = 0; pi < n && pj < m; pj++) {
            for (int i = pi + 1; i < n; i++) {
                if (a[i][pj] && !a[pi][pj]) {
                    boolean[] t = a[i];
                    a[i] = a[pi];
                    a[pi] = t;
                }
            }
            if (!a[pi][pj]) // 当前列已经全零
                continue;
            for (int i = 0; i < n; i++)
                if (i != pi) {
                    boolean d = a[i][pj];
                    for (int j = 0; j <= m; j++) // 化当前列为 0，可以优化
                        a[i][j] ^= d & a[pi][j];
                }
            id[pi++] = pj;
        }
        for (int i = pi; i < n; i++)
            if (a[i][m]) // 增广矩阵的秩更大，无解
                return null;
        boolean[][] X = new boolean[1 + m - pi][m];
        for (int j = 0, k = 0; j < m; j++) {
            if (id[k] == j)
                X[0][j] = a[k++][m];
            else {
                for (int i = 0; i < k; i++)
                    X[1 + j - k][id[i]] = a[i][j];
                X[1 + j - k][j] = true;
            }
        }
        return X;
    }
}
