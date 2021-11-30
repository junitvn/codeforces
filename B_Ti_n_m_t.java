
/**
 * B_Ti_n_m_t
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class B_Ti_n_m_t {

    static int getBit(long mask, int pos) {
        return (int) (mask >> pos) & 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1[] = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        long k = Long.parseLong(line1[1]);
        String line2[] = br.readLine().split(" ");
        long arr[] = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(line2[i]);
        }
        int n1 = n / 2;
        int n2 = n - n1;
        int res = -1;
        HashMap<Long, Integer> mapSum = new HashMap<Long, Integer>();
        for (long mask = 0; mask < (1l << n2); mask++) {
            int count = 0;
            long sum = 0;
            for (int i = n1; i < n; i++) {
                sum += getBit(mask, i - n1) * arr[i];
                count += getBit(mask, i - n1);
            }
            mapSum.put(sum, Math.max(count, mapSum.getOrDefault(sum, 0)));
        }

        for (long mask = 0; mask < (1l << n1); mask++) {
            long sum = 0;
            int count = 0;
            for (int i = 0; i < n1; i++) {
                sum += getBit(mask, i) * arr[i];
                count += getBit(mask, i);
            }
            if (mapSum.get(k - sum) != null) {
                res = Math.max(count + mapSum.get(k - sum), res);
            }
        }
        System.out.println(res);
    }
}