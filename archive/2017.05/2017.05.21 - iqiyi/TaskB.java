package main;

import lib.misc.Scanner;

import java.io.PrintWriter;
import java.util.Stack;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) is[i] = in.nextInt();
        out.println(maxRect(is));
    }

    long maxRect(int[] _hs) {
        int n = _hs.length;
        long res = 0;
        int[] hs = new int[n + 1];//n 番に番兵を置く
        System.arraycopy(_hs, 0, hs, 0, n);
        Stack<Integer> sx = new Stack<Integer>();
        Stack<Integer> sh = new Stack<Integer>();
        sh.push(0);
        for (int i = 0; i <= n; i++) {
            int x = i;
            while (sh.peek() > hs[i]) {
                res = Math.max(res, (long) sh.pop() * (i - (x = sx.pop())));
            }
            sx.push(x);
            sh.push(hs[i]);
        }
        return res;
    }
}
