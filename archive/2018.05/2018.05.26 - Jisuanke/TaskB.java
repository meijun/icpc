package main;

import lib.math.GCD;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        long k = in.nextLong();
        List<Integer> key = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
        int[][] pow = new int[4][20];
        int[] es = new int[]{2, 3, 5, 7};
        int[] p2 = pow[0], p3 = pow[1], p5 = pow[2], p7 = pow[3];
        for (int i = 0; i < 4; i++) {
            int v = 1;
            for (int j = 0; j < 20; j++) {
                pow[i][j] = v;
                v *= es[i];
            }
        }
        long res = 0;
        for (int i = 1; i <= n; i++) {
            int x = 1;
            for (char c : Integer.toString(i).toCharArray()) {
                x *= c - '0';
            }
            if (x == 0) continue;
//            if (x <= k) res++;
            int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
            while (x % 2 == 0) { x /= 2; c2++; }
            while (x % 3 == 0) { x /= 3; c3++; }
            while (x % 5 == 0) { x /= 5; c5++; }
            while (x % 7 == 0) { x /= 7; c7++; }
//            for (int di = 0; di < key.size(); di++) {
//                int d = key.get(di);
//                int dc = value.get(di);
//                int d2 = d >> 24 & 0xff;
//                int d3 = d >> 16 & 0xff;
//                int d5 = d >> 8 & 0xff;
//                int d7 = d & 0xff;
//                d2 = Math.min(d2, c2);
//                d3 = Math.min(d3, c3);
//                d5 = Math.min(d5, c5);
//                d7 = Math.min(d7, c7);
//                x = 1;
//                x *= p2[d2];
//                x *= p3[d3];
//                x *= p5[d5];
//                x *= p7[d7];
//                if (x <= k) {
//                    res += dc * 2;
//                }
//            }
            int c = c2 << 24 | c3 << 16 | c5 << 8 | c7;
            int ix = key.indexOf(c);
            if (ix == -1) {
                ix = key.size();
                key.add(c);
                value.add(0);
            }
            value.set(ix, value.get(ix) + 1);
        }
        for (int i = 0; i < key.size(); i++) {
            int c = key.get(i);
            int cc = value.get(i);
            int c2 = c >> 24 & 0xff;
            int c3 = c >> 16 & 0xff;
            int c5 = c >> 8 & 0xff;
            int c7 = c & 0xff;
            for (int j = 0; j < i; j++) {
                int d = key.get(j);
                int dd = value.get(j);
                int d2 = d >> 24 & 0xff;
                int d3 = d >> 16 & 0xff;
                int d5 = d >> 8 & 0xff;
                int d7 = d & 0xff;
                d2 = Math.min(d2, c2);
                d3 = Math.min(d3, c3);
                d5 = Math.min(d5, c5);
                d7 = Math.min(d7, c7);
                int x = 1;
                x *= p2[d2];
                x *= p3[d3];
                x *= p5[d5];
                x *= p7[d7];
                if (x <= k) {
                    res += (long) cc * dd * 2;
                }
            }
            int x = 1;
            x *= p2[c2];
            x *= p3[c3];
            x *= p5[c5];
            x *= p7[c7];
            if (x <= k) {
                res += (long) cc * cc;
            }
        }
        res %= 998244353;
        out.println(res);
    }
}
