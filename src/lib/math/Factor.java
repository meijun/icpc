package lib.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factor.
 * Created by meijun on 6/6/2016.
 */
public class Factor {
    /**
     * Search method.
     */
    public static void factorize(BigInteger n, Map<BigInteger, Integer> factors, List<BigInteger> primes) {
        if (n.isProbablePrime(10)) {
//            factors.merge(n, 1, Integer::sum);
            inc(factors, n);
        } else {
            for (BigInteger p : primes) {
                while (n.mod(p).equals(BigInteger.ZERO)) {
//                    factors.merge(p, 1, Integer::sum);
                    inc(factors, p);
                    n = n.divide(p);
                }
            }
            if (!n.equals(BigInteger.ONE)) {
                if (n.isProbablePrime(10)) {
//                    factors.merge(n, 1, Integer::sum);
                    inc(factors, n);
                } else {
                    BigInteger d = pollardRho(n, BigInteger.ONE);
                    factorize(d, factors, primes);
                    factorize(n.divide(d), factors, primes);
                }
            }
        }
    }

    private static <K> void inc(Map<K, Integer> map, K k) {
        Integer oldValue = map.get(k);
        Integer newValue = oldValue == null ? 1 : oldValue + 1;
        map.put(k, newValue);
    }

    public static BigInteger pollardRho(BigInteger n, BigInteger c) {
        BigInteger x = BigInteger.valueOf(2);
        BigInteger y = BigInteger.valueOf(2);
        BigInteger d = BigInteger.ONE;
        while (d.equals(BigInteger.ONE)) {
            x = x.multiply(x).mod(n).add(c);
            y = y.multiply(y).mod(n).add(c);
            y = y.multiply(y).mod(n).add(c);
            d = x.subtract(y).abs().gcd(n);
        }
        if (d.equals(n)) return pollardRho(n, c.add(BigInteger.ONE));
        return d;
    }


    /**
     * O(sqrt n).
     *
     * @return {@code HashMap<p, e>}
     */
    public static Map<Integer, Integer> factorize(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
//                map.merge(i, 1, Integer::sum);
                inc(map, i);
                n /= i;
            }
        }
        if (n > 1) {
//            map.merge(n, 1, Integer::sum);
            inc(map, n);
        }
        return map;
    }

    /**
     * O(sqrt n).
     *
     * @return {@code HashMap<p, e>}
     */
    public static Map<Long, Integer> factorize(long n) {
        Map<Long, Integer> map = new HashMap<>();
        for (long i = 2; i * i <= n; i++) {
            while (n % i == 0) {
//                map.merge(i, 1, Integer::sum);
                inc(map, i);
                n /= i;
            }
        }
        if (n > 1) {
//            map.merge(n, 1, Integer::sum);
            inc(map, n);
        }
        return map;
    }


    /**
     * O(sqrt n) / 10.
     * <br /> Ordered.
     */
    public static List<Integer> primeFactors(int n, List<Integer> primes) {
        List<Integer> ps = new ArrayList<>();
        for (int i : primes) {
            if ((long) i * i > n) break;
            if (n % i == 0) {
                ps.add(i);
                while (n % i == 0) n /= i;
            }
        }
        if (n != 1) ps.add(n);
        return ps;
    }

    /**
     * About O(sqrt n) / 10.
     * <br /> Ordered.
     */
    public static List<Long> primeFactors(long n, List<Integer> primes) {
        List<Long> ps = new ArrayList<>();
        for (int i : primes) {
            if ((long) i * i > n) break;
            if (n % i == 0) {
                ps.add((long) i);
                while (n % i == 0) n /= i;
            }
        }
        if (n != 1) ps.add(n);
        return ps;
    }

    /**
     * O(sqrt n).
     */
    public static List<Integer> primeFactors(int n) {
        List<Integer> ps = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                ps.add(i);
                while (n % i == 0) n /= i;
            }
        }
        if (n != 1) ps.add(n);
        return ps;
    }

    /**
     * O(sqrt n).
     */
    public static List<Long> primeFactors(long n) {
        List<Long> ps = new ArrayList<>();
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                ps.add(i);
                while (n % i == 0) n /= i;
            }
        }
        if (n != 1) ps.add(n);
        return ps;
    }


    /**
     * O(sqrt n).
     * <br /> Not Ordered.
     */
    public static List<Integer> factors(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                if (i * i != n) res.add(n / i);
            }
        }
        return res;
    }

    /**
     * O(sqrt n).
     * <br /> Not Ordered.
     */
    public static List<Long> factors(long n) {
        List<Long> res = new ArrayList<>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                if (i * i != n) res.add(n / i);
            }
        }
        return res;
    }

    public static int[] minFactorTable(int n) {
        int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            f[i] = i;
        }
        for (int i = 2; i * i < n; i++) {
            if (f[i] == i) {
                for (int j = i * i; j < n; j += i) {
                    f[j] = i;
                }
            }
        }
        return f;
    }
}
