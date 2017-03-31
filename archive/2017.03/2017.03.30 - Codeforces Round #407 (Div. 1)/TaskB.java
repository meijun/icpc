package main;

import lib.ds.UnionFindSet;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        UnionFindSet ufs = new UnionFindSet(n);
        boolean[] vis = new boolean[n];
        int self = 0;
        int[] edges = new int[n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            ufs.union(u, v);
            vis[u] = true;
            vis[v] = true;
            if (u == v) {
                self++;
            } else {
                edges[u]++;
                edges[v]++;
            }
        }
        long res = 0;
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (vis[i]) {
                if (root == -1 || ufs.find(i) == root) {
                    res += (long) (edges[i] - 1) * edges[i] / 2;
                } else {
                    out.println(0);
                    return ;
                }
                root = ufs.find(i);
            }
        }
        res += (long) self * (self - 1) / 2;
        res += (long) self * (m - self);
        out.println(res);
    }
}
