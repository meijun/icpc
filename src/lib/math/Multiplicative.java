package lib.math;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Multiplicative function.
 * Created by meijun on 6/6/2016.
 */
public class Multiplicative {

    public static final Func DIVISOR_COUNT = (p, e, pow) -> e + 1;
    public static final Func DIVISOR_SUM = (p, e, pow) -> (pow * p - 1) / (p - 1);
    public static final Func PHI = (p, e, pow) -> pow / p * (p - 1);
    public static final Func MOBIUS = (p, e, pow) -> e == 1 ? -1 : 0;

    public static long value(long n, Func func) {
        long value = 1;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                int e = 0;
                long po = 1;
                do {
                    e++;
                    po *= i;
                    n /= i;
                } while (n % i == 0);
                value *= func.value(i, e, po);
            }
        }
        if (n != 1) {
            value *= func.value(n, 1, n);
        }
        return value;
    }

    public static long[] table(int n, Func func) {
        int[] minFactor = Factor.minFactorTable(n);
        long[] result = new long[n];
        if (n <= 1) {
            return result;
        }
        result[1] = 1;
        for (int i = 2; i < n; i++) {
            int ii = i;
            int e = 0;
            do {
                ii /= minFactor[i];
                e++;
            } while (ii % minFactor[i] == 0);
            result[i] = result[ii] * func.value(minFactor[i], e, i / ii);
        }
        return result;
    }

    /**
     * 把 n 的约数的莫比乌斯值用 map 形式的返回。O(sqrt n)
     */
    public static Map<Long, Integer> mobius(long n) {
        Map<Long, Integer> res = new TreeMap<>();
        List<Long> primes = Factor.primeFactors(n);
        int m = primes.size();
        for (int i = 0; i < (1 << m); i++) {
            int mu = 1;
            long d = 1;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    mu *= -1;
                    d *= primes.get(j);
                }
            }
            res.put(d, mu);
        }
        return res;
    }

    interface Func {
        long value(long p, int e, long pow);
    }
}
