package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskATestCase {

    Random in = new Random(0);
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 1000; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = 9;
        char[] s = new char[n];
        for (int i = 0; i < n; i++) {
            int t = in.nextInt(3);
            if (t == 0) {
                s[i] = '(';
            } else if (t == 1) {
                s[i] = ')';
            } else {
                s[i] = '?';
            }
        }
        input.append(s);
        Set<Integer> set = new HashSet<>();
        dfs(n, s, 0, set);
        output.append(set.size());
        return new Test(input.toString(), output.toString());
    }

    private void dfs(int n, char[] s, int ni, Set<Integer> set) {
        if (ni == n) {
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i; j < n; j++) {
                    if (s[j] == '(') sum++;
                    if (s[j] == ')') sum--;
                    if (sum < 0) break;
                    if (sum == 0) set.add(i * n + j);
                }
            }
        } else {
            if (s[ni] != '?') {
                dfs(n, s, ni + 1, set);
            } else {
                s[ni] = '(';
                dfs(n, s, ni + 1, set);
                s[ni] = ')';
                dfs(n, s, ni + 1, set);
                s[ni] = '?';
            }
        }
    }
}
