package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskF {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Node[] ns = new Node[n];
        for (int i = 0; i < n; i++) {
            ns[i] = new Node();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            ns[u].es.put(in.next().charAt(0), ns[v]);
        }
        Node root = ns[0];

    }
    class Node {
        TreeMap<Character, Node> es = new TreeMap<>();
    }
}
