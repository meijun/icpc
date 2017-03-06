package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] as = new int[n];
        int[] bs = new int[n];
        for (int i = 0; i < n; i++) as[i] = in.nextInt();
        for (int i = 0; i < n; i++) bs[i] = in.nextInt();
        int sum = 0;
        for (int i : bs) sum += i;
        for (int i = 0; i < n; i++) {
            as[i] -= bs[i];
        }
        Arrays.sort(as);
        for (int i = 0; i < k || (i < n && as[i] < 0); i++) {
            sum += as[i];
        }
        out.println(sum);
    }
}
