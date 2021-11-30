
/**
 * G_Road_division
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G_Road_division {

    static boolean check(double mid, int n, double L, double[] arr) {
        double[] f = new double[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = Math.min(f[i - 1] + mid, arr[i + 1]);
            if (f[i] < arr[i])
                return false;
        }
        return f[n] == L;
    }

    static double bs(int n, double L, double[] arr) {
        double l = 0;
        double r = L;

        while (r - l > 1e-6) {
            double mid = (r + l) / 2;
            if (check(mid, n, L, arr)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return r;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nlLine[] = br.readLine().split(" ");
        int n = Integer.parseInt(nlLine[0]);
        int L = Integer.parseInt(nlLine[1]);
        String nLine[] = br.readLine().split(" ");
        double[] arr = new double[n + 2];
        arr[n + 1] = L;
        for (int i = 1; i <= n; i++) {
            arr[i] = Double.parseDouble(nLine[i - 1]);
        }
        Arrays.sort(arr);

        System.out.println(bs(n, L, arr));
    }
}