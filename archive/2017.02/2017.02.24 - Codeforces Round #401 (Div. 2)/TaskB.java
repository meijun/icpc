package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        char[] a = in.next().toCharArray();
        char[] b = in.next().toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        int j = n - 1;
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (j >= 0 && a[j] > b[i]) j--;
            if (j >= 0) res++;
            j--;
        }
        out.println(n - res);
        res = 0;
        j = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (j >= 0 && a[j] >= b[i]) j--;
            if (j >= 0) res++;
            j--;
        }
        out.println(res);
    }
}
