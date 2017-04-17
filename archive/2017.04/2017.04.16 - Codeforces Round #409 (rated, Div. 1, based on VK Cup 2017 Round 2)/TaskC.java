package main;

import lib.math.Factor;
import lib.math.GCD;
import lib.math.ModInv;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[] del = new boolean[m];
        for (int i = 0; i < n; i++) {
            del[in.nextInt()] = true;
        }
        if (m == 1) {
            out.println(1);
            out.println(0);
            return ;
        }
        List<Integer>[] cnt = new List[m];
        for (int i = 0; i < cnt.length; i++) cnt[i] = new ArrayList<>();
        for (int i = 1; i < m; i++) {
            if (!del[i]) {
                cnt[GCD.gcd(i, m)].add(i);
            }
        }
        int[] dp = new int[m];
        int[] pre = new int[m];
        for (int i = 1; i < m; i++) {
            if (!cnt[i].isEmpty()) {
                List<Integer> fs = Factor.factors(i);
                for (int j : fs) {
                    if (dp[i] == 0 || dp[i] < dp[j]) {
                        pre[i] = j;
                        dp[i] = dp[j];
                    }
                }
                dp[i] += cnt[i].size();
            }
        }
        int last = 0;
        for (int i = 1; i < m; i++) {
            if (dp[i] > dp[last]) {
                last = i;
            }
        }
        if (del[0]) {
            out.println(dp[last]);
        } else {
            out.println(dp[last] + 1);
        }
        List<Integer> g = new ArrayList<>();
        while (last != 1) {
            g.add(last);
            last = pre[last];
        }
        if (!cnt[1].isEmpty()) {
            g.add(1);
        }
        last = 1;
        for (int i = g.size() - 1; i >= 0; i--) {
            int f = g.get(i);
            for (int u : cnt[f]) {
                int gcd = GCD.gcd(GCD.gcd(u, last), m);
//                $.debug(f, last, u, m, gcd, last / gcd, m / gcd);
//                $.debug(BigInteger.valueOf(last / gcd).modInverse(BigInteger.valueOf(m / gcd)));
//                $.debug(last, m, u, gcd, ModInv.inv(last / gcd, m / gcd));
                out.print(ModInv.inv(last / gcd, m / gcd) * u / gcd % m);
                out.print(" ");
                last = u;
            }
        }
        if (!del[0]) out.println(0);
    }
}
