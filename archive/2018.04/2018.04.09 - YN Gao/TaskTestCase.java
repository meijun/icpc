package main;

import lib.misc.$;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskTestCase {

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
        int n = in.nextInt(11) + 1;
        int[] rs = new int[n];
        int[] ds = new int[n];
        input.append(n + ln);
        for (int i = 0; i < n; i++) {
            rs[i] = in.nextInt(n) + 1;
            ds[i] = in.nextInt(n) + 1;
            input.append(rs[i] + " " + ds[i] + ln);
        }
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        int res = Integer.MAX_VALUE;
        do {
            int tmp = 0;
            int d0 = 0, d1 = 0;
            int t = 0;
            for (int i : p) {
                if (d0 == 0) {
                    d0 = ds[i];
                    t += rs[i];
                } else {
                    d1 = ds[i];
                    t += rs[i];
                }
                if (d0 != 0 && d1 != 0) {
                    t += Math.min(d0, d1);
                    tmp += t;
                    d0 = Math.max(d0, d1);
                    d1 = 0;
                }
            }
            t += d0;
            tmp += t;
            res = Math.min(res, tmp);
        } while ($.nextPermutation(p));
        output.append(res);
        return new Test(input.toString(), output.toString());
    }
}
