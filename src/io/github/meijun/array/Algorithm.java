package io.github.meijun.array;

import io.github.meijun.math.Rand;
import io.github.meijun.misc.function.IntBinaryProcedure;

import java.util.Comparator;
import java.util.function.IntPredicate;

/**
 * Some basic algorithm.
 * Created by meijun on 6/4/2016.
 */
public class Algorithm {

    public static void reverse(int[] a) {
        reverse(a, 0, a.length);
    }
    public static void reverse(long[] a) {
        reverse(a, 0, a.length);
    }
    public static void reverse(double[] a) {
        reverse(a, 0, a.length);
    }
    public static void reverse(char[] a) {
        reverse(a, 0, a.length);
    }
    public static <T> void reverse(T[] a) {
        reverse(a, 0, a.length);
    }

    public static void reverse(int[] a, int l, int r) {
        headTailProcess(l, r, (le, ri) -> swap(a, le, ri));
    }
    public static void reverse(long[] a, int l, int r) {
        headTailProcess(l, r, (le, ri) -> swap(a, le, ri));
    }
    public static void reverse(double[] a, int l, int r) {
        headTailProcess(l, r, (le, ri) -> swap(a, le, ri));
    }
    public static void reverse(char[] a, int l, int r) {
        headTailProcess(l, r, (le, ri) -> swap(a, le, ri));
    }
    public static <T> void reverse(T[] a, int l, int r) {
        headTailProcess(l, r, (le, ri) -> swap(a, le, ri));
    }

    public static void headTailProcess(int l, int r, IntBinaryProcedure procedure) {
        while (l < r) {
            procedure.process(l++, --r);
        }
    }

    public static void swap(int[] a, int u, int v) {
        int t = a[u];
        a[u] = a[v];
        a[v] = t;
    }
    public static void swap(long[] a, int u, int v) {
        long t = a[u];
        a[u] = a[v];
        a[v] = t;
    }
    public static void swap(double[] a, int u, int v) {
        double t = a[u];
        a[u] = a[v];
        a[v] = t;
    }
    public static void swap(char[] a, int u, int v) {
        char t = a[u];
        a[u] = a[v];
        a[v] = t;
    }
    public static <T> void swap(T[] a, int u, int v) {
        T t = a[u];
        a[u] = a[v];
        a[v] = t;
    }

    public static int count(int[] a, int v) {
        return count(a.length, i -> a[i] == v);
    }
    public static int count(long[] a, long v) {
        return count(a.length, i -> a[i] == v);
    }
    public static int count(double[] a, double v) {
        return count(a.length, i -> a[i] == v);
    }
    public static int count(char[] a, char v) {
        return count(a.length, i -> a[i] == v);
    }
    public static int count(boolean[] a, boolean v) {
        return count(a.length, i -> a[i] == v);
    }
    public static <T> int count(T[] a, T v) {
        return count(a.length, i -> a[i].equals(v));
    }

    public static int count(int n, IntPredicate tester) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (tester.test(i)) res++;
        }
        return res;
    }

    public static boolean contains(int[] a, int v) {
        return find(a, v) != -1;
    }
    public static boolean contains(long[] a, long v) {
        return find(a, v) != -1;
    }
    public static boolean contains(double[] a, double v) {
        return find(a, v) != -1;
    }
    public static boolean contains(char[] a, char v) {
        return find(a, v) != -1;
    }
    public static boolean contains(boolean[] a, boolean v) {
        return find(a, v) != -1;
    }
    public static <T> boolean contains(T[] a, T v) {
        return find(a, v) != -1;
    }

    public static int find(int[] a, int v) {
        return find(a.length, i -> a[i] == v);
    }
    public static int find(long[] a, long v) {
        return find(a.length, i -> a[i] == v);
    }
    public static int find(double[] a, double v) {
        return find(a.length, i -> a[i] == v);
    }
    public static int find(char[] a, char v) {
        return find(a.length, i -> a[i] == v);
    }
    public static int find(boolean[] a, boolean v) {
        return find(a.length, i -> a[i] == v);
    }
    public static <T> int find(T[] a, T v) {
        return find(a.length, i -> a[i].equals(v));
    }

    public static int find(int n, IntPredicate tester) {
        for (int i = 0; i < n; i++) {
            if (tester.test(i)) return i;
        }
        return -1;
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
    public static <T extends Comparable<T>> T[] unique(T[] a) {
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
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T[] unique(T[] a, int l, int r) {
        if (l == r) {
            return (T[]) new Object[0];
        }
        int cnt = 1;
        for (int i = l + 1; i < r; i++) {
            if (a[i].compareTo(a[i - 1]) != 0) {
                cnt++;
            }
        }
        T[] res = (T[]) new Object[cnt];
        res[0] = a[l];
        int id = 1;
        for (int i = l + 1; i < r; i++) {
            if (a[i].compareTo(a[i - 1]) != 0) {
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
    public static <T extends Comparable<T>> int lowerBound(T[] a, T v) {
        return lowerBound(a, 0, a.length, v);
    }
    public static <T> int lowerBound(T[] a, T v, Comparator<T> cmp) {
        return lowerBound(a, 0, a.length, v, cmp);
    }

    public static int lowerBound(int[] a, int l, int r, int v) {
        return binarySearch(l, r, m -> a[m] >= v);
    }
    public static int lowerBound(long[] a, int l, int r, long v) {
        return binarySearch(l, r, m -> a[m] >= v);
    }
    public static <T extends Comparable<T>> int lowerBound(T[] a, int l, int r, T v) {
        return binarySearch(l, r, m -> a[m].compareTo(v) >= 0);
    }
    public static <T> int lowerBound(T[] a, int l, int r, T v, Comparator<T> cmp) {
        return binarySearch(l, r, m -> cmp.compare(a[m], v) >= 0);
    }

    public static int upperBound(int[] a, int v) {
        return upperBound(a, 0, a.length, v);
    }
    public static int upperBound(long[] a, long v) {
        return upperBound(a, 0, a.length, v);
    }
    public static <T extends Comparable<T>> int upperBound(T[] a, T v) {
        return upperBound(a, 0, a.length, v);
    }
    public static <T> int upperBound(T[] a, T v, Comparator<T> cmp) {
        return upperBound(a, 0, a.length, v, cmp);
    }

    public static int upperBound(int[] a, int l, int r, int v) {
        return binarySearch(l, r, m -> a[m] > v);
    }
    public static int upperBound(long[] a, int l, int r, long v) {
        return binarySearch(l, r, m -> a[m] > v);
    }
    public static <T extends Comparable<T>> int upperBound(T[] a, int l, int r, T v) {
        return binarySearch(l, r, m -> a[m].compareTo(v) > 0);
    }
    public static <T> int upperBound(T[] a, int l, int r, T v, Comparator<T> cmp) {
        return binarySearch(l, r, m -> cmp.compare(a[m], v) > 0);
    }

    public static int binarySearch(int l, int r, IntPredicate tester) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (tester.test(m)) r = m;
            else l = m + 1;
        }
        return l;
    }
}
