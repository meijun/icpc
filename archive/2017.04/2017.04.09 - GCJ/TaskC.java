package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            $.debug(i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        long n = in.nextLong();
        long k = in.nextLong();
        TreeMap<Long, Long> map = new TreeMap<>();
        map.put(n, 1L);
        long crt = n + 1;
        while (k > 0) {
            crt = map.lowerKey(crt);
            long cnt = map.get(crt);
            if (cnt >= k) {
                out.println((crt / 2) + " " + ((crt - 1) / 2));
            }
            k -= cnt;
            map.merge(crt / 2, cnt, Long::sum);
            map.merge((crt - 1) / 2, cnt, Long::sum);
        }
    }

}
