package io.github.meijun.misc.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * more more speedy Scanner.
 * Created by meijun on 8/28/2014.
 */
public class ScannerS {
    InputStream in;
    byte[] buf = new byte[1 << 10];
    int p, n;
    boolean[] isSpace = new boolean[128];

    public ScannerS(InputStream in) {
        this.in = in;
        isSpace[' '] = isSpace['\n'] = isSpace['\r'] = isSpace['\t'] = true;
    }

    int read() {
        if (n == -1)
            return -1;
        if (p >= n) {
            p = 0;
            try {
                n = in.read(buf);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (n <= 0)
                return -1;
        }
        return buf[p++];
    }

    public boolean hasNext() {
        int c = read();
        while (c >= 0 && isSpace[c])
            c = read();
        if (c == -1)
            return false;
        p--;
        return true;
    }

    public String next() {
        if (!hasNext())
            throw new InputMismatchException();
        StringBuilder sb = new StringBuilder();
        int c = read();
        while (c >= 0 && !isSpace[c]) {
            sb.append((char) c);
            c = read();
        }
        return sb.toString();
    }

    public int nextInt() {
        if (!hasNext())
            throw new InputMismatchException();
        int c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (c >= 0 && !isSpace[c]);
        return res * sgn;
    }

    public long nextLong() {
        if (!hasNext())
            throw new InputMismatchException();
        int c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (c >= 0 && !isSpace[c]);
        return res * sgn;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}