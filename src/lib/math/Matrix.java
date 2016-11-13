package lib.math;

/**
 * Matrix operations.
 * Created by meijun on 6/7/2014.
 */
public class Matrix {

    public static int[][] mul(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++)
                if (a[i][k] != 0) {
                    for (int j = 0; j < n; j++) {
                        c[i][j] = c[i][j] + a[i][k] * b[k][j];
                    }
                }
        }
        return c;
    }

    public static int[][] mul(int[][] a, int[][] b, int mod) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++)
                if (a[i][k] != 0) {
                    for (int j = 0; j < n; j++) {
                        c[i][j] = (int) ((c[i][j] + (long) a[i][k] * b[k][j]) % mod);
                    }
                }
        }
        return c;
    }

    public static long[][] mul(long[][] a, long[][] b) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++)
                if (a[i][k] != 0) {
                    for (int j = 0; j < n; j++) {
                        c[i][j] = c[i][j] + a[i][k] * b[k][j];
                    }
                }
        }
        return c;
    }

    public static long[][] mul(long[][] a, long[][] b, long mod) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++)
                if (a[i][k] != 0) {
                    for (int j = 0; j < n; j++) {
                        c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                    }
                }
        }
        return c;
    }

    public static double[][] mul(double[][] a, double[][] b) {
        int n = a.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++)
                if (a[i][k] != 0) {
                    for (int j = 0; j < n; j++) {
                        c[i][j] = c[i][j] + a[i][k] * b[k][j];
                    }
                }
        }
        return c;
    }

    public static int[][] pow(int[][] a, int b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a);
            a = mul(a, a);
            b >>>= 1;
        }
        return c;
    }

    public static int[][] pow(int[][] a, int b, int mod) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a, mod);
            a = mul(a, a, mod);
            b >>>= 1;
        }
        return c;
    }

    public static long[][] pow(long[][] a, long b) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a);
            a = mul(a, a);
            b >>>= 1;
        }
        return c;
    }

    public static long[][] pow(long[][] a, long b, long mod) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a, mod);
            a = mul(a, a, mod);
            b >>>= 1;
        }
        return c;
    }

    public static double[][] pow(double[][] a, long b) {
        int n = a.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a);
            a = mul(a, a);
            b >>>= 1;
        }
        return c;
    }
}
