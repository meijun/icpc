package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class Task6215TestCase {

    Random in = new Random(0);
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 1; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        input.append(1 + ln);
        int n = in.nextInt(100) + 1;
        int[] is = new int[n];
        input.append(n + ln);
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt(n);
            input.append(is[i] + " ");
        }
        input.append(ln);
        for (;;) {
            boolean[] del = new boolean[n];
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (i > 0 && is[i - 1] > is[i]) {
                    del[i - 1] = true;
                    del[i] = true;
                    found = true;
                }
            }
            if (!found) break;
            int nn = 0;
            for (int i = 0; i < n; i++) {
                if (!del[i]) nn++;
            }
            int[] isn = new int[nn];
            nn = 0;
            for (int i = 0; i < n; i++) {
                if (!del[i]) isn[nn++] = is[i];
            }
            n = nn;
            is = isn;
        }
        output.append(n + ln);
        for (int i = 0; i < n; i++) {
            output.append(is[i] + " ");
        }
        return new Test(input.toString(), output.toString());
    }
}
