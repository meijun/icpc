package lib.misc;

import lib.math.Rand;

import java.util.Arrays;

/**
 * Short cut.
 * Created by meijun on 6/6/2014.
 */
public class $ {
    public static void debug(Object... os) {
        System.err.println(Arrays.deepToString(os));
    }

    public static void reverse(int[] a) {
        reverse(a, 0, a.length);
    }

    public static void reverse(int[] a, int l, int r) {
        while (l < r) {
            swap(a, l++, --r);
        }
    }

    public static void swap(int[] a, int u, int v) {
        int t = a[u];
        a[u] = a[v];
        a[v] = t;
    }

    public static void radixSort(int[] a) {
        final int d = 8;
        final int w = 32;
        int[] t = new int[a.length];
        for (int p = 0; p < w / d; p++) {
            // counting-sort
            int[] cnt = new int[1 << d];
            for (int v : a) ++cnt[((v ^ Integer.MIN_VALUE) >>> (d * p)) & ((1 << d) - 1)];
            for (int i = 1; i < cnt.length; i++)
                cnt[i] += cnt[i - 1];
            for (int i = a.length - 1; i >= 0; i--)
                t[--cnt[((a[i] ^ Integer.MIN_VALUE) >>> (d * p)) & ((1 << d) - 1)]] = a[i];
            System.arraycopy(t, 0, a, 0, a.length);
        }
    }

    /**
     * move k-th element to {@code a[k - 1]}. average O({@code a.length})
     */
    public static void kthElement(int[] a, int l, int r, int k) {
        while (true) {
            int c = randomizedPartition(a, l, r);
            if (k < c)
                r = c;
            else if (k > c)
                l = c + 1;
            else
                return;
        }
    }

    public static int randomizedPartition(int[] a, int l, int r) {
        swap(a, Rand.rand(l, r), r - 1);
        int separator = a[r - 1];
        int i = l - 1;
        for (int j = l; j < r; j++)
            if (a[j] <= separator)
                swap(a, ++i, j);
        return i;
    }

    public static boolean nextPermutation(int[] a) {
        int n = a.length;
        for (int i = n - 1; i > 0; i--) {
            if (a[i - 1] < a[i]) {
                int j = n;
                //noinspection StatementWithEmptyBody
                while (a[i - 1] >= a[--j]) ;
                swap(a, i - 1, j);
                reverse(a, i, n);
                return true;
            }
        }
        reverse(a, 0, n);
        return false;
    }

    public static int[] unique(int[] a) {
        return unique(a, 0, a.length);
    }

    public static long[] unique(long[] a) {
        return unique(a, 0, a.length);
    }

    public static int[] unique(int[] a, int l, int r) {
        if (l == r) {
            return new int[0];
        }
        int count = 1;
        for (int i = l + 1; i < r; i++) {
            if (a[i] != a[i - 1]) {
                count++;
            }
        }
        int[] res = new int[count];
        res[0] = a[l];
        int id = 1;
        for (int i = l + 1; i < r; i++) {
            if (a[i] != a[i - 1]) {
                res[id++] = a[i];
            }
        }
        return res;
    }

    public static long[] unique(long[] a, int l, int r) {
        if (l == r) {
            return new long[0];
        }
        int count = 1;
        for (int i = l + 1; i < r; i++) {
            if (a[i] != a[i - 1]) {
                count++;
            }
        }
        long[] res = new long[count];
        res[0] = a[l];
        int id = 1;
        for (int i = l + 1; i < r; i++) {
            if (a[i] != a[i - 1]) {
                res[id++] = a[i];
            }
        }
        return res;
    }

    public static int lowerBound(int[] a, int v) {
        return lowerBound(a, 0, a.length, v);
    }

    public static int lowerBound(long[] a, long v) {
        return lowerBound(a, 0, a.length, v);
    }

    public static int lowerBound(int[] a, int l, int r, int v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (a[m] >= v) r = m;
            else l = m + 1;
        }
        return l;
    }

    public static int lowerBound(long[] a, int l, int r, long v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (a[m] >= v) r = m;
            else l = m + 1;
        }
        return l;
    }

    public static int upperBound(int[] a, int v) {
        return upperBound(a, 0, a.length, v);
    }

    public static int upperBound(long[] a, long v) {
        return upperBound(a, 0, a.length, v);
    }

    public static int upperBound(int[] a, int l, int r, int v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (a[m] > v) r = m;
            else l = m + 1;
        }
        return l;
    }

    public static int upperBound(long[] a, int l, int r, long v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (a[m] > v) r = m;
            else l = m + 1;
        }
        return l;
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

}
