package main;

import io.github.meijun.misc.io.Scanner;
import io.github.meijun.misc.io.Printer;
import java.util.*;

public class TaskHello3 {
    Scanner in;
    Printer out;

    public void solve(int testNumber, Scanner in, Printer out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        out.println(in.nextInt() + 1);
    }
}
