package lib.ds.seg;

/**
 * Seg min
 * Created by meijun on 6/7/2016.
 */
public class SegMin extends Seg {

    public SegMin(int n) {
        super(n, Long.MAX_VALUE);
    }

    public SegMin(long[] a) {
        super(a, Long.MAX_VALUE);
    }

    @Override
    public long merge(long a, long b) {
        return Math.min(a, b);
    }

    @Override
    public void push(int o, int L, int R, long m, long a) {
        is[o] = is[o] * m + a;
        mul[o] = mul[o] * m;
        add[o] = add[o] * m + a;
    }
}
