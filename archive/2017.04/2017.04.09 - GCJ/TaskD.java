package main;

import lib.graph.BiMatching;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

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
        int m = in.nextInt();
        boolean[][] ad = new boolean[n][n];
        boolean[][] ml = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            char c = in.next().charAt(0);
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (c == '+' || c == 'o') {
                ad[x][y] = true;
            }
            if (c == 'x' || c == 'o') {
                ml[x][y] = true;
            }
        }
        boolean[][] adn = new boolean[n][n];
        boolean[][] mln = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            boolean contains = false;
            for (int j = 0; j < n; j++) if (ml[i][j] || mln[i][j]) contains = true;
            if (!contains) {
                for (int j = 0; j < n; j++) {
                    boolean con = false;
                    for (int k = 0; k < n; k++) {
                        if (ml[k][j] || mln[k][j]) con = true;
                    }
                    if (!con) {
                        mln[i][j] = true;
                        break;
                    }
                }
            }
        }
        BiMatching.V[] vs = new BiMatching.V[(n * 2 - 1) * 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BiMatching.V(i);
        }
        boolean[] ox = new boolean[n * 2 - 1];
        boolean[] oy = new boolean[n * 2 - 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ad[i][j]) {
                    int x = i + j;
                    int y = i - j + n - 1;
                    ox[x] = true;
                    oy[y] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = i + j;
                int y = i - j + n - 1;
                if (!ox[x] && !oy[y]) {
                    vs[x].connect(vs[y + n * 2 - 1]);
                }
            }
        }
        BiMatching.hungary(vs);
        for (int x = 0; x < n * 2 - 1; x++) {
            if (vs[x].pair != null) {
                int y = vs[x].pair.id - (n * 2 - 1);
                y -= n - 1;
                int i = (x + y) / 2;
                int j = (x - y) / 2;
                adn[i][j] = true;
            }
        }
        int res = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ad[i][j] || adn[i][j]) res++;
                if (ml[i][j] || mln[i][j]) res++;
                if (adn[i][j] || mln[i][j]) {
                    cnt++;
                }
            }
        }
        out.println(res + " " + cnt);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adn[i][j] || mln[i][j]) {
                    char c;
                    if ((ad[i][j] || adn[i][j]) && (ml[i][j] || mln[i][j])) {
                        c = 'o';
                    } else if (adn[i][j]) {
                        c = '+';
                    } else {
                        c = 'x';
                    }
                    out.println(c + " " + (i + 1) + " " + (j + 1));
                }
            }
        }
    }
}
