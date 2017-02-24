package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Item[] is = new Item[n + m];
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            is[i] = new Item(s);
        }
        for (int i = 0; i < m; i++) {
            is[n + i] = new Item(-1);
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            for (int j = 0; j < x; j++) {
                int d = in.nextInt() - 1;
                is[n + i].is.add(is[d]);
                is[d].is.add(is[n + i]);
            }
        }
        int vis = -1;
        for (int i = 0; i < m; i++) {
            if (is[i + n].vis == 0) {
                if (!dfs(is[i + n], 0, --vis)) {
//                    if (!dfs(is[i + n], 1, --vis)) {
                        out.println("NO");
                        return ;
//                    }
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            $.debug(is[i + n].state, is[i + n].vis);
//        }
        out.println("YES");
    }

    private boolean dfs(Item c, int as, int vis) {
        if (c.vis == vis) {
            return c.state == as;
        }
        c.vis = vis;
        c.state = as;
        for (Item d : c.is) {
            for (Item s : d.is) {
                if (s != c) {
                    if (!dfs(s, c.state ^ d.state ^ 1, vis)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    class Item {
        List<Item> is = new ArrayList<>();
        int state;
        int vis;

        public Item(int state) {
            this.state = state;
        }
    }
}
