package io.github.meijun.array;

import java.util.function.*;

/**
 * Accumulate across array.
 * Created by meijun on 6/6/2016.
 */
public class Accumulate {

    public static int[] range(int n) {
        return range(0, n);
    }
    public static int[] range(int l, int r) {
        int[] a = new int[r - l];
        for (int i = 0; i < a.length; i++) {
            a[i] = l + i;
        }
        return a;
    }

    public static <T> int min(T[] a, ToIntFunction<T> mapper) {
        return accumulate(a, Integer.MAX_VALUE, mapper, Integer::min);
    }
    public static <T> long min(T[] a, ToLongFunction<T> mapper) {
        return accumulate(a, Long.MAX_VALUE, mapper, Long::min);
    }
    public static <T> double min(T[] a, ToDoubleFunction<T> mapper) {
        return accumulate(a, Double.POSITIVE_INFINITY, mapper, Double::min);
    }

    public static <T> int max(T[] a, ToIntFunction<T> mapper) {
        return accumulate(a, Integer.MIN_VALUE, mapper, Integer::max);
    }
    public static <T> long max(T[] a, ToLongFunction<T> mapper) {
        return accumulate(a, Long.MIN_VALUE, mapper, Long::max);
    }
    public static <T> double max(T[] a, ToDoubleFunction<T> mapper) {
        return accumulate(a, Double.NEGATIVE_INFINITY, mapper, Double::max);
    }

    public static <T> int sum(T[] a, ToIntFunction<T> mapper) {
        return accumulate(a, 0, mapper, Integer::sum);
    }
    public static <T> long sum(T[] a, ToLongFunction<T> mapper) {
        return accumulate(a, 0L, mapper, Long::sum);
    }
    public static <T> double sum(T[] a, ToDoubleFunction<T> mapper) {
        return accumulate(a, 0d, mapper, Double::sum);
    }

    public static int min(int[] a) {
        return accumulate(a, Integer.MAX_VALUE, Integer::min);
    }
    public static long min(long[] a) {
        return accumulate(a, Long.MAX_VALUE, Long::min);
    }
    public static double min(double[] a) {
        return accumulate(a, Double.POSITIVE_INFINITY, Double::min);
    }

    public static int max(int[] a) {
        return accumulate(a, Integer.MIN_VALUE, Integer::max);
    }
    public static long max(long[] a) {
        return accumulate(a, Long.MIN_VALUE, Long::max);
    }
    public static double max(double[] a) {
        return accumulate(a, Double.NEGATIVE_INFINITY, Double::max);
    }

    public static int sum(int[] a) {
        return accumulate(a, 0, Integer::sum);
    }
    public static long sum(long[] a) {
        return accumulate(a, 0, Long::sum);
    }
    public static double sum(double [] a) {
        return accumulate(a, 0, Double::sum);
    }

    public static int accumulate(int[] a, int init, IntBinaryOperator op) {
        int res = init;
        for (int ai : a) {
            res = op.applyAsInt(res, ai);
        }
        return res;
    }
    public static long accumulate(long[] a, long init, LongBinaryOperator op) {
        long res = init;
        for (long ai : a) {
            res = op.applyAsLong(res, ai);
        }
        return res;
    }
    public static double accumulate(double[] a, double init, DoubleBinaryOperator op) {
        double res = init;
        for (double ai : a) {
            res = op.applyAsDouble(res, ai);
        }
        return res;
    }


    public static <T> int accumulate(T[] a, int init, ToIntFunction<T> mapper, IntBinaryOperator op) {
        int res = init;
        for (T ai : a) {
            res = op.applyAsInt(res, mapper.applyAsInt(ai));
        }
        return res;
    }
    public static <T> long accumulate(T[] a, long init, ToLongFunction<T> mapper, LongBinaryOperator op) {
        long res = init;
        for (T ai : a) {
            res = op.applyAsLong(res, mapper.applyAsLong(ai));
        }
        return res;
    }
    public static <T> double accumulate(T[] a, double init, ToDoubleFunction<T> mapper, DoubleBinaryOperator op) {
        double res = init;
        for (T ai : a) {
            res = op.applyAsDouble(res, mapper.applyAsDouble(ai));
        }
        return res;
    }
    public static <T, R> R accumulate(T[] a, R init, Function<T, R> mapper, BinaryOperator<R> op) {
        R res = init;
        for (T ai : a) {
            res = op.apply(res, mapper.apply(ai));
        }
        return res;
    }

}
