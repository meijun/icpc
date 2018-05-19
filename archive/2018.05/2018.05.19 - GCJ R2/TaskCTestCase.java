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
        for (int i = 0; i < 100; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = 3;
        int[][] map = new int[n][n];
        input.append(1 + ln);
        input.append(n + ln);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sig = in.nextBoolean() ? 1 : -1;
                int v = in.nextInt(n) + 1;
                v *= sig;
                map[i][j] = v;
                input.append(v + " ");
            }
            input.append(ln);
        }
        for (int i = 0; i < n * n; i++) {
            if (solved(n, map, i)) {
                output.append("Case #1: ");
                output.append(i + ln);
                break;
            }
        }
        return new Test(input.toString(), output.toString());
    }

    private boolean solved(int n, int[][] map, int res) {
        return dfs(n, map, 0, 0, res);
    }

    private boolean dfs(int n, int[][] map, int x, int y, int remain) {
        if (remain == 0) {
            return fit(n, map);
        }
        if (x == n) return false;
        if (y == n) return dfs(n, map, x + 1, 0, remain);
        if (dfs(n, map, x, y + 1, remain)) return true;
        int old = map[x][y];
        for (int i = -n; i <= n; i++) {
            map[x][y] = i;
            if (i != 0 && i != old && fit1(n, map, x, y)) {
                if (dfs(n, map, x, y + 1, remain - 1)) {
                    return true;
                }
            }
        }
        map[x][y] = old;
        return false;
    }

    private boolean fit1(int n, int[][] map, int x, int y) {
        for (int i = 0; i < n; i++) {
            if (i != x && map[x][y] == map[i][y] || i != y && map[x][y] == map[x][i]) {
                return false;
            }
        }
        return true;
    }

    private boolean fit(int n, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i != k && map[i][j] == map[k][j] || j != k && map[i][j] == map[i][k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
