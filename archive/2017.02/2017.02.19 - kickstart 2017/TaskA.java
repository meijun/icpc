package main;

import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {

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
        Name max = null;
        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            if (s.length() == 0) s = in.nextLine();
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) set.add(c);
            if (set.contains(' ')) set.remove(' ');
            Name name = new Name(set.size(), s);
            if (max == null || max.cnt < name.cnt || max.cnt == name.cnt && max.s.compareTo(name.s) > 0) {
                max = name;
            }
        }
        out.println(max.s);
    }

    class Name {
        int cnt;
        String s;

        public Name(int cnt, String s) {
            this.cnt = cnt;
            this.s = s;
        }
    }
}
