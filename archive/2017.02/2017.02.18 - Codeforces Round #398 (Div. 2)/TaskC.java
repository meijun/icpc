package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        T[] ts = new T[n];
        for (int i = 0; i < n; i++) {
            ts[i] = new T(i);
        }
        int root = -1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int p = in.nextInt() - 1;
            ts[i].w = in.nextInt();
            if (p == -1) root = i;
            else {
                ts[i].cs.add(ts[p]);
                ts[p].cs.add(ts[i]);
            }
            sum += ts[i].w;
        }
        dfs_color(ts[root], null, sum);
        // part 1: black nodes
        dfs_hasBlackChild(ts[root], null);
        int first = -1;
        for (T t : ts) {
            if (t.blackLeaf) {
                if (first != -1) {
                    out.print(first + 1);
                    out.print(" ");
                    out.println(t.id + 1);
                    return ;
                } else {
                    first = t.id;
                }
            }
        }
        // part 2: red parent, black child
        if (!dfs_red_black(ts[root], null, null, out)) {
            out.println(-1);
        }
    }

    private boolean dfs_red_black(T t, T f, T red, PrintWriter out) {
        if (red != null) {
            if (t.black) {
                out.print(red.id + 1);
                out.print(" ");
                out.println(t.id + 1);
                return true;
            }
        } else {
            if (t.red) {
                red = t;
            }
        }
        for (T c : t.cs) {
            if (c != f) {
                if (dfs_red_black(c, t, red, out)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs_hasBlackChild(T t, T f) {
        boolean has = false;
        for (T c : t.cs) {
            if (c != f) {
                has |= dfs_hasBlackChild(c, t);
            }
        }
        if (t.black && !has) t.blackLeaf = true;
        has |= t.black;
        return has;
    }

    private int dfs_color(T t, T f, int all) {
        int sum = 0;
        for (T c : t.cs) {
            if (c != f) {
                sum += dfs_color(c, t, all);
            }
        }
        sum += t.w;
        if (sum * 3 == all) {
            t.black = true;
        }
        if (sum * 3 == all * 2) {
            t.red = true;
        }
        return sum;
    }

    class T {
        List<T> cs = new ArrayList<>();
        int id;
        int w;
        boolean black;
        boolean red;
        boolean blackLeaf;


        public T(int id) {
            this.id = id;
        }
    }
}
