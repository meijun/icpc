package main;

import io.github.meijun.misc.io.Scanner;
import io.github.meijun.misc.io.Printer;
import java.util.*;

public class TaskHello {
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
