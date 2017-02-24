package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<Long> ls = new ArrayList<>();
        long l = 1;
        if (k == 1 || k == -1) {
            ls.add(1L);
            if (k == -1) {
                ls.add(-1L);
            }
        } else {
            while (l < 1e14 + 10 && l > -(1e14 + 10)) {
                ls.add(l);
                l *= k;
            }
        }
        long base = 0;
        Map<Long, Integer> cnt = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            base += a;
            cnt.merge(a - base, 1, Integer::sum);
            for (long w : ls) {
                res += cnt.getOrDefault(w - base, 0);
            }
        }
        out.println(res);
    }
}
