package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task2 {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        Arrays.sort(ps);
        int maxY = -1;
        List<P> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; ) {
            int crtY = -1;
            int j = i;
            while (j >= 0 && ps[j].x == ps[i].x) {
                crtY = Math.max(crtY, ps[j].y);
                if (ps[j].y >= maxY) {
                    res.add(ps[j]);
                }
                j--;
            }
            i = j;
            maxY = Math.max(maxY, crtY);
        }
        Collections.sort(res);
        for (P p : res) {
            out.println(p.x + " " + p.y);
        }
    }
    class P implements Comparable<P> {
        int x;
        int y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            return Integer.compare(x, o.x);
        }
    }
}
