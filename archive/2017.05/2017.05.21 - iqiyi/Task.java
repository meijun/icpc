package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String line = in.nextLine();
        String[] ls = line.split(" ");
        int d = Integer.parseInt(ls[0]);
        int q = Integer.parseInt(ls[1]);
//        int K = 100000;
        int K = 600000;
        int[] value = new int[K + 1];
        Arrays.fill(value, -1);

        long[] freq = new long[K + 1];
        int[] old_f = new int[K + 1];
        TreeMap<Long, Integer> map = new TreeMap<>();
//        int Q = 500000;
        int Q = 600000;

        for (int qi = 1; qi <= q; qi++) {
            line = in.nextLine().trim();
            ls = line.split(" ");
            int id = Integer.parseInt(ls[0]);
            if (ls.length == 1) {
                out.println(value[id]);
                if (value[id] != -1) {
                    long f = freq[id];
                    long nf = (f / Q + 1) * Q + f % Q;

                    map.remove(f);
                    map.put(nf, id);

                    freq[id] = nf;
                }
                old_f[id]++;
            } else {
                int nv = Integer.parseInt(ls[0]);
                if (d > 0) value[id] = nv;
                if (freq[id] == 0 && d > 0) {
                    if (map.size() == d) {
                        Map.Entry<Long, Integer> first = map.firstEntry();
                        int old_id = first.getValue();
                        long old_freq = first.getKey();

                        value[old_id] = -1;
//                        old_f[old_id] = (int) (old_freq % Q);
                        freq[old_id] = 0;
                        map.remove(old_freq);
                    }
//                    if (old_f[id] == 0) old_f[id] = qi;
                    long f = qi;
                    freq[id] = f;
                    map.put(f, id);
                } else {
//                    long f = freq[id];
//                    long nf = (f / Q) * Q + qi;
//                    map.remove(f);
//                    map.put(nf, id);
//                    freq[id] = nf;
                }
            }
//            $.debug(id, map);
        }
    }
}
