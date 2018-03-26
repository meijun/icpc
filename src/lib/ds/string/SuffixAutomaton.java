package lib.ds.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuffixAutomaton {
    /**
     * See https://sites.google.com/site/indy256/algo/suffix_automaton
     */

    public static class State {
        int length;
        int link;
        int[] next = new int[26];

        {
            Arrays.fill(next, -1);
        }

        int endpos;
        List<Integer> ilink = new ArrayList<>(0);
    }

    public static State[] buildSuffixAutomaton(String s) {
        int n = s.length();
        State[] st = new State[Math.max(2, 2 * n - 1)];
        st[0] = new State();
        st[0].link = -1;
        st[0].endpos = -1;
        int last = 0;
        int size = 1;
        for (char c : s.toCharArray()) {
            c -= 'a';
            int cur = size++;
            st[cur] = new State();
            st[cur].length = st[last].length + 1;
            st[cur].endpos = st[last].length;
            int p;
            for (p = last; p != -1 && st[p].next[c] == -1; p = st[p].link) {
                st[p].next[c] = cur;
            }
            if (p == -1) {
                st[cur].link = 0;
            } else {
                int q = st[p].next[c];
                if (st[p].length + 1 == st[q].length)
                    st[cur].link = q;
                else {
                    int clone = size++;
                    st[clone] = new State();
                    st[clone].length = st[p].length + 1;
                    st[clone].next = st[q].next.clone();
                    st[clone].link = st[q].link;
                    for (; p != -1 && st[p].next[c] == q; p = st[p].link)
                        st[p].next[c] = clone;
                    st[q].link = clone;
                    st[cur].link = clone;
                    st[clone].endpos = -1;
                }
            }
            last = cur;
        }
        for (int i = 1; i < size; i++) {
            st[st[i].link].ilink.add(i);
        }
        return Arrays.copyOf(st, size);
    }
}
