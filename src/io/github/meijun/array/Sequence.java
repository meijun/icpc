package io.github.meijun.array;

import java.util.Arrays;

/**
 * Array Utility
 * <pre> {@code
 * concat   (int[] a, int[] b, int[] c)     int long double char T
 * concat   (int[] a, int[] b)              int long double char T
 * split    (int[] a, int l, int r)         int long double char T
 * fill     (int[]..6[] a, int v)           int long double char T
 * unbox    (Integer[] a):                  int long double char
 * box      (int[] a)                       int long double char
 * } </pre>
 * Created by meijun on 6/4/2016.
 */
public class Sequence {
    public static int[] concat(int[] a, int[] b, int[] c) {
        int[] r = new int[a.length + b.length + c.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        System.arraycopy(c, 0, r, a.length + b.length, c.length);
        return r;
    }
    public static long[] concat(long[] a, long[] b, long[] c) {
        long[] r = new long[a.length + b.length + c.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        System.arraycopy(c, 0, r, a.length + b.length, c.length);
        return r;
    }
    public static double[] concat(double[] a, double[] b, double[] c) {
        double[] r = new double[a.length + b.length + c.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        System.arraycopy(c, 0, r, a.length + b.length, c.length);
        return r;
    }
    public static char[] concat(char[] a, char[] b, char[] c) {
        char[] r = new char[a.length + b.length + c.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        System.arraycopy(c, 0, r, a.length + b.length, c.length);
        return r;
    }
    public static <T> T[] concat(T[] a, T[] b, T[] c) {
        //noinspection unchecked
        T[] r = (T[]) new Object[a.length + b.length + c.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        System.arraycopy(c, 0, r, a.length + b.length, c.length);
        return r;
    }

    public static int[] concat(int[] a, int[] b) {
        int[] r = new int[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }
    public static long[] concat(long[] a, long[] b) {
        long[] r = new long[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }
    public static double[] concat(double[] a, double[] b) {
        double[] r = new double[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }
    public static char[] concat(char[] a, char[] b) {
        char[] r = new char[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }
    public static <T> T[] concat(T[] a, T[] b) {
        //noinspection unchecked
        T[] r = (T[]) new Object[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }

    public static int[] split(int[] a, int l, int r) {
        return Arrays.copyOfRange(a, l, r);
    }
    public static long[] split(long[] a, int l, int r) {
        return Arrays.copyOfRange(a, l, r);
    }
    public static double[] split(double[] a, int l, int r) {
        return Arrays.copyOfRange(a, l, r);
    }
    public static char[] split(char[] a, int l, int r) {
        return Arrays.copyOfRange(a, l, r);
    }
    public static <T> T[] split(T[] a, int l, int r) {
        return Arrays.copyOfRange(a, l, r);
    }

    public static void fill(int[][][][][][] a, int v) {
        for (int[][][][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(int[][][][][] a, int v) {
        for (int[][][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(int[][][][] a, int v) {
        for (int[][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(int[][][] a, int v) {
        for (int[][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(int[][] a, int v) {
        for (int[] b : a) {
            fill(b, v);
        }
    }
    public static void fill(int[] a, int v) {
        Arrays.fill(a, v);
    }
    public static void fill(long[][][][][][] a, long v) {
        for (long[][][][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(long[][][][][] a, long v) {
        for (long[][][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(long[][][][] a, long v) {
        for (long[][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(long[][][] a, long v) {
        for (long[][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(long[][] a, long v) {
        for (long[] b : a) {
            fill(b, v);
        }
    }
    public static void fill(long[] a, long v) {
        Arrays.fill(a, v);
    }
    public static void fill(double[][][][][][] a, double v) {
        for (double[][][][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(double[][][][][] a, double v) {
        for (double[][][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(double[][][][] a, double v) {
        for (double[][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(double[][][] a, double v) {
        for (double[][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(double[][] a, double v) {
        for (double[] b : a) {
            fill(b, v);
        }
    }
    public static void fill(double[] a, double v) {
        Arrays.fill(a, v);
    }
    public static void fill(char[][][][][][] a, char v) {
        for (char[][][][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(char[][][][][] a, char v) {
        for (char[][][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(char[][][][] a, char v) {
        for (char[][][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(char[][][] a, char v) {
        for (char[][] b : a) {
            fill(b, v);
        }
    }
    public static void fill(char[][] a, char v) {
        for (char[] b : a) {
            fill(b, v);
        }
    }
    public static void fill(char[] a, char v) {
        Arrays.fill(a, v);
    }
    public static <T> void fill(T[][][][][][] a, T v) {
        for (T[][][][][] b : a) {
            fill(b, v);
        }
    }
    public static <T> void fill(T[][][][][] a, T v) {
        for (T[][][][] b : a) {
            fill(b, v);
        }
    }
    public static <T> void fill(T[][][][] a, T v) {
        for (T[][][] b : a) {
            fill(b, v);
        }
    }
    public static <T> void fill(T[][][] a, T v) {
        for (T[][] b : a) {
            fill(b, v);
        }
    }
    public static <T> void fill(T[][] a, T v) {
        for (T[] b : a) {
            fill(b, v);
        }
    }
    public static <T> void fill(T[] a, T v) {
        Arrays.fill(a, v);
    }

    public static int[] unbox(Integer[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
    public static long[] unbox(Long[] a) {
        long[] b = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
    public static double[] unbox(Double[] a) {
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
    public static char[] unbox(Character[] a) {
        char[] b = new char[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    public static Integer[] box(int[] a) {
        Integer[] b = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
    public static Long[] box(long[] a) {
        Long[] b = new Long[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
    public static Double[] box(double[] a) {
        Double[] b = new Double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
    public static Character[] box(char[] a) {
        Character[] b = new Character[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
}
