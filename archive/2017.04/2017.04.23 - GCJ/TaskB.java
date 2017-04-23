package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            $.debug(i);
            solve(in, out);
        }
    }
    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int r = in.nextInt();
        int o = in.nextInt();
        int y = in.nextInt();
        int g = in.nextInt();
        int b = in.nextInt();
        int v = in.nextInt();
        if (y + v == n || r + g == n || b + o == n) {
            if (y != 0) {
                String s = "YV";
                eq(out, y, v, s);
            } else if (r != 0) {
                eq(out, r, g, "RG");
            } else {
                eq(out, b, o, "BO");
            }
            return;
        }
        List<Color> cs = new ArrayList<>();
        if (g > 0 && r < g + 1 || v > 0 && y < v + 1 || o > 0 && b < o + 1) {
            out.println("IMPOSSIBLE");
            return;
        }
        while (r > g + 1 && g > 0) {
            cs.add(new Color('R', "RGR"));
            g--;
            r -= 2;
        }
        while (y > v + 1 && v > 0) {
            cs.add(new Color('V', "YVY"));
            v--;
            y -= 2;
        }
        while (b > o + 1 && o > 0) {
            cs.add(new Color('B', "BOB"));
            o--;
            b -= 2;
        }
        if (g > 0) {
            longColor(g, cs, 'R', 'G');
        } else if (r > 0) {
            for (int i = 0; i < r; i++) {
                cs.add(new Color('R', "R"));
            }
        }

        if (v > 0) {
            longColor(v, cs, 'Y', 'V');
        } else if (y > 0) {
            for (int i = 0; i < y; i++) {
                cs.add(new Color('Y', "Y"));
            }
        }

        if (o > 0) {
            longColor(o, cs, 'B', 'O');
        } else if (b > 0) {
            for (int i = 0; i < b; i++) {
                cs.add(new Color('B', "B"));
            }
        }
        List<Color>[] css = new List[3];
        for (int i = 0; i < css.length; i++) css[i] = new ArrayList<>();
        for (Color c : cs) {
            if (c.c == 'R') css[2].add(c);
            else if (c.c == 'Y') css[1].add(c);
            else css[0].add(c);
        }
        Arrays.sort(css, Comparator.comparingInt(List::size));
        int N = css[0].size() + css[1].size() + css[2].size();
        Color[] res = new Color[N];
        for (int i = 0; i < css[2].size(); i++) {
            if (i * 2 >= N) {
                out.println("IMPOSSIBLE");
                return;
            }
            res[i * 2] = css[2].get(i);
        }
        int K = 0;
        for (int i = 0; i < css[1].size(); i++) {
            int id = i * 2 + css[2].size() * 2;
            if (id >= N) {
                for (int j = i; j < css[1].size(); j++) {
                    for (int k = 0; k < N; k++) {
                        if (res[k] == null) {
                            res[k] = css[1].get(j);
                            break;
                        }
                    }
                }
                for (int j = 0; j < N; j++) {
                    if (res[j] == null) {
                        res[j] = css[0].get(K);
                        K++;
                    }
                }
                break;
            }
            res[id] = css[1].get(i);
        }
        for (int j = 0; j < N; j++) {
            if (res[j] == null) {
                res[j] = css[0].get(K);
                K++;
            }
        }
        boolean ok = true;
