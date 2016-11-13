package lib.math;

import java.util.Arrays;

/**
 * Prime.
 * Created by meijun on 6/6/2016.
 */
public class Prime {
    /**
     * O(sqrt n).
     */
    public static boolean test(long n) {
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean[] table(int n) {
        boolean[] isp = new boolean[n];
        Arrays.fill(isp, true);
        isp[0] = isp[1] = false;
        for (int i = 2; i < n; i++) {
            if (isp[i]) {
                for (int j = i * i; j < n; j += i) {
                    isp[j] = false;
                }
            }
        }
        return isp;
    }

    public static int[] primes(int n) {
        boolean[] isp = table(n);
        int cnt = 0;
        for (boolean b : isp) if (!b) cnt++;
        int[] ps = new int[cnt];
        for (int i = 0, id = 0; i < n; i++) {
            if (isp[i]) {
                ps[id++] = i;
            }
        }
        return ps;
    }
}
