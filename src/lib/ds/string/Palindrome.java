package lib.ds.string;

/**
 * Palindrome.
 * Created by meijun on 9/16/2014.
 */
public class Palindrome {
    /**
     * Manacher algorithm.
     * [a   c   c   a   b   a]
     * -> [1 0 1 4 1 0 1 0 3 0 1 0]
     */
    public static int[] manacher(char[] cs) {
        int n = cs.length;
        int[] len = new int[n * 2];
        for (int i = 0, j = 0, k; i < n * 2; i += k, j = Math.max(j - k, 0)) {
            while (i - j >= 0 && i + j + 1 < n * 2
                    && cs[(i - j) / 2] == cs[(i + j + 1) / 2]) j++;
            len[i] = j;
            for (k = 1; i - k >= 0 && j - k >= 0 && len[i - k] != j - k; k++) {
                len[i + k] = Math.min(len[i - k], j - k);
            }
        }
        return len;
    }
}
