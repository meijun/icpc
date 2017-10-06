package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;

public class TaskInversion {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            is[i] = new Item(i, in.nextInt(), in.nextInt());
        }
        Item[] ts = new Item[n];
        Item[] ts1 = new Item[n];
        int res = solve1(is, 0, n, ts, ts1);
        out.println(res);
    }

    private int solve1(Item[] is, int l, int r, Item[] ts, Item[] ts1) {
        if (r - l < 2) return 0;
        int m = (l + r) / 2;
        int res = solve1(is, l, m, ts, ts1) + solve1(is, m, r, ts, ts1);
        for (int k = l, i = l, j = m; k < r; k++) {
            if (i < m && (j == r || is[i].x < is[j].x)) {
                ts[k] = is[i++];
            } else {
                ts[k] = is[j++];
            }
        }
        System.arraycopy(ts, l, is, l, r - l);
        res += solve2(ts, l, r, ts1, m);
        return res;
    }

    private int solve2(Item[] is, int l, int r, Item[] ts, int mid) {
        if (r - l < 2) return 0;
        int m = (l + r) / 2;
        int mm = -1;
        for (int i = 0; m + i < r; i++) {
            if (is[m + i - 1].x < is[m + i].x) {
                mm = m + i;
                break;
            }
            if (m - i > l && is[m - i - 1].x < is[m - i].x) {
                mm = m - i;
                break;
            }
        }
        if (mm != -1) {
            m = mm;
        }
        int res = solve2(is, l, m, ts, mid) + solve2(is, m, r, ts, mid);

        if (mm != -1) for (int i = m - 1, j = r - 1, cnt = 0; i >= l; i--) {
            while (j >= m && is[j].y > is[i].y) {
                if (is[j].id < mid) cnt++;
                j--;
            }
            if (is[i].id >= mid) res += cnt;
        }
        for (int k = l, i = l, j = m; k < r; k++) {
            if (i < m && (j == r || is[i].y <= is[j].y)) {
                ts[k] = is[i++];
            } else {
                ts[k] = is[j++];
            }
        }
        System.arraycopy(ts, l, is, l, r - l);
        return res;
    }

    class Item {
        int id;
        int x;
        int y;
        int q;

        public Item(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" +
                    "id=" + id +
                    ", x=" + x +
                    ", y=" + y +
                    ", q=" + q +
                    '}';
        }
    }
}
