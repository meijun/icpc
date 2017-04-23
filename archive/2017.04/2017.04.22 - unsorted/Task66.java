package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task66 {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        n++;
        int[] ten = new int[10];
        for (int i = 0; i < 10; i++) ten[i] = (i == 0 ? 1 : ten[i - 1] * 10);
        int[][] n9 = new int[10][10]; // [len][fb]
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 9; j++) {
                if (i <= 1) n9[i][j] = 0;
                else {
                    for (int k = 0; k <= 9; k++) {
                        n9[i][j] += n9[i - 1][k];
                    }
                    if (j == 6) {
                        n9[i][j] -= n9[i - 1][6];
                        n9[i][j] += ten[i - 2];
                    }
                }
            }
        }

        char[] nc = Integer.toString(n).toCharArray();
        int[] ni = new int[nc.length];
        for (int i = 0; i < nc.length; i++) ni[i] = nc[i] - '0';

        boolean c66 = false;
        boolean l6 = false;
        int res = 0;
        for (int i = 0; i < ni.length; i++) {
            if (c66) {
                for (int f = 0; f < ni[i]; f++) {
                    res += ten[ni.length - i - 1];
                }
            } else {
                for (int f = 0; f < ni[i]; f++) {
                    res += n9[ni.length - i][f];
                }
                if (l6 && ni[i] > 6) {
                    res -= n9[ni.length - i][6];
                    res += ten[ni.length - i - 1];
                }
            }
            if (i > 0 && ni[i - 1] == 6 && ni[i] == 6) {
                c66 = true;
            }
            l6 = (ni[i] == 6);
        }
        out.println(res);
    }
}
