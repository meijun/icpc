package io.github.meijun.array;

/**
 * cast to int long double
 * Created by meijun on 6/6/2016.
 */
public class Cast {

    public static int[] l2i(long[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = (int) a[i];
        }
        return b;
    }
    public static int[] d2i(double[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = (int) a[i];
        }
        return b;
    }

    public static long[] i2l(int[] a) {
        long[] b = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
    public static long[] d2l(double[] a) {
        long[] b = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = (long) a[i];
        }
        return b;
    }

    public static double[] i2d(int[] a) {
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
    public static double[] l2d(long[] a) {
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    public static int[] d2i(double[] a, double delta) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = (int) (a[i] + delta);
        }
        return b;
    }
    public static long[] d2l(double[] a, double delta) {
        long[] b = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = (long) (a[i] + delta);
        }
        return b;
    }
}
