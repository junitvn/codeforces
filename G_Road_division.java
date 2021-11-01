
/**
 * G_Road_division
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G_Road_division {

    static boolean check(double mid, int n, double L, double[] arr) {
        int count = 1;
        int i = 0;
        for (int pos = 1; pos < n; pos++) {
            if (arr[pos] - arr[i] >= mid) {
                count++;
                i = pos;
            }
        }
        return count < n;
    }

    static double bs(int n, double L, double[] arr) {
        double l = 0;
        double r = arr[arr.length - 1] + 1;

        while (r - l > 0.0000001) {
            double mid = (r + l) / 2;
            if (check(mid, n, L, arr)) {
                l = mid;
            } else {
                r = mid;
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
        double[] arr = new double[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Double.parseDouble(nLine[i]);
        }
        System.out.println(bs(n, L, arr));
    }
}