package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskDTestCase {

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
        int n = 200000;
        int m = 20000;
        for (int i = 0; i < n; i++) {
            input.append("1 ");
        }
        input.append(ln);
        List<Integer>[] links = new List[m];
        for (int i = 0; i < m; i++) {
            links[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            links[in.nextInt(m)].add(i);
            links[in.nextInt(m)].add(i);
        }
        for (int i = 0; i < m; i++) {
            input.append(links[i].size());
            for (int j : links[i]) {
                input.append(" " + (j + 1));
            }
            input.append(ln);
        }
        output.append("YES");
        return new Test(input.toString(), output.toString());
    }
}
