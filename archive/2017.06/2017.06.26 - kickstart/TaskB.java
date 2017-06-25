package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            $.debug(i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        char[][] maps = new char[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = in.next().toCharArray();
        }
        int[] i0 = new int[n];
        int[] i1 = new int[n];
        int[] j0 = new int[n];
        int[] j1 = new int[n];
        Arrays.fill(i0, -1);
        Arrays.fill(i1, -1);
        Arrays.fill(j0, -1);
        Arrays.fill(j1, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (maps[i][j] == 'X') {
                    if (i0[i] == -1) {
                        i0[i] = j;
                    } else if (i1[i] == -1) {
                        i1[i] = j;
                    } else {
                        im(out);
                        return ;
                    }
                    if (j0[j] == -1) {
                        j0[j] = i;
                    } else if (j1[j] == -1) {
                        j1[j] = i;
                    } else {
                        im(out);
                        return ;
                    }
                }
            }
        }
        int ci = -1, cj = -1;
        for (int i = 0; i < n; i++) {
            if (i1[i] == -1) {
                ci = i;
                cj = i0[i];
                if (j0[cj] != i || j1[cj] != -1) {
                    im(out);
                    return ;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != ci) {
                int ja = i0[i];
                int jb = i1[i];
                int ic = j0[ja];
                if (ic == i) ic = j1[ja];
                int id = j0[jb];
                if (id == i) id = j1[jb];
                if (ic != id) {
                    im(out);
                    return ;
                }
            }
        }
        out.println("POSSIBLE");
    }

    private void im(PrintWriter out) {
        out.println("IMPOSSIBLE");
    }

    class X {
        int x;
        int y;

        public X(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
