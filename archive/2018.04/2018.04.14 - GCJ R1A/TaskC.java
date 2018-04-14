package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        int s = 0;
        int[] lower = new int[n];
        double[] upper = new double[n];
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            int w = in.nextInt();
            int h = in.nextInt();
            s += (w + h) * 2;
            lower[i] = Math.min(w, h) * 2;
            upper[i] = Math.sqrt(w * w + h * h) * 2;
            is[i] = new Item(lower[i], upper[i]);
        }
        Arrays.sort(is);
        double sp = 0;
//        for (int i = 0; i < n; i++) {
//            if (s + lower[i] > p) break;
//            sp += upper[i] - lower[i];
//            s += lower[i];
//        }
        for (Item i : is) {
            if (s + i.lower <= p) {
                s += i.lower;
                sp += i.upper - i.lower;
            }
        }
        double res = Math.min(p, s + sp);
        out.printf("%.6f%n", res);
    }
    class Item implements Comparable<Item> {
        int lower;
        double upper;

        @Override
        public int compareTo(Item o) {
            return -Double.compare(upper, o.upper);
        }

        public Item(int lower, double upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }
}
