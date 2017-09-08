package lib.ds.seg;

import java.util.Arrays;

/**
 * Seg with lazy tag
 * Created by meijun on 6/7/2014.
 */
public abstract class Seg {

    public int N;
    public long[] is;
    public long[] mul;
    public long[] add;
    public long init;

    public Seg(int n, long init) {
        N = Integer.highestOneBit(n) << 1;
        this.init = init;
        is = new long[N * 2];
        mul = new long[N * 2];
        add = new long[N * 2];
        Arrays.fill(is, init);
        Arrays.fill(mul, 1);
    }
    public Seg(long[] a, long init) {
        int n = a.length;
        N = Integer.highestOneBit(n) << 1;
        is = new long[N * 2];
        mul = new long[N * 2];
        add = new long[N * 2];
        Arrays.fill(mul, 1);
        System.arraycopy(a, 0, is, N, n);
        for (int i = N - 1; i > 0; i--) {
            is[i] = merge(is[i << 1], is[i << 1 | 1]);
        }
    }

    public abstract long merge(long a, long b);

    public abstract void push(int o, int L, int R, long m, long a);

    public void update(int l, int r, long m, long a) {
        if (l < r) update(1, 0, N, l, r, m, a);
    }

    private void update(int o, int L, int R, int l, int r, long m, long a) {
        if (l <= L && R <= r) {
            push(o, L, R, m, a);
        } else {
            int M = (L + R) >> 1;
            push(o, L, M, R);
            if (l < M) update(o << 1, L, M, l, r, m, a);
            if (r > M) update(o << 1 | 1, M, R, l, r, m, a);
            is[o] = merge(is[o << 1], is[o << 1 | 1]);
        }
    }

    private void push(int o, int L, int M, int R) {
        if (mul[o] != 1 || add[o] != 0) {
            push(o << 1, L, M, mul[o], add[o]);
            push(o << 1 | 1, M, R, mul[o], add[o]);
            mul[o] = 1;
            add[o] = 0;
        }
    }

    public long query(int l, int r) {
        if (l < r) return query(1, 0, N, l, r);
        return init;
    }

    private long query(int o, int L, int R, int l, int r) {
        if (l <= L && R <= r) {
            return is[o];
        } else {
            int M = (L + R) >> 1;
            push(o, L, M, R);
            long res = init;
            if (l < M) res = merge(res, query(o << 1, L, M, l, r));
            if (r > M) res = merge(res, query(o << 1 | 1, M, R, l, r));
            is[o] = merge(is[o << 1], is[o << 1 | 1]);
            return res;
        }
    }
}
