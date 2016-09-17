package io.github.meijun.misc;

import io.github.meijun.misc.function.Procedure;

import java.util.Arrays;

/**
 * Short cut.
 * Created by meijun on 6/6/2014.
 */
public class __ {
    public static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }
    public static double timeIt(Procedure procedure) {
        long start = System.nanoTime();
        procedure.process();
        return (System.nanoTime() - start) * 1e-9;
    }
}
