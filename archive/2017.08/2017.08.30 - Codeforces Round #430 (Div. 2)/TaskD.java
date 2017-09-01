package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int N = Integer.highestOneBit(300000) << 1;
        int[] cnt = new int[N * 2];
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            cnt[N + a] = 1;
        }
        for (int i = N - 1; i > 0; i--) {
            cnt[i] = cnt[i * 2] + cnt[i * 2 + 1];
        }
        int x = 0;
        while (m-- > 0) {
            x ^= in.nextInt();
            int seg_id = 1;
            int bit = N >> 1;
//            $.debug("x", x, Integer.toBinaryString(x));
            while (bit > 0) {
                int bit_crt = (bit & x) != 0 ? 1 : 0;
                if (cnt[seg_id * 2 + bit_crt] < bit) {
                    seg_id = seg_id * 2 + bit_crt;
//                    $.debug(bit_crt, seg_id);
                } else {
                    seg_id = seg_id * 2 + (bit_crt ^ 1);
//                    $.debug(bit_crt ^ 1, seg_id);
                }
                bit >>= 1;
            }
            out.println((seg_id - N) ^ x);
        }
    }
}
