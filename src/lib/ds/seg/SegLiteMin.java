package lib.ds.seg;

import java.util.Arrays;

/**
 * Seg lite min.
 * Created by meijun on 11/14/2016.
 */
public class SegLiteMin {

    public final int N;
    public int[] is;

    public SegLiteMin(int n) {
        N = Integer.highestOneBit(n) << 1;
        is = new int[N * 2];
        Arrays.fill(is, N, N + N, Integer.MAX_VALUE);
        for (int i = N - 1; i > 0; i--) {
            is[i] = Math.min(is[i << 1], is[i << 1 | 1]);
        }
    }

    public SegLiteMin(int[] vs) {
        N = Integer.highestOneBit(vs.length) << 1;
        is = new int[N * 2];
        System.arraycopy(vs, 0, is, N, vs.length);
        for (int i = N - 1; i > 0; i--) {
            is[i] = Math.min(is[i << 1], is[i << 1 | 1]);
        }
    }

    public void update(int k, int v) {
        k += N;
        is[k] = v;
        for (k >>= 1; k > 0; k >>= 1) {
            is[k] = Math.min(is[k << 1], is[k << 1 | 1]);
        }
    }

    public int query(int s, int t) {
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        while (0 < s && s + (s & -s) <= t) {
            int i = (N + s) / (s & -s);
            left = Math.min(left, is[i]);
            s += s & -s;
        }
        while (s < t) {
            int i = (N + t) / (t & -t) - 1;
            right = Math.min(is[i], right);
            t -= t & -t;
        }
        return Math.min(left, right);
    }
}
