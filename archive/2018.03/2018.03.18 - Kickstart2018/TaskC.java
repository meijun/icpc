package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Stream;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            out.printf("Case #%d: ", i + 1);
            solve(in, out);
        }
    }

    public void solvePa(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        Prob[] ps = new Prob[t];
        for (int i = 0; i < t; i++) {
            ps[i] = read(in, out);
        }
        Arrays.stream(ps).parallel().forEach(p -> {
            p.res = getRes(p.ds, p.s1, p.s2, p.n, p.a, p.b, p.c, p.d);
        });
        for (int i = 0; i < t; i++) {
            out.printf("Case #%d: %d%n", i + 1, ps[i].res);
        }
    }

    private Prob read(Scanner in, PrintWriter out) {
        int l = in.nextInt();
        String[] ds = new String[l];
        for (int i = 0; i < l; i++) {
            ds[i] = in.next();
        }
        String s1 = in.next();
        String s2 = in.next();
        int n = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();
        long d = in.nextInt();
        return new Prob(ds, s1, s2, n, a, b, c, d);
    }


    class Prob {
        String[] ds;
        String s1;
        String s2;
        int n;
        long a;
        long b;
        long c;
        long d;
        int res;

        public Prob(String[] ds, String s1, String s2, int n, long a, long b, long c, long d) {
            this.ds = ds;
            this.s1 = s1;
            this.s2 = s2;
            this.n = n;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int l = in.nextInt();
        String[] ds = new String[l];
        for (int i = 0; i < l; i++) {
            ds[i] = in.next();
        }
        String s1 = in.next();
        String s2 = in.next();
        int n = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();
        long d = in.nextInt();
        int res = getRes(ds, s1, s2, n, a, b, c, d);
        out.println(res);
    }

    private int getRes(String[] ds, String s1, String s2, int n, long a, long b, long c, long d) {
        long[] xs = new long[n];
        xs[0] = s1.charAt(0);
        xs[1] = s2.charAt(0);
        int[] cs = new int[n];
        cs[0] = (int) xs[0] - 'a';
        cs[1] = (int) xs[1] - 'a';
        for (int i = 2; i < n; i++) {
            long x = (a * xs[i - 1] + b * xs[i - 2] + c) % d;
            xs[i] = x;
            cs[i] = (int) x % 26;
        }
        Map[][][] arrays = new HashMap[100000 + 1][][];
        Map[][][] cnt = new HashMap[100000 + 1][][];
        for (String s : ds) {
            int m = s.length();
            char f = s.charAt(0);
            char e = s.charAt(m - 1);
            if (arrays[m] == null) {
                arrays[m] = new HashMap[26][26];
                cnt[m] = new HashMap[26][26];
            }
            int[] ar = new int[26];
            for (char c1 : s.substring(1, m - 1).toCharArray()) {
                ar[c1 - 'a'] += 1;
            }
            if (arrays[m][f - 'a'][e - 'a'] == null) {
                arrays[m][f - 'a'][e - 'a'] = new HashMap();
                cnt[m][f - 'a'][e - 'a'] = new HashMap();
            }
            arrays[m][f - 'a'][e - 'a'].put(hash(ar), false);
            cnt[m][f - 'a'][e - 'a'].merge(hash(ar), 1, (x, y) -> ((Integer) x + (Integer) y));
        }
        for (int len = 0; len <= 100000; len++) {
            if (arrays[len] != null) {
                Map[][] arrs = arrays[len];
                int[] arr = new int[26];
                for (int i = 1; i < len - 1; i++) {
                    arr[cs[i]]++;
                }
                for (int i = 0; i < n; i++) {
                    int j = i + len - 1;
                    Map m = arrs[cs[i]][cs[j]];
                    if (m != null) {
                        long h = hash(arr);
                        if (m.containsKey(h)) {
                            m.put(h, true);
                        }
                    }
                    if (j >= n - 1) break;
                    arr[cs[j]]++;
                    arr[cs[i + 1]]--;
                }
            }
        }
        int res = 0;
        for (int len = 0; len <= 100000; len++) {
            if (arrays[len] != null) {
                Map[][] arrs = arrays[len];
                for (int i = 0; i < 26; i++) {
                    for (int j = 0; j < 26; j++) {
                        if (arrs[i][j] != null) {
                            for (Object en : arrs[i][j].entrySet()) {
                                Map.Entry ent = (Map.Entry) en;
                                if ((Boolean) ent.getValue()) {
                                    res += (Integer) cnt[len][i][j].get(ent.getKey());
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    long hash(int[] arr) {
        long result = 1;
        for (int element : arr)
            result = 31 * result + element;
        return result;
    }

    private void solve2(Scanner in, PrintWriter out) {
        int l = in.nextInt();
        String[] ds = new String[l];
        for (int i = 0; i < l; i++) {
            ds[i] = in.next();
        }
        String s1 = in.next();
        String s2 = in.next();
        int n = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();
        long d = in.nextInt();
        char[] cs = new char[n];
        cs[0] = s1.charAt(0);
        cs[1] = s2.charAt(0);
        long[] xs = new long[n];
        xs[0] = cs[0];
        xs[1] = cs[1];
        for (int i = 2; i < n; i++) {
            long x = (a * xs[i - 1] + b * xs[i - 2] + c) % d;
            cs[i] = (char) ('a' + x % 26);
            xs[i] = x;
        }
        int res = 0;
        for (String s : ds) {
            int m = s.length();
            char f = s.charAt(0);
            char e = s.charAt(m - 1);
            String ss = s.substring(1, m - 1);
            ss = String.valueOf(sorted(ss.toCharArray()));
            for (int i = 0; i < n; i++) {
                int j = i + m - 1;
                if (j < n && cs[i] == f && cs[j] == e) {
                    String inn = String.valueOf(sorted(Arrays.copyOfRange(cs, i + 1, j)));
                    if (ss.equals(inn)) {
                        res += 1;
                        break;
                    }
                }
            }
        }
        out.println(res);
    }

    private char[] sorted(char[] cs) {
        Arrays.sort(cs);
        return cs;
    }
}
