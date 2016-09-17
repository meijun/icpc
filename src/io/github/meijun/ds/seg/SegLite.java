package io.github.meijun.ds.seg;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

/**
 * SegLite.
 * Created by meijun on 6/23/2014.
 */
public class SegLite {

    public final int init;
    public final IntBinaryOperator op;
    public final int N;
    public int[] is;

    public SegLite(int n, int init, IntBinaryOperator op) {
        this.init = init;
        this.op = op;
        N = Integer.highestOneBit(n) << 1;
        is = new int[N * 2];
        Arrays.fill(is, N, N + N, init);
        initialize();
    }

    public SegLite(int[] vs, int init, IntBinaryOperator op) {
        this.init = init;
        this.op = op;
        N = Integer.highestOneBit(vs.length) << 1;
        is = new int[N * 2];
        System.arraycopy(vs, 0, is, N, vs.length);
        initialize();
    }

    private void initialize() {
        for (int i = N - 1; i > 0; i--) {
            is[i] = op.applyAsInt(is[i << 1], is[i << 1 | 1]);
        }
    }

    public void update(int k, int v) {
        k += N;
        is[k] = v;
        for (k >>= 1; k > 0; k >>= 1) {
            is[k] = op.applyAsInt(is[k << 1], is[k << 1 | 1]);
        }
    }

    public int query(int s, int t) {
        int left = init;
        int right = init;
        while (0 < s && s + (s & -s) <= t) {
            int i = (N + s) / (s & -s);
            left = op.applyAsInt(left, is[i]);
            s += s & -s;
        }
        while (s < t) {
            int i = (N + t) / (t & -t) - 1;
            right = op.applyAsInt(is[i], right);
            t -= t & -t;
        }
        return op.applyAsInt(left, right);
    }
}
