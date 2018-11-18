package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        String[] fs = new String[p];
        for (int i = 0; i < p; i++) {
            fs[i] = in.next();
        }
        Node root = new Node();
        for (String s : fs) {
            insert(root, s);
        }
        long res = dfs(root, n);
        out.println((1L << n) - res);
    }

    long dfs(Node root, int depth) {
        long res = 0;
        if (root.end) {
            res += 1L << depth;
            return res;
        }
        if (root.left != null) {
            res += dfs(root.left, depth - 1);
        }
        if (root.right != null) {
            res += dfs(root.right, depth - 1);
        }
        return res;
    }

    void insert(Node root, String s) {
        Node crt = root;
        for (char c : s.toCharArray()) {
            if (c == 'R') {
                if (crt.left == null) {
                    crt.left = new Node();
                }
                crt = crt.left;
            } else {
                if (crt.right == null) {
                    crt.right = new Node();
                }
                crt = crt.right;
            }
        }
        crt.end = true;
    }

    class Node {
        Node left;
        Node right;
        boolean end;

        public Node() {
        }

        public Node(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }
}
