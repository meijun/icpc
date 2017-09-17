package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task6215 {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = i - 1;
            right[i] = i + 1;
        }
        List<Integer> toCheck = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) toCheck.add(i);
        boolean[] del = new boolean[n];
        for (;;) {
            List<Integer> toDel = new ArrayList<>();
            for (int id : toCheck) {
                if (right[id] < n && is[id] > is[right[id]]) {
                    toDel.add(id);
                    toDel.add(right[id]);
                }
            }
            if (toDel.isEmpty()) break;
            toCheck.clear();
            for (int id : toDel) {
                if (!del[id]) {
                    if (left[id] >= 0) toCheck.add(left[id]);
                    del[id] = true;
                    if (left[id] >= 0) right[left[id]] = right[id];
                    if (right[id] < n) left[right[id]] = left[id];
                }
            }
//            for (int i = 0; i < n; i++) {
//                out.print(del[i] ? "X" : "_");
//                out.print(" ");
//            }
//            out.println();
        }
        int cnt = 0;
        for (boolean b : del) if (!b) cnt++;
        out.println(cnt);
        for (int i = 0; i < n; i++) {
            if (!del[i]) {
                out.print(is[i]);
                out.print(" ");
            }
        }
        out.println();
    }
}
