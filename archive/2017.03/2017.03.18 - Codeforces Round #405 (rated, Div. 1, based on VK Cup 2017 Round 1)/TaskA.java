package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        boolean[][] g = new boolean[n][n];
        for (boolean[] b : g) Arrays.fill(b, true);
        int[] names = new int[n];
        for (int i = 0; i < k - 1; i++) {
            names[i] = i;
            int o = names[i];
            out.print("" + ((char) (o / 26 + 'A')) + ((char) (o % 26 + 'a')) + " ");
        }
        for (int i = 0; i <= n - k; i++) {
            if (in.next().charAt(0) == 'Y') {
                int s = k * (k - 1) / 2;
                for (int j = 0; j < k - 1; j++) {
                    s -= names[i + j];
                }
                names[i + k - 1] = s;
//                for (int u = 0; u < k; u++) {
//                    for (int v = 0; v < k; v++) {
//                        if (u != v) {
//                            g[i + u][i + v] = false;
//                        }
//                    }
//                }
            } else {
                names[i + k - 1] = names[i];
            }
            int o = names[i + k - 1];
            out.print("" + ((char) (o / 26 + 'A')) + ((char) (o % 26 + 'a')) + " ");
        }
        out.println();
//        Arrays.fill(names, -1);
//        for (int i = 0; i < n; i++) {
//            for (int o = 0; ; o++) {
////                $.debug(i, o, names);
//                if (fit(n, g, o, i, names)) {
//                    out.print("" + ((char) (o / 26 + 'A')) + ((char) (o % 26 + 'a')));
//                    out.print(' ');
//                    names[i] = o;
//                    break;
//                }
//            }
//        }
//        out.println();
    }

    private boolean fit(int n, boolean[][] g, int o, int x, int[] names) {
        for (int i = 0; i < n; i++) {
            if (!g[i][x] && names[i] == o) {
                return false;
            }
        }
        return true;
    }
}
