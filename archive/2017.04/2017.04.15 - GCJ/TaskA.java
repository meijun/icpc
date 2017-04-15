package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d:\n", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int r = in.nextInt();
        int c = in.nextInt();
        char[][] maps = new char[r][];
        for (int i = 0; i < r; i++) {
            maps[i] = in.next().toCharArray();
        }
        boolean[] used = new boolean[128];
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                if (maps[i][j] != '?' && !used[maps[i][j]]) {
                    char v = maps[i][j];
                    maps[i][j] = '?';
                    int il = i, ir = i;
                    while (il > 0 && maps[il - 1][j] == '?') il--;
                    while (ir < r-1 && maps[ir + 1][j] == '?') ir++;
                    int jl = j, jr = j;
                    while (jl > 0 && allEmpty(maps, il, ir, jl-1)) jl--;
                    while (jr < c-1 && allEmpty(maps, il, ir, jr+1)) jr++;
                    for (int ii = il; ii <= ir; ii++) {
                        for (int jj = jl; jj <= jr; jj++) {
                            maps[ii][jj] = v;
                        }
                    }
                    used[v] = true;
//                    $.debug(i, j);
//                    for (char[] cs : maps) $.debug(cs);
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                out.print(maps[i][j]);
            }
            out.println();
        }
    }

    private boolean allEmpty(char[][] maps, int il, int ir, int j) {
        for (int i = il; i <= ir; i++) {
            if (maps[i][j] != '?') return false;
        }
        return true;
    }
}
