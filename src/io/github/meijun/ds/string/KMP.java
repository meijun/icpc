package io.github.meijun.ds.string;

/**
 * String KMP Algorithm.
 * Created by meijun on 10/3/2014.
 */
public class KMP {
    public int m;
    public char[] p;
    public int[] fail;

    public KMP(char[] p) {
        m = p.length;
        this.p = p;
        fail = new int[m + 1];
        int crt = fail[0] = -1;
        for (int i = 1; i <= m; i++) {
            while (crt >= 0 && p[crt] != p[i - 1]) crt = fail[crt];
            fail[i] = ++crt;
        }
    }

    public static int[] getFailArray(char[] p) {
        int m = p.length;
        int[] fail = new int[m + 1];
        int crt = fail[0] = -1;
        for (int i = 1; i <= m; i++) {
            while (crt >= 0 && p[crt] != p[i - 1]) crt = fail[crt];
            fail[i] = ++crt;
        }
        return fail;
    }

    public int searchFrom(char[] t) {
        int n = t.length, count = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j >= 0 && t[i] != p[j]) j = fail[j];
            if (++j == m) {
                count++;
                j = fail[j];
            }
        }
        return count;
    }
}
