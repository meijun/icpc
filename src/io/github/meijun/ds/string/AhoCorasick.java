package io.github.meijun.ds.string;

import java.util.*;

/**
 * AC Auto Machine.
 * Created by meijun on 10/3/2014.
 */
public class AhoCorasick {
    public int m;
    public Node root;

    public AhoCorasick(char[][] ps) {
        m = ps.length;
        root = new Node();
        for (int i = 0; i < m; i++) {
            Node t = root;
            for (char c : ps[i]) {
                if (!t.cs.containsKey(c)) t.cs.put(c, new Node());
                t = t.cs.get(c);
            }
            t.accept.add(i);
        }
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            Node t = que.poll();
            for (Map.Entry<Character, Node> e : t.cs.entrySet()) {
                char c = e.getKey();
                Node u = e.getValue();
                que.offer(u);
                Node r = t.fail;
                while (r != null && !r.cs.containsKey(c)) r = r.fail;
                if (r == null) u.fail = root;
                else u.fail = r.cs.get(c);
                u.accept.addAll(u.fail.accept);
            }
        }
    }

    public Map<Node, Integer> getNodeIndex(List<Node> ns) {
        Map<Node, Integer> index = new HashMap<>();
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        int crt = 0;
        while (!que.isEmpty()) {
            Node t = que.poll();
            ns.add(t);
            index.put(t, crt++);
            que.addAll(t.cs.values());
        }
        return index;
    }

    public int[] searchFrom(char[] t) {
        int n = t.length;
        int[] count = new int[m];
        Node u = root;
        for (char c : t) {
            while (u != null && !u.cs.containsKey(c)) u = u.fail;
            if (u == null) u = root;
            else u = u.cs.get(c);
            for (int j : u.accept) count[j]++;
        }
        return count;
    }

    public static class Node {
        public Map<Character, Node> cs = new TreeMap<>();
        public List<Integer> accept = new ArrayList<>();
        public Node fail;
    }
}
