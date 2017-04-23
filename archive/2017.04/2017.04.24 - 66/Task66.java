package main;



import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Task66 {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
//        $.debug(getRes(660));
        int n = in.nextInt();
//        int bf = 0;
//        for (int i = 1; i <= n; i++) {
////            if (i == 1) break;
//            int res = getRes(i);
//            bf += fit(i);
//            if (res != bf) {
//                $.debug(i, res, bf);
//                break;
//            }
//        }
        out.println(getRes(n));
    }

    private int fit(int x) {
        String s = Integer.toString(x);
        int res = 0;
        boolean ok = false;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length()&& s.charAt(i) == '6' && s.charAt(i + 1) == '6') ok = true;
            if (s.charAt(i) == '0') res++;
        }
        return ok ? res : 0;
    }

    private int getRes(int n) {
        n++;
        int[] ten = new int[10];
        for (int i = 0; i < 10; i++) ten[i] = (i == 0 ? 1 : ten[i - 1] * 10);
        int[] zero = new int[10];
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= 9; j++) zero[i] += zero[i - 1];
            zero[i] += zero[i - 1] + ten[i - 1];
        }
        int[][] n9 = new int[10][10]; // [len][fb]
        int[][] n9c = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 9; j++) {
                if (i <= 1) n9c[i][j] = n9[i][j] = 0;
                else {
                    for (int k = 0; k <= 9; k++) {
                        n9[i][j] += n9[i - 1][k];
                        n9c[i][j] += n9c[i - 1][k];
                    }
                    if (j == 6) {
                        n9[i][j] -= n9[i - 1][6];
                        n9[i][j] += zero[i - 2];
                        n9c[i][j] -= n9c[i - 1][6];
                        n9c[i][j] += ten[i - 2];
                    }
                    if (j == 0) {
                        n9[i][j] += n9c[i][j];
                    }
                }
            }
        }

        char[] nc = Integer.toString(n).toCharArray();
        int[] ni = new int[nc.length];
        for (int i = 0; i < nc.length; i++) ni[i] = nc[i] - '0';

        boolean c66 = false;
        boolean l6 = false;
        int pre0 = 0;
        int res = 0;
        for (int i = 0; i < ni.length; i++) {
            if (c66) {
                for (int f = 0; f < ni[i]; f++) {
                    res += zero[ni.length - i - 1];
                    res += pre0 * ten[ni.length - i - 1];
                    if (f == 0) res += ten[ni.length - i - 1];
                }
            } else {
                for (int f = 0; f < ni[i]; f++) {
                    res += n9[ni.length - i][f];
                    res += pre0 * n9c[ni.length - i][f];
                }
                if (l6 && ni[i] > 6) {
                    res -= n9[ni.length - i][6];
                    res -= pre0 * n9c[ni.length - i][6];
                    res += zero[ni.length - i - 1];
                    res += pre0 * ten[ni.length - i - 1];
                }
            }
            if (i > 0 && ni[i - 1] == 6 && ni[i] == 6) {
                c66 = true;
            }
            l6 = (ni[i] == 6);
            if (ni[i] == 0) pre0++;
//            $.debug(i, res);
        }
        int delta = 0;
        for (int i = 1; i < ni.length; i++) {
            for (int j = 1; j <= 9; j++) {
                delta += i * n9c[ni.length - i][j];
            }
        }
//        $.debug("res", res, "delta", delta);
        res -= delta;
        return res;
    }

}