//        $.debug(css[0], css[1], css[2]);
//        $.debug(res);
        for (int i = 0; i < N; i++) {
//            $.debug(i, N);
            if (res[i].c == res[(i + 1) % N].c) ok = false;
        }
        if (!ok) out.println("IMPOSSIBLE");
        else {
            for (Color c : res) out.print(c.s);
            out.println();
        }
    }

    private void solve3(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int r = in.nextInt();
        int o = in.nextInt();
        int y = in.nextInt();
        int g = in.nextInt();
        int b = in.nextInt();
        int v = in.nextInt();
        if (y + v == n || r + g == n || b + o == n) {
            if (y != 0) {
                String s = "YV";
                eq(out, y, v, s);
            } else if (r != 0) {
                eq(out, r, g, "RG");
            } else {
                eq(out, b, o, "BO");
            }
            return;
        }
        List<Color> cs = new ArrayList<>();
        if (g > 0 && r < g + 1 || v > 0 && y < v + 1 || o > 0 && b < o + 1) {
            out.println("IMPOSSIBLE");
            return ;
        }
        if (g > 0) {
            longColor(g, cs, 'R', 'G');
            r -= g + 1;
        }
        if (r > 0) {
            for (int i = 0; i < r; i++) {
                cs.add(new Color('R', "R"));
            }
        }

        if (v > 0) {
            longColor(v, cs, 'Y', 'V');
            y -= v + 1;
        }
        if (y > 0) {
            for (int i = 0; i < y; i++) {
                cs.add(new Color('Y', "Y"));
            }
        }

        if (o > 0) {
            longColor(o, cs, 'B', 'O');
            b -= o + 1;
        }
        if (b > 0) {
            for (int i = 0; i < b; i++) {
                cs.add(new Color('B', "B"));
            }
        }
        List<Color>[] css = new List[3];
        for (int i = 0; i < css.length; i++) css[i] = new ArrayList<>();
        for (Color c : cs) {
            if (c.c == 'R') css[2].add(c);
            else if (c.c == 'Y') css[1].add(c);
            else css[0].add(c);
        }
        Arrays.sort(css, Comparator.comparingInt(List::size));
        int[] cnt = new int[]{r, y, b};
        Arrays.sort(cnt);
        if (cnt[0] != css[0].size() || cnt[1] != css[1].size() || cnt[2] != css[2].size()) {
            $.debug(cnt, css[0].size(), css[1].size(), css[2].size());
        }
        int N = css[0].size() + css[1].size() + css[2].size();
        Color[] res = new Color[N];
        for (int i = 0; i < css[2].size(); i++) {
            if (i * 2 >= N) {
                out.println("IMPOSSIBLE");
                return ;
            }
            res[i * 2] = css[2].get(i);
        }
        for (int i = 0; i < css[1].size(); i++) {
            int id = i * 2 + css[2].size() * 2;
            if (id >= N) {
                for (int k = 0; k < N; k++) {
                    if (res[k] == null) {
                        res[k] = css[1].get(i);
                        break;
                    }
                }
            } else {
                res[id] = css[1].get(i);
            }
        }
        for (int i = 0; i < css[0].size(); i++) {
            for (int k = 0; k < N; k++) {
                if (res[k] == null) {
                    res[k] = css[0].get(i);
                    break;
                }
            }
        }
        boolean ok = true;
//        $.debug(css[0], css[1], css[2]);
//        $.debug(res);
        for (int i = 0; i < N; i++) {
//            $.debug(i, N);
            if (res[i].c == res[(i + 1) % N].c) ok = false;
        }
        if (!ok) out.println("IMPOSSIBLE");
        else {
            for (Color c : res) out.print(c.s);
            out.println();
        }
    }

    private void eq(PrintWriter out, int y, int v, String s) {
        if (y == v) {
            for (int i = 0; i < y; i++) {
                out.print(s);
            }
            out.println();
        } else {
            out.println("IMPOSSIBLE");
        }
    }

    private void longColor(int g, List<Color> cs, char r, char g2) {
        char[] c = new char[g * 2 + 1];
        for (int i = 0; i < g; i++) {
            c[i * 2] = r;
            c[i * 2 + 1] = g2;
        }
        c[g * 2] = r;
        cs.add(new Color(r, String.valueOf(c)));
    }

    private void solve2(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int r = in.nextInt();
        int o = in.nextInt();
        int y = in.nextInt();
        int g = in.nextInt();
        int b = in.nextInt();
        int v = in.nextInt();
        char[] cs = new char[n];
        int[] is = new int[]{r, y, b};
        Arrays.sort(is);
        for (int i = 0; i < is[2]; i++) {
            if (i * 2 >= n) {
                out.println("IMPOSSIBLE");
                return ;
            }
            cs[i * 2] = '2';
        }
        for (int i = 0; i < is[1]; i++) {
            int id = i * 2 + is[2] * 2;
            if (id >= n) {
                for (int j = i; j < is[1]; j++) {
                    for (int k = 0; k < n; k++) {
                        if (cs[k] != '2' && cs[k] != '1') {
                            cs[k] = '1';
                            break;
                        }
                    }
                }
                for (int j = 0; j < n; j++) {
                    if (cs[j] != '2' && cs[j] != '1') {
                        cs[j] = '0';
                    }
                }
                break;
            }
            cs[id] = '1';
        }
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            if (cs[i] == cs[(i + 1) % n]) ok = false;
        }
        if (!ok) out.println("IMPOSSIBLE");
        else {
            char c2 = (r == is[2] ? 'R' : y == is[2] ? 'Y' : 'B');
            char c0 = (r == is[0] ? 'R' : y == is[0] ? 'Y' : 'B');
            char c1 = (c2 != 'R' && c0 != 'R' ? 'R' : c2 != 'Y' && c0 != 'Y' ? 'Y' : 'B');
            if (is[2] == is[0]) {
                c0 = 'R'; c1 = 'Y'; c2 = 'B';
            }
            for (int i = 0; i < n; i++) {
                out.print(cs[i] == '2' ? c2 : cs[i] == '1' ? c1 : c0);
            }
            out.println();
        }
    }
    class Color {
        char c;
        String s;

        public Color(char c, String s) {
            this.c = c;
            this.s = s;
        }

        @Override
        public String toString() {
            return s;
        }
    }
}
