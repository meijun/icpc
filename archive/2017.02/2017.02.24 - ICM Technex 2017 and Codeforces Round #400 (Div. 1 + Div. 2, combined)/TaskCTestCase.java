package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskCTestCase {

    Random in = new Random(0);
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 0; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = 100000;
        input.append(n + " " + 2 + ln);
        for (int i = 0; i < n; i++) {
            input.append(i + " ");
        }
        return new Test(input.toString(), output.toString());
    }
}
