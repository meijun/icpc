package main;

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
        int n = in.nextInt(100) + 1;
        int m = in.nextInt(26) + 1;
        char[] cs = new char[n];
        for (int i = 0; i < n; i++) {
            cs[i] = (char) (in.nextInt(m) + 'a');
        }
        input.append(String.valueOf(cs));
        String[] pre = new String[n + 1];
        String[] suf = new String[n + 1];
        for (int i = 0; i <= n; i++) {
            pre[i] = String.valueOf(cs, 0, i);
            suf[i] = String.valueOf(cs, i, n - i);
        }
        Set<String> res = new HashSet<>();
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                res.add(pre[i] + suf[j]);
            }
        }
        output.append(res.size());
        return new Test(input.toString(), output.toString());
    }
}
