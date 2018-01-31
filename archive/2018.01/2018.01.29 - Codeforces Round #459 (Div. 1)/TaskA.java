package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        char[] s = in.next().toCharArray();
        int n = s.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int left = 0;
            int right = 0;
            int hard = 0;
            for (int j = i; j < n; j++) {
                if (s[j] == '(') {
                    hard++;
                    sum++;
                } else if (s[j] == ')') {
                    hard--;
                    sum--;
                    if (sum + left < 0) break;
                } else {
                    hard--;
                    left++;
                    if (sum + left > 0) right++;
                }
                if (j > i && (j - i + 1) % 2 == 0) {
                    if (-sum <= left && sum <= right && hard <= 0) res++;
                }
                hard = Math.max(0, hard);
//                $.debug(i, j, res, sum, left, right);
            }
//            $.debug(i, res);
        }
        out.println(res);
    }
}
