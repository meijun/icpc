package lib.ds;

import java.util.List;

/**
 * Tree LCA.
 * Created by meijun on 9/22/2014.
 */
public class LCA {

    public List<Integer>[] vs;
    public int root;

    public int[] depth;
    public int[][] pre;

    public LCA(List<Integer>[] vs, int root) {
        this.vs = vs;
        this.root = root;
        int n = vs.length;
        depth = new int[n];
        pre = new int[log2(n) + 1][n];
        dfs(root, -1, 0);
        for (int k = 0; k + 1 < pre.length; k++) {
            for (int v = 0; v < n; v++) {
                if (pre[k][v] < 0) pre[k + 1][v] = -1;
                else pre[k + 1][v] = pre[k][pre[k][v]];
            }
        }
    }

    private static int log2(int b) {
        return 31 - Integer.numberOfLeadingZeros(b);
    }

    public void dfs(int v, int p, int d) {
        pre[0][v] = p;
        depth[v] = d;
        for (int u : vs[v])
            if (u != p) {
                dfs(u, v, d + 1);
            }
    }

    public int lca(int u, int v) {
        if (depth[u] > depth[v]) return lca(v, u);
        v = climb(v, depth[v] - depth[u]);
        if (u == v) return u;
        for (int k = pre.length - 1; k >= 0; k--) {
            if (pre[k][u] != pre[k][v]) {
                u = pre[k][u];
                v = pre[k][v];
            }
        }
        return pre[0][u];
    }

    public int climb(int v, int d) {
        for (int k = 0; k < pre.length; k++) {
            if ((d >> k & 1) != 0) v = pre[k][v];
        }
        return v;
    }
}
