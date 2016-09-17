package io.github.meijun.ds;

/**
 * Union Find Set
 * Created by meijun on 9/3/2014.
 */
public class UnionFindSet {
    public int[] pre;

    public UnionFindSet(int n) {
        pre = new int[n];
        for (int i = 0; i < n; i++) pre[i] = i;
    }

    public int find(int id) {
        if (pre[id] != id) pre[id] = find(pre[id]);
        return pre[id];
    }

    public void union(int u, int v) {
        pre[find(u)] = find(v);
    }

    public boolean isSame(int u, int v) {
        return find(u) == find(v);
    }

    public boolean isRoot(int id) {
        return pre[id] == id;
    }
}
