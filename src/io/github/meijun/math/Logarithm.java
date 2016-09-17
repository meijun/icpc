package io.github.meijun.math;

import java.util.HashMap;
import java.util.Map;

import static io.github.meijun.math.Equation.congruence;

/**
 * Mod log
 * Created by meijun on 6/6/2016.
 */
public class Logarithm {
    // a^x = b (modPrime m) return min(x)
    // return -1 if no solution
    public static long modLog(long a, long b, long m) {
        if (b % GCD.gcd(a, m) != 0) return -1;
        if (m == 0) return 0;
        long n = (long) Math.sqrt(m) + 1;
        Map<Long, Long> map = new HashMap<>();
        long an = 1;
        for (long j = 0; j < n; j++) {
            if (!map.containsKey(an)) map.put(an, j);
            an = an * a % m;
        }
        long ain = 1, res = Long.MAX_VALUE;
        for (long i = 0; i < n; i++) {
            long[] is = congruence(ain, b, m);
            for (long aj = is[0]; aj < m; aj += is[1])
                if (map.containsKey(aj)) {
                    long j = map.get(aj);
                    res = Math.min(res, i * n + j);
                }
            if (res < Long.MAX_VALUE) return res;
            ain = ain * an % m;
        }
        return -1;
    }
}
