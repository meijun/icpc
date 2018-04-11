package main;

import lib.misc.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Task229 {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        while (in.hasNext()) {
            String s = in.next();
            BigInteger b = new BigInteger(s);
            out.println(b.toString(2));
        }
    }
}
