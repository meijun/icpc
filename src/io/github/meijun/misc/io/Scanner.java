package io.github.meijun.misc.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * More speedy Scanner.
 * Created by meijun on 6/6/2014.
 */
public class Scanner {
    BufferedReader br;
    StringTokenizer st;

    public Scanner(InputStream in) {
        br = new BufferedReader(new InputStreamReader(in));
        eat("");
    }

    private void eat(String s) {
        st = new StringTokenizer(s);
    }

    public String nextLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!st.hasMoreTokens()) {
            String s = nextLine();
            if (s == null)
                return false;
            eat(s);
        }
        return true;
    }

    public String next() {
        hasNext();
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public BigInteger nextBigInteger() {
        return new BigInteger(next());
    }

    public int[] nextIntArray(int n) {
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = nextInt();
        }
        return is;
    }

    public long[] nextLongArray(int n) {
        long[] ls = new long[n];
        for (int i = 0; i < n; i++) {
            ls[i] = nextLong();
        }
        return ls;
    }

    public double[] nextDoubleArray(int n) {
        double[] ds = new double[n];
        for (int i = 0; i < n; i++) {
            ds[i] = nextDouble();
        }
        return ds;
    }

    public char[] nextCharArray() {
        return next().toCharArray();
    }

    public int[][] nextIntMatrix(int row, int col) {
        int[][] mat = new int[row][];
        for (int i = 0; i < row; i++) {
            mat[i] = nextIntArray(col);
        }
        return mat;
    }

    public long[][] nextLongMatrix(int row, int col) {
        long[][] mat = new long[row][];
        for (int i = 0; i < row; i++) {
            mat[i] = nextLongArray(col);
        }
        return mat;
    }

    public double[][] nextDoubleMatrix(int row, int col) {
        double[][] mat = new double[row][];
        for (int i = 0; i < row; i++) {
            mat[i] = nextDoubleArray(col);
        }
        return mat;
    }

    public char[][] nextCharMatrix(int row) {
        char[][] mat = new char[row][];
        for (int i = 0; i < row; i++) {
            mat[i] = nextCharArray();
        }
        return mat;
    }

    public void nextIntArray(int n, int[] as, int[] bs) {
        for (int i = 0; i < n; i++) {
            as[i] = nextInt();
            bs[i] = nextInt();
        }
    }

    public void nextIntArray(int n, int[] as, int[] bs, int[] cs) {
        for (int i = 0; i < n; i++) {
            as[i] = nextInt();
            bs[i] = nextInt();
            cs[i] = nextInt();
        }
    }

    public void nextIntArray(int n, int[] as, int[] bs, int[] cs, int[] ds) {
        for (int i = 0; i < n; i++) {
            as[i] = nextInt();
            bs[i] = nextInt();
            cs[i] = nextInt();
            ds[i] = nextInt();
        }
    }
}