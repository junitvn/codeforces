import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * H_Trung_b_nh_c_ng
 */
public class H_Trung_b_nh_c_ng {

    static boolean check(int n, int k, double mid, double[] arr) {
        double[] b = new double[n + 1];
        double[] T = new double[n + 1];
        double[] TMin = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            b[i] = arr[i] - mid;
            T[i] = T[i - 1] + b[i];
            TMin[i] = Math.min(TMin[i - 1], T[i]);
        }

        double maxSum = -1000;
        for (int i = k; i <= n; i++) {
            double max = T[i] - TMin[i - k];
            maxSum = Math.max(max, maxSum);
        }

        return maxSum >= 0;
    }

    static double bs(int n, int k, double minValue, double maxValue, double[] arr) {
        double l = minValue;
        double r = maxValue;
        while (r - l > 1e-4) {
            double mid = (r + l) / 2;
            if (check(n, k, mid, arr)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nkLine[] = br.readLine().split(" ");
        int n = Integer.parseInt(nkLine[0]);
        int k = Integer.parseInt(nkLine[1]);
        String nLine[] = br.readLine().split(" ");
        double[] arr = new double[n + 1];
        double maxValue = Double.MIN_NORMAL;
        double minValue = Double.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            arr[i] = Double.parseDouble(nLine[i - 1]);
            maxValue = arr[i] > maxValue ? arr[i] : maxValue;
            minValue = arr[i] < minValue ? arr[i] : minValue;
        }
        System.out.println(bs(n, k, minValue, maxValue, arr));
    }
}