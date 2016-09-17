package io.github.meijun.ds.seg;

/**
 * Seg max
 * Created by meijun on 6/7/2016.
 */
public class SegMax extends Seg {

    public SegMax(int n) {
        super(n, Long.MIN_VALUE);
    }

    public SegMax(long[] a) {
        super(a);
    }

    @Override
    public long merge(long a, long b) {
        return Math.max(a, b);
    }

    @Override
    public void push(int o, int L, int R, long m, long a) {
        is[o] = is[o] * m + a;
        mul[o] = mul[o] * m;
        add[o] = add[o] * m + a;
    }
}
