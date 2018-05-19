package main;

import lib.graph.BiMatching;
import lib.misc.$;
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
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = in.nextInt();
            }
        }
        int all = 0;
        for (int v = -n; v <= n; v++) {
            if (v != 0) {
                BiMatching.V[] vs = new BiMatching.V[n + n];
                for (int i = 0; i < vs.length; i++) vs[i] = new BiMatching.V(i);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) if (map[i][j] == v) {
                        for (int k = 0; k < n; k++) {
                            if (k != i && map[i][j] == map[k][j] || k != j && map[i][j] == map[i][k]) {
                                vs[i].connect(vs[n+j]);
                                all += 1;
                                break;
                            }
                        }
                    }
                }
                int mat = BiMatching.hungary(vs);
                all -= mat;
            }
        }
        out.println(all);
    }
}
