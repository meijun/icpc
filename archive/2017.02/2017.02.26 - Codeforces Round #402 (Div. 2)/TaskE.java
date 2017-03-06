package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {

    private BitSet one;
    private BitSet zero;
    private int m;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        m = in.nextInt();
        Map<String, Item> env = new HashMap<>();
        env.put("?", new Item(null, null, null, VAR));
        List<Item> sums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String v = in.next();
            in.next();
            String a = in.next();
            if (a.charAt(0) == '0' || a.charAt(0) == '1') {
                Item crt = new Item(bitSet(a), null, null, CONST);
                env.put(v, crt);
            } else {
                char op = in.next().charAt(0);
                String b = in.next();
                Item crt = new Item(null, env.get(a), env.get(b), op == 'O' ? OR : op == 'A' ? AND : XOR);
                env.put(v, crt);
            }
            sums.add(env.get(v));
        }
        one = new BitSet(m);
        one.flip(0, m);
        zero = new BitSet(m);
        for (Item i : sums) {
            dfs(i);
        }
        int[] min = new int[m];
        int[] max = new int[m];
        for (int i = 0; i < m; i++) {
            int s0 = 0, s1 = 0;
            for (Item it : sums) {
                s0 += it.zero.get(i) ? 1 : 0;
                s1 += it.one.get(i) ? 1 : 0;
            }
            if (s0 <= s1) {
                min[i] = 0;
            } else {
                min[i] = 1;
            }
            if (s0 >= s1) {
                max[i] = 0;
            } else {
                max[i] = 1;
            }
        }
        for (int i = 0; i < m; i++) {
            out.print(min[i]);
        }
        out.println();
        for (int i = 0; i < m; i++) {
            out.print(max[i]);
        }
        out.println();
    }

    private void dfs(Item crt) {
        if (crt.vis) return ;
        crt.vis = true;
        if (crt.type == CONST) {
            crt.one = crt.zero = crt.b;
        } else if (crt.type == VAR) {
            crt.one = one;
            crt.zero = zero;
        } else {
            if (crt.left != null) dfs(crt.left);
            if (crt.right != null) dfs(crt.right);
            if (crt.type == AND) {
                crt.one = (BitSet) crt.left.one.clone();
                crt.one.and(crt.right.one);
                crt.zero = (BitSet) crt.left.zero.clone();
                crt.zero.and(crt.right.zero);
            } else if (crt.type == OR) {
                crt.one = (BitSet) crt.left.one.clone();
                crt.one.or(crt.right.one);
                crt.zero = (BitSet) crt.left.zero.clone();
                crt.zero.or(crt.right.zero);
            } else { // XOR
                crt.one = (BitSet) crt.left.one.clone();
                crt.one.xor(crt.right.one);
                crt.zero = (BitSet) crt.left.zero.clone();
                crt.zero.xor(crt.right.zero);
            }
        }
    }

    private BitSet bitSet(String a) {
        int n = a.length();
        BitSet b = new BitSet(n);
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) == '1') b.set(i);
        }
        return b;
    }

    class Item {
        BitSet b;
        Item left;
        Item right;
        int type;
        BitSet zero;
        BitSet one;
        boolean vis;

        public Item(BitSet b, Item left, Item right, int type) {
            this.b = b;
            this.left = left;
            this.right = right;
            this.type = type;
        }
    }
    final int AND = 0;
    final int OR = 1;
    final int XOR = 2;
    final int VAR = 3;
    final int CONST = 5;
}
