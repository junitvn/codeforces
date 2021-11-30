
/**
 * E_H_P_S_NM
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E_H_P_S_NM {

    static long pow(long a, long b) {
        long res = 1;
        for (long i = 0; i < b; i++) {
            res *= a;
        }
        return res;
    }

    static long f(long a, long b) {
        return a / b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        long n = Long.parseLong(lineOne[0]);
        String[] lineTwo = br.readLine().split(" ");
        long m = Long.parseLong(lineTwo[0]);
        long[] sieve = new long[1000000];
        sieve[1] = 1;
        for (long i = 2; i < sieve.length; i++) {
            if (sieve[(int) i] == 0) {
                for (long j = i * 2; j < sieve.length; j += i) {
                    sieve[(int) j] = 1;
                }
            }
        }
        long res = 0;
        for (long p = pow(10, m - 1); p < pow(10, m); p++) {
            long s = p % 10;
            if (sieve[(int) p] == 0) {
                res += f((s + 1) * pow(10, n - m - 1) - 1, p) - f(s * pow(10, n - m - 1) - 1, p);
            }
        }
        System.out.println(res);
    }
}