
/**
 * C_EXPMOD
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class C_EXPMOD {
    static int getBit(BigInteger mask, int pos) {
        return (mask.shiftRight(pos)).and(BigInteger.valueOf(1)).intValue();
    }

    public static void main(String[] args) throws IOException {
        long m = 1000000000 + 7;
        BigInteger bigM = BigInteger.valueOf(m);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1[] = br.readLine().split(" ");
        // long a = Long.parseUnsignedLong(line1[0]);
        // long b = Long.parseUnsignedLong(line1[1]);
        BigInteger a = new BigInteger(line1[0]);
        BigInteger b = new BigInteger(line1[1]);

        BigInteger[] x = new BigInteger[64];
        x[0] = a.mod(bigM);
        for (int i = 1; i < x.length; i++) {
            x[i] = (x[i - 1].multiply(x[i - 1])).mod(bigM);
        }
        BigInteger res = BigInteger.valueOf(1);
        for (int i = 0; i < 64; i++) {
            if (getBit(b, i) == 1) {
                res = res.multiply(x[i]);
                res = res.mod(bigM);
            }
        }
        res = res.mod(bigM);
        System.out.println(res);
    }
}