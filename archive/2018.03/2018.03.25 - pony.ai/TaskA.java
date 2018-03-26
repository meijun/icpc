package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        while (in.hasNext()) {
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        String t = in.next();
        State[] ss = buildSuffixAutomaton(t);
        dfs(ss, 0);
        int n = in.nextInt();
        int grundy = 0;
        for (int i = 0; i < n; i++) {
            String s = in.next();
            int crt = 0;
            for (char c : s.toCharArray()) {
                c -= 'a';
                crt = ss[crt].next[c];
            }
            grundy ^= ss[crt].grundy;
        }
        out.println(grundy != 0 ? "Alice" : "Bob");
    }

    private int dfs(State[] ss, int c) {
        if (ss[c].grundy != -1) return ss[c].grundy;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            if (ss[c].next[i] != -1) {
                set.add(dfs(ss, ss[c].next[i]));
            }
        }
        ss[c].grundy = 0;
        while (set.contains(ss[c].grundy)) ss[c].grundy++;
        return ss[c].grundy;
    }

    public static class State {
        int length;
        int link;
        int[] next = new int[26];

        {
            Arrays.fill(next, -1);
        }

        int endpos;
        List<Integer> ilink = new ArrayList<>(0);

        int grundy = -1;
    }

    public static State[] buildSuffixAutomaton(String s) {
        int n = s.length();
        State[] st = new State[Math.max(2, 2 * n - 1)];
        st[0] = new State();
        st[0].link = -1;
        st[0].endpos = -1;
        int last = 0;
        int size = 1;
        for (char c : s.toCharArray()) {
            c -= 'a';
            int cur = size++;
            st[cur] = new State();
            st[cur].length = st[last].length + 1;
            st[cur].endpos = st[last].length;
            int p;
            for (p = last; p != -1 && st[p].next[c] == -1; p = st[p].link) {
                st[p].next[c] = cur;
            }
            if (p == -1) {
                st[cur].link = 0;
            } else {
                int q = st[p].next[c];
                if (st[p].length + 1 == st[q].length)
                    st[cur].link = q;
                else {
                    int clone = size++;
                    st[clone] = new State();
                    st[clone].length = st[p].length + 1;
                    st[clone].next = st[q].next.clone();
                    st[clone].link = st[q].link;
                    for (; p != -1 && st[p].next[c] == q; p = st[p].link)
                        st[p].next[c] = clone;
                    st[q].link = clone;
                    st[cur].link = clone;
                    st[clone].endpos = -1;
                }
            }
            last = cur;
        }
        for (int i = 1; i < size; i++) {
            st[st[i].link].ilink.add(i);
        }
        return Arrays.copyOf(st, size);
    }


    private void solve2(Scanner in, PrintWriter out) {
        String t = in.next();
        Node root = buildSuffixTree(t);
        dfs(root);
        int n = in.nextInt();
        int grundy = 0;
        for (int i = 0; i < n; i++) {
            String s = in.next();
            Node crt = root;
            for (int j = 0; ; ) {
                int nj = j + crt.end - crt.begin;
                if (nj >= s.length()) {
                    grundy ^= crt.grundy;
                    break;
                }
                crt = crt.children[s.charAt(nj) - 'a'];
                j = nj;
            }
        }
        out.println(grundy != 0 ? "Alice" : "Bob");
    }

    private void dfs(Node root) {
        Set<Integer> set = new HashSet<>();
        for (Node ch : root.children) {
            if (ch != null) {
                dfs(ch);
                set.add(ch.grundy);
            }
        }
        while (set.contains(root.grundy)) root.grundy++;
    }

    public static Node buildSuffixTree(String s) {
        int n = s.length();
        byte[] a = new byte[n];
        for (int i = 0; i < n; i++)
            a[i] = (byte) (s.charAt(i) - 'a');
        Node root = new Node(0, 0, 0, null);
        Node cn = root;
        // root.suffixLink must be null, but that way it gets more convenient processing
        root.suffixLink = root;
        Node needsSuffixLink = null;
        int lastRule = 0;
        int j = 0;
        for (int i = -1; i < n - 1; i++) {// strings s[j..i] are already in tree, add s[i+1] to it
            int cur = a[i + 1]; // last char of current string
            for (; j <= i + 1; j++) {
                int curDepth = i + 1 - j;
                if (lastRule != 3) {
                    cn = cn.suffixLink != null ? cn.suffixLink : cn.parent.suffixLink;
                    int k = j + cn.depth;
                    while (curDepth > 0 && !cn.contains(curDepth - 1)) {
                        k += cn.end - cn.begin;
                        cn = cn.children[a[k]];
                    }
                }
                if (!cn.contains(curDepth)) { // explicit node
                    if (needsSuffixLink != null) {
                        needsSuffixLink.suffixLink = cn;
                        needsSuffixLink = null;
                    }
                    if (cn.children[cur] == null) {
                        // no extension - add leaf
                        cn.children[cur] = new Node(i + 1, n, curDepth, cn);
                        lastRule = 2;
                    } else {
                        cn = cn.children[cur];
                        lastRule = 3; // already exists
                        break;
                    }
                } else { // implicit node
                    int end = cn.begin + curDepth - cn.depth;
                    if (a[end] != cur) { // split implicit node here
                        Node newn = new Node(cn.begin, end, cn.depth, cn.parent);
                        newn.children[cur] = new Node(i + 1, n, curDepth, newn);
                        newn.children[a[end]] = cn;
                        cn.parent.children[a[cn.begin]] = newn;
                        if (needsSuffixLink != null) {
                            needsSuffixLink.suffixLink = newn;
                        }
                        cn.begin = end;
                        cn.depth = curDepth;
                        cn.parent = newn;
                        cn = needsSuffixLink = newn;
                        lastRule = 2;
                    } else if (cn.end != n || cn.begin - cn.depth < j) {
                        lastRule = 3;
                        break;
                    } else {
                        lastRule = 1;
                    }
                }
            }
        }
        root.suffixLink = null;
        return root;
    }

    public static class Node {
        public int begin;
        public int end;
        public int depth; // distance in characters from tree root to this node
        public Node parent;
        public Node[] children;
        public Node suffixLink; // null means link to root
        public int grundy;

        public Node(int begin, int end, int depth, Node parent) {
            children = new Node[26];
            this.begin = begin;
            this.end = end;
            this.parent = parent;
            this.depth = depth;
        }

        public boolean contains(int d) {
            return depth <= d && d < depth + (end - begin);
        }
    }

}
