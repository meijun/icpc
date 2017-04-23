package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class Task66TestCase {

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
        int n = in.nextInt(100000);
//        n = Integer.parseInt(((in.nextInt(9) + 1) + "99999").substring(0, in.nextInt(5) + 1));
//        n = 69761;
        input.append(n);
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (fit(i)) {
                cnt++;
            }
        }
        output.append(cnt);
        return new Test(input.toString(), output.toString());
    }

    private boolean fit(int x) {
        String s = Integer.toString(x);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '6' && s.charAt(i + 1) == '6') return true;
        }
        return false;
    }
}
