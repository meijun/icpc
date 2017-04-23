package main;



import lib.misc.$;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class Task66TestCase {

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
        int n = in.nextInt(100000);
//        n = Integer.parseInt(((in.nextInt(9) + 1) + "99999").substring(0, in.nextInt(5) + 1));
//        n = 1999 + 1;
        input.append(n);
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            cnt += fit(i);
        }
        output.append(cnt);
        return new Test(input.toString(), output.toString());
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
}
