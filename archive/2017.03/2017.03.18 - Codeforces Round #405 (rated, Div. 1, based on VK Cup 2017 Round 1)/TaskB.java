package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    private int k;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        k = in.nextInt();
        T[] ts = new T[n];
        for (int i = 0; i < n; i++) {
            ts[i] = new T(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            ts[u].add(ts[v]);
            ts[v].add(ts[u]);
        }
        dfs(ts[0], null);
        long res = dfs(ts[0], null, new long[k + 1], new long[k + 1]);
        out.println(res / 2);
    }

    private void dfs(T u, T f) {
        for (T v : u) if (v != f) {
            dfs(v, u);
            for (int i = 2; i <= k; i++) {
                u.cnt[i] += v.cnt[i - 1];
                u.sum[i] += v.sum[i - 1];
            }
            u.cnt[1] += v.cnt[k] + 1;
            u.sum[1] += v.sum[k] + v.cnt[k] + 1;
        }
//        $.debug(u.id, u.cnt, u.sum);
    }

    private long dfs(T u, T f, long[] f_cnt, long[] f_sum) {
//        $.debug(u.id, f_cnt, f_sum);
        long[] u_cnt = new long[k + 1];
        long[] u_sum = new long[k + 1];
        for (int i = 2; i <= k; i++) {
            u_cnt[i] += f_cnt[i - 1];
            u_sum[i] += f_sum[i - 1];
        }
        if (f != null) {
            u_cnt[1] += f_cnt[k] + 1;
            u_sum[1] += f_sum[k] + f_cnt[k] + 1;
        }
//        $.debug(u.id, u_cnt, u_sum);
        long res = 0;
        for (T v : u) if (v != f) {
            long[] uv_cnt = new long[k + 1];
            long[] uv_sum = new long[k + 1];
            for (int i = 1; i <= k; i++) {
                uv_cnt[i] = u_cnt[i] + u.cnt[i] - v.cnt[i - 1];
                uv_sum[i] = u_sum[i] + u.sum[i] - v.sum[i - 1];
            }
            uv_cnt[1] -= v.cnt[k] + 1;
            uv_sum[1] -= v.sum[k] + v.cnt[k] + 1;
            res += dfs(v, u, uv_cnt, uv_sum);
        }
        for (int i = 1; i <= k; i++) {
            res += u_sum[i] + u.sum[i];
        }
        return res;
    }

    class T extends ArrayList<T> {
        int id;
        long[] cnt = new long[k + 1];
        long[] sum = new long[k + 1];

        public T(int id) {
            this.id = id;
        }
    }
}
