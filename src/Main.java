import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.StringTokenizer;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author meijun
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        Printer out = new Printer(outputStream);
        TaskHello solver = new TaskHello();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskHello {
        Scanner in;
        Printer out;

        public void solve(int testNumber, Scanner in, Printer out) {
            this.in = in;
            this.out = out;
            run();
        }

        void run() {
            out.println(in.nextInt() + 42);
        }

    }

    static class Printer extends PrintWriter {
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

    }

    static class Scanner {
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

    }
}

