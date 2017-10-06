package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskInversionTestCase {

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
        input.append(n + ln);
        int[] x = new int[n];
        int[] y = new int[n];
        int bound = 100;
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt(bound) + 1;
            y[i] = in.nextInt(bound) + 1;
            input.append(x[i] + " " + y[i] + ln);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (x[i] > x[j] && y[i] > y[j]) {
                    res++;
                }
            }
        }
        output.append(res);
        return new Test(input.toString(), output.toString());
    }
}
