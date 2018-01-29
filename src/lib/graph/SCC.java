package lib.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * SCC.
 * Created by meijun on 9/8/2014.
 */
public class SCC {

    public static int n;
    public static V[] us;

    public static int scc(V[] vs) {
        n = vs.length;
        us = new V[n];
        for (V v : vs) v.visit = false;
        for (V v : vs) if (!v.visit) dfs(v);
        for (V v : vs) v.visit = false;
        for (V u : us) if (!u.visit) dfsRev(u, n++);
        return n;
    }

    public static void dfs(V v) {
        v.visit = true;
        for (V u : v.fs) if (!u.visit) dfs(u);
        us[--n] = v;
    }

    public static void dfsRev(V v, int k) {
        v.visit = true;
        for (V u : v.rs) if (!u.visit) dfsRev(u, k);
        v.comp = k;
    }

    public static class V {
        public boolean visit;
        public int comp;
        public List<V> fs = new ArrayList<>();
        public List<V> rs = new ArrayList<>();

        public void add(V u) {
            fs.add(u);
            u.rs.add(this);
        }
    }
}
