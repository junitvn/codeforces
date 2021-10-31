
/**
 * E_04_AGGRCOW
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class E_04_AGGRCOW {
    static long res = Integer.MAX_VALUE;

    static long bs(int n, int c, int[] T) {
        long l = -1;
        long r = T[T.length - 1];
        while (r - l > 1) {
            long mid = (r + l) / 2;
            if (check(c, n, mid, T)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return l;
    }

    static boolean check(int c, int n, long mid, int[] T) {
        int count = 1;
        int i = 1;
        for (int pos = 1; pos <= n; pos++) {
            if (T[pos] - T[i] >= mid) {
                count++;
                i = pos;
            }
        }
        return count < c;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nLine[] = br.readLine().split(" ");
        int N = Integer.parseInt(nLine[0]);
        for (int tc = 1; tc <= N; tc++) {
            String ncLine[] = br.readLine().split(" ");
            int n = Integer.parseInt(ncLine[0]); // number of stalls
            int c = Integer.parseInt(ncLine[1]); // number of cows
            int[] stallPosition = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                String line[] = br.readLine().split(" ");
                stallPosition[i] = Integer.parseInt(line[0]);
            }
            Arrays.sort(stallPosition);
            // for (int i = 1; i < T.length; i++) {
            // T[i] = T[i - 1] + stallPosition[i];
            // }
            res = bs(n, c, stallPosition);
            System.out.println(res);
        }
    }
}