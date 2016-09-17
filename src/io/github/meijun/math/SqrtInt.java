package io.github.meijun.math;

import java.math.BigInteger;

/**
 * Square root of integer
 * Created by meijun on 6/6/2016.
 */
public class SqrtInt {
    public static BigInteger sqrt(String n) {
        int len = n.length(), i;
        BigInteger res = BigInteger.ZERO;
        BigInteger b20 = BigInteger.valueOf(20);
        BigInteger t, x = BigInteger.ZERO, v, few = BigInteger.ZERO;
        BigInteger hg = BigInteger.valueOf(100);
        int pos = 2 - len % 2;
        String tmp = n.substring(0, pos);
        while (true) {
            v = few.multiply(hg).add(BigInteger.valueOf(Integer.parseInt(tmp)));
            if (res.compareTo(BigInteger.ZERO) == 0) i = 9;
            else i = v.divide(res.multiply(b20)).intValue();
            for (; i >= 0; i--) {
                t = res.multiply(b20).add(BigInteger.valueOf(i)).multiply(BigInteger.valueOf(i));
                if (t.compareTo(v) <= 0) {
                    x = t;
                    break;
                }
            }
            res = res.multiply(BigInteger.TEN).add(BigInteger.valueOf(i));
            few = v.subtract(x);
            pos++;
            if (pos > len) break;
            tmp = n.substring(pos - 1, ++pos);
        }
        return res;
    }
}
