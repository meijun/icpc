package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class TaskE {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        char[][] is = new char[n][];
        for (int i = 0; i < n ; i++) {
            is[i] = in.next().toCharArray();
        }
        Map<String, Integer> map = new HashMap<>();
        for (int j = 0; j < m; j++) {
            char[] js = new char[n];
            for (int i = 0; i < n; i++) js[i] = is[i][j];
            map.merge(String.valueOf(js), 1, Integer::sum);
        }
        $.debug(map);
        out.println(4);
        Set<BigInteger> bs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            BigInteger b = new BigInteger(String.valueOf(is[i]), 2);
            bs.add(b);
        }
        String mm = "";
        for (int i = 0; i < m; i++) mm += "1";
        BigInteger M = new BigInteger(mm, 2);
        for (;;) {
            int size = bs.size();
            $.debug(size);
            Set<BigInteger> nbs = new HashSet<>();
            for (BigInteger b : bs) {
                nbs.add(b);
                nbs.add(b.xor(M));
                for (BigInteger c : bs) {
                    nbs.add(b.and(c));
                }
            }
            if (nbs.size() == bs.size()) break;
            bs = nbs;
        }
//        for (BigInteger b : bs) $.debug(b.toString(2));
    }
}
