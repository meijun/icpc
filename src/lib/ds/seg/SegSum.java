package lib.ds.seg;

/**
 * Seg sum
 * Created by meijun on 6/7/2016.
 */
public class SegSum extends Seg {

    public SegSum(int n) {
        super(n, 0);
    }

    public SegSum(long[] a) {
        super(a, 0);
    }

    @Override
    public long merge(long a, long b) {
        return a + b;
    }

    @Override
    public void push(int o, int L, int R, long m, long a) {
        is[o] = is[o] * m + a * (R - L);
        mul[o] = mul[o] * m;
        add[o] = add[o] * m + a;
    }
}
