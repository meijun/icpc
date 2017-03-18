package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        char[] s = in.next().toCharArray();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (goLeft(s, i) < goRight(s, i)) {
                res += doGoLeft(s, i);
            } else {
                res += doGoRight(s, i);
            }
//            $.debug(s, i, res);
        }
        out.println(res);
    }

    private int goRight(char[] s, int x) {
        if (x == 0 || s[x] != 'K' || s[x - 1] != 'V') return 0;
        if (x + 1 >= s.length || s[x + 1] == 'K') return s.length;
        for (int i = x + 1; i < s.length; i++) {
            if (s[i] != 'V') return i - x;
        }
        return s.length;
    }
    private int doGoRight(char[] s, int x) {
        if (x == 0 || s[x] != 'K' || s[x - 1] != 'V') return 0;
        for (int i = x + 1; i < s.length; i++) {
            swap(s, i, i - 1);
            if (s[i] != 'V') return i - x;
        }
        return s.length;
    }

    private int goLeft(char[] s, int x) {
        if (x == 0 || s[x] != 'K' || s[x - 1] != 'V') return 0;
        for (int i = x - 1; i > 0; i--) {
            if (s[i - 1] != 'V') return x - i;
        }
        return x;
    }
    private int doGoLeft(char[] s, int x) {
//        $.debug(s, x);
        if (x == 0 || s[x] != 'K' || s[x - 1] != 'V') return 0;
        for (int i = x - 1; i > 0; i--) {
            swap(s, i, i + 1);
            if (s[i - 1] != 'V') return x - i;
        }
        swap(s, 0, 1);
        return x;
    }

    private void swap(char[] s, int x, int y) {
        char t = s[x]; s[x] = s[y]; s[y] = t;
    }
}
