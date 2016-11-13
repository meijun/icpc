package lib.ds.seg;

import java.util.Arrays;
import java.util.function.LongBinaryOperator;

/**
 * SegLite with long type.
 * Created by meijun on 6/23/2014.
 */
public class LongSegLite {

    public final long init;
    public final LongBinaryOperator op;
    public final int N;
    public long[] is;

    public LongSegLite(int n, long init, LongBinaryOperator op) {
        this.init = init;
        this.op = op;
        N = Integer.highestOneBit(n) << 1;
        is = new long[N * 2];
        Arrays.fill(is, N, N + N, init);
        for (int i = N - 1; i > 0; i--) {
            is[i] = this.op.applyAsLong(is[i << 1], is[i << 1 | 1]);
        }
    }

    public LongSegLite(long[] vs, long init, LongBinaryOperator op) {
        this.init = init;
        this.op = op;
        N = Integer.highestOneBit(vs.length) << 1;
        is = new long[N * 2];
        System.arraycopy(vs, 0, is, N, vs.length);
        for (int i = N - 1; i > 0; i--) {
            is[i] = this.op.applyAsLong(is[i << 1], is[i << 1 | 1]);
        }
    }

    public void update(int k, long v) {
        k += N;
        is[k] = v;
        for (k >>= 1; k > 0; k >>= 1) {
            is[k] = op.applyAsLong(is[k << 1], is[k << 1 | 1]);
        }
    }

    public long query(int s, int t) {
        long left = init;
        long right = init;
        while (0 < s && s + (s & -s) <= t) {
            int i = (N + s) / (s & -s);
            left = op.applyAsLong(left, is[i]);
            s += s & -s;
        }
        while (s < t) {
            int i = (N + t) / (t & -t) - 1;
            right = op.applyAsLong(is[i], right);
            t -= t & -t;
        }
        return op.applyAsLong(left, right);
    }
}
