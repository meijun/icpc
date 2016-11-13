package lib.ds.string;

/**
 * String hash algorithm.
 * Created by meijun on 6/21/2014.
 */
public class Hash {

    public static final long BASE = (long) (1e9 + 7);
    public long[] ps;

    public Hash(int n) {
        ps = new long[n + 1];
        for (int i = 0; i <= n; i++) ps[i] = (i == 0 ? 1 : ps[i - 1] * BASE);
    }

    public static long getHash(char[] cs) {
        return getHash(cs, 0, cs.length);
    }

    public static long getHash(char[] cs, int b, int e) {
        long h = 0;
        for (int i = b; i < e; i++) {
            h = h * BASE + cs[i];
        }
        return h;
    }

    public static long getHash(int[] is) {
        return getHash(is, 0, is.length);
    }

    public static long getHash(int[] is, int b, int e) {
        long h = 0;
        for (int i = b; i < e; i++) {
            h = h * BASE + is[i];
        }
        return h;
    }

    public long[] build(char[] cs) {
        int n = cs.length;
        long[] hs = new long[n + 1];
        for (int i = 0; i < n; i++) hs[i + 1] = hs[i] * BASE + cs[i];
        return hs;
    }

    public long[] build(int[] is) {
        int n = is.length;
        long[] hs = new long[n + 1];
        for (int i = 0; i < n; i++) hs[i + 1] = hs[i] * BASE + is[i];
        return hs;
    }

    public long get(long[] hs, int b, int e) {
        return hs[e] - hs[b] * ps[e - b];
    }
}
