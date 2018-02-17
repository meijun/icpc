package main;

import lib.ds.UnionFindSet;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        char[] as = in.next().toCharArray();
        char[] bs = in.next().toCharArray();
        UnionFindSet uf = new UnionFindSet(26);
        for (int i = 0; i < n; i++) {
            if (as[i] != bs[i]) {
                uf.union(as[i] - 'a', bs[i] - 'a');
            }
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (uf.find(i) != i) res++;
        }
        out.println(res);
        for (int i = 0; i < 26; i++) {
            if (uf.find(i) != i) {
                out.println(((char) (i + 'a')) + " " + ((char) (uf.find(i) + 'a')));
            }
        }
    }
}
