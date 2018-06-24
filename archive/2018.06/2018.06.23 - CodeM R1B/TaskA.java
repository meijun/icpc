package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        Action[] as = new Action[k];
        for (int i = 0; i < k; i++) {
            as[i] = new Action(in.nextInt() - 1, in.nextInt() - 1, in.nextInt(), in.next());
        }
        Arrays.sort(as);
        int[][] map = new int[n][m];
        int cnt = 0;
        int mi = 0;
        int mc = 0;
        for (int i = 0; i < k; i++) {
            Action a = as[i];
            if (a.z == 0) {
                if (map[a.x][a.y] == 0) {
                    cnt++;
                }
                map[a.x][a.y]++;
            } else {
                map[a.x][a.y]--;
                if (map[a.x][a.y] == 0) {
                    cnt--;
                }
            }
            if (cnt >= mc) {
                mi = i;
                mc = cnt;
            }
        }
        $.fill(map, 0);
        for (int i = 0; i < k; i++) {
            Action a = as[i];
            if (a.z == 0) {
                map[a.x][a.y]++;
            } else {
                map[a.x][a.y]--;
            }
            if (i == mi) {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.print(map[i][j] == 0 ? 0 : 1);
            }
            out.println();
        }
    }
    class Action implements Comparable<Action> {
        int x;
        int y;
        int z;
        int t;

        public Action(int x, int y, int z, String s) {
            this.x = x;
            this.y = y;
            this.z = z;
            int h = Integer.parseInt(s.substring(0, 2));
            int m = Integer.parseInt(s.substring(3, 5));
            int e = Integer.parseInt(s.substring(6, 8)) * 1000 + Integer.parseInt(s.substring(9));
            t = h * 3600 + m * 60;
            t *= 1000;
            t += e;
        }

        @Override
        public int compareTo(Action o) {
            return Integer.compare(t, o.t);
        }
    }
}
