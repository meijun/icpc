package lib.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Bridge.
 * Created by meijun on 7/15/2014.
 */
public class Bridge {

    public static List<E> bridge;

    public static List<E> connection(V[] vs) {
        bridge = new ArrayList<>();
        for (V v : vs)
            if (v.num < 0) {
                dfs(v, 0);
                if (v.count > 0) v.count--;
            }
        return bridge;
    }

    public static int dfs(V v, int c) {
        v.num = c;
        int low = c;
        boolean rev = false;
        for (V u : v.vs) {
            if (u.num < 0) {
                int t = dfs(u, c + 1);
                low = Math.min(low, t);
                if (v.num <= t) v.count++;
                if (v.num < t) bridge.add(new E(v, u));
            } else if (u.num != v.num - 1 || rev) low = Math.min(low, u.num);
            else rev = true;
        }
        return low;
    }

    public static class V {
        public List<V> vs = new ArrayList<>();
        public int num = -1;
        public int count;

        public void connect(V u) {
            vs.add(u);
            u.vs.add(this);
        }
    }

    public static class E {
        public V u;
        public V v;

        public E(V u, V v) {
            this.u = u;
            this.v = v;
        }
    }
}
