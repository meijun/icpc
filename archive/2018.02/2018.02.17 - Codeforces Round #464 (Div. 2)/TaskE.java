package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int q = in.nextInt();
        List<Integer> is = new ArrayList<>(q);
        long max = 0;
        long sum = 0;
        int id = 0;
        while (q-- > 0) {
            if (in.nextInt() == 1) {
                int x = in.nextInt();
                is.add(x);
                max = x;
            } else {
                while (id < is.size() - 1 && (sum + is.get(id) + max) / (id + 2.0) < (sum + max) / (id + 1.0)) {
                    sum += is.get(id);
                    id++;
                }
                out.printf("%.10f%n", max - (sum + max) / (id + 1.0));
            }
        }
    }
}
