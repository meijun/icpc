package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        char[][] ss = new char[n][];
        for (int i = 0; i < n; i++) {
            ss[i] = in.next().toCharArray();
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        res[n - 1] = ss[n - 1].length - 1;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < ss[i].length; j++) {
                if (ss[i][j] > ss[i + 1][j]) {
                    res[i] = j - 1;
                    break;
                }
                if (ss[i][j] < ss[i + 1][j]) {
                    res[i] = ss[i].length - 1;
                    break;
                }
                if (res[i + 1] == j) {
                    res[i] = j;
                    break;
                }
            }
            if (res[i] == -1) {
                res[i] = ss[i].length - 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= res[i]; j++) {
                out.print(ss[i][j]);
            }
            out.println();
        }
    }
}
