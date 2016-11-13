package lib.math;

/**
 * FFT
 * Created by meijun on 6/4/2016.
 */
public class FFT {

    /**
     * {@code [1, 1, 1, 1, 1] * [1, 1, 1, 1, 1] = [1, 2, 3, 4, 5, 4, 3, 2, 1]}
     * <p> {@code re.length = im.length = 2^k} </p>
     * <p> use {@code (int) (re[i] + 0.5)} to convert to int.</p>
     * <pre>{@code
     * fft(1, re, im);
     * for (int i = 0; i < n; i++) {
     *     double re = re[i] * re[i] - im[i] * im[i];
     *     double im = re[i] * im[i] * 2;
     *     re[i] = re;
     *     im[i] = im;
     * }
     * fft(-1, re, im);
     * }</pre>
     * {@code re = [1, 2, 3, 4, 5, 4, 3, 2, 1, 0]}
     *
     * @param sign {@code 1} for encode, {@code -1} for decode
     * @param re   real part
     * @param im   image part
     */
    public static void fft(int sign, double[] re, double[] im) {
        int n = re.length, d = Integer.numberOfLeadingZeros(n) + 1;
        double theta = sign * 2 * Math.PI / n;
        for (int m = n; m >= 2; m >>= 1, theta *= 2) {
            for (int i = 0, mh = m >> 1; i < mh; i++) {
                double wr = Math.cos(i * theta), wi = Math.sin(i * theta);
                for (int j = i; j < n; j += m) {
                    int k = j + mh;
                    double xr = re[j] - re[k], xi = im[j] - im[k];
                    re[j] += re[k];
                    im[j] += im[k];
                    re[k] = wr * xr - wi * xi;
                    im[k] = wr * xi + wi * xr;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int j = Integer.reverse(i) >>> d;
            if (j < i) {
                double tr = re[i];
                re[i] = re[j];
                re[j] = tr;
                double ti = im[i];
                im[i] = im[j];
                im[j] = ti;
            }
        }
        if (sign == -1) for (int i = 0; i < n; i++) {
            re[i] /= n;
            im[i] /= n;
        }
    }

    public static double[] mul(double[] a, double[] b) {
        int len = Integer.highestOneBit(a.length + b.length) << 1;
        double[] ra = new double[len];
        double[] ia = new double[len];
        System.arraycopy(a, 0, ra, 0, a.length);
        fft(1, ra, ia);
        double[] rb;
        double[] ib;
        if (a == b) {
            rb = ra;
            ib = ia;
        } else {
            rb = new double[len];
            ib = new double[len];
            System.arraycopy(b, 0, rb, 0, b.length);
            fft(1, rb, ib);
        }
        for (int i = 0; i < len; i++) {
            double rr = ra[i] * rb[i] - ia[i] * ib[i];
            double ir = ra[i] * ib[i] + rb[i] * ia[i];
            ra[i] = rr;
            ia[i] = ir;
        }
        fft(-1, ra, ia);
        return ra;
    }
}
