package main;

import lib.math.GCD;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskBTestCase {

    Random in = new Random(0);
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 10; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(1000) + 1;
        long k = in.nextInt(1000) + 1;
        input.append(n + " " + k + ln);
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int x = 1;
            for (char c : Integer.toString(i).toCharArray()) {
                x *= c - '0';
            }
            f[i] = x;
        }
        int max = 0;
        for (int i : f) max = Math.max(max, i);
        int[][] gcd = new int[max + 1][max + 1];
        for (int i = 1; i <= max; i++) {
            for (int j = 1; j <= max; j++) {
                gcd[i][j] = GCD.gcd(i, j);
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (f[i] * f[j] > 0 && gcd[f[i]][f[j]] <= k) {
                    res++;
                }
            }
        }
        output.append(res);
        return new Test(input.toString(), output.toString());
    }
}
