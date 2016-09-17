package io.github.meijun.misc.io;

import java.io.*;

/**
 * Wrapper
 * Created by meijun on 6/6/2016.
 */
public class Printer extends PrintWriter {

    public Printer(Writer out) {
        super(out);
    }

    public Printer(Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public Printer(OutputStream out) {
        super(out);
    }

    public Printer(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public Printer(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public Printer(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public Printer(File file) throws FileNotFoundException {
        super(file);
    }

    public Printer(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }

    public void close() {
        super.close();
    }

    public void println(int[] a) {
        boolean f = true;
        for (int i : a) {
            if (f) f = false; else print(' ');
            print(i);
        }
        println();
    }

    public void println(long[] a) {
        boolean f = true;
        for (long i : a) {
            if (f) f = false; else print(' ');
            print(i);
        }
        println();
    }
}
