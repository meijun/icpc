package main;

import lib.geo.P;
import lib.misc.$;
import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.nextInt();
        }
//        int S = 60;
//        int[] sg = new int[S + 1];
//        Map<State, Integer> dp = new HashMap<>();
//        for (int i = 1; i <= S; i++) {
//            sg[i] = dfs(dp, new State(i, 0));
//        }
        int[] sg = {0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10};
        int sum = 0;
        for (int i : s) sum ^= sg[i];
//        $.debug(sg);
        out.println(sum == 0 ? "YES" : "NO");
    }

    private Integer dfs(Map<State, Integer> dp, State state) {
        if (dp.containsKey(state)) return dp.get(state);
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= state.s; i++) if ((state.ban & (1 << i)) == 0) {
            set.add(dfs(dp, new State(state.s - i, state.ban | (1 << i))));
        }
        for (int i = 0; ; i++) {
            if (!set.contains(i)) {
                dp.put(state, i);
                return i;
            }
        }
    }

    class State {
        int s;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (s != state.s) return false;
            return ban == state.ban;
        }

        @Override
        public int hashCode() {
            int result = s;
            result = 31 * result + (int) (ban ^ (ban >>> 32));
            return result;
        }

        long ban;

        public State(int s, long ban) {
            this.s = s;
            this.ban = ban;
        }
    }
}
