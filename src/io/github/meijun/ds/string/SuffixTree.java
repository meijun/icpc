package io.github.meijun.ds.string;

/**
 * Suffix Tree.
 * Created by meijun on 3/18/2015.
 */
public class SuffixTree {

    public static String ALPHABET;
    public static final String ALPHA_NUM = "$abcdefghijklmnopqrstuvwxyz1234567890\1\2";
    public static final String ALPHA = "$abcdefghijklmnopqrstuvwxyz\1\2";

    public static class Node {
        public int begin;
        public int end;
        public int depth; // distance in characters from tree root to this node
        public Node parent;
        public Node[] children;
        public Node suffixLink; // null means link to root

        public Node(int begin, int end, int depth, Node parent) {
            children = new Node[ALPHABET.length()];
            this.begin = begin;
            this.end = end;
            this.parent = parent;
            this.depth = depth;
        }

        public boolean contains(int d) {
            return depth <= d && d < depth + (end - begin);
        }
    }

    public static Node buildSuffixTree(String s, String alphabet) {
        ALPHABET = alphabet;
        int n = s.length();
        byte[] a = new byte[n];
        for (int i = 0; i < n; i++)
            a[i] = (byte) ALPHABET.indexOf(s.charAt(i));
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
}
