package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            is[i] = new Item(in.nextInt(), in.nextInt());
        }

//        int res = 0;
//        int d0 = 0, d1 = 0;
//        int t = 0;
//        for (Item it : is) {
//            if (d0 == 0) {
//                d0 = it.d;
//                t += it.r;
//            } else {
//                d1 = it.d;
//                t += it.r;
//            }
//            if (d0 != 0 && d1 != 0) {
//                t += Math.min(d0, d1);
//                d0 = Math.max(d0, d1);
//                res += t;
//            }
//        }
//        t += d0;
//        res += t;
//        out.println(res);

        Item f = is[0];
        Set<Item> set = new HashSet<>();
        Collections.addAll(set, is);
        for (Item i : set) if (i.r < f.r) f = i;
        set.remove(f);
        int res = 0;
        int t = f.r;
        int d = f.d;
        while (!set.isEmpty()) {
            f = set.iterator().next();
            for (Item i : set) if (i.r + Math.min(i.d, d) < f.r + Math.min(f.d, d)) f = i;
            set.remove(f);
            t += f.r + Math.min(f.d, d);
            res += t;
            d = Math.max(f.d, d);
        }
        t += d;
        res += t;
        out.println(res);
    }
    class Item {
        int r;
        int d;

        public Item(int r, int d) {
            this.r = r;
            this.d = d;
        }
    }
}
