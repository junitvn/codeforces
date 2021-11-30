
/**
 * C_S_g_n_ho_n_h_o
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C_S_g_n_ho_n_h_o {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int l = Integer.parseInt(lineOne[0]);
        String[] lineTwo = br.readLine().split(" ");
        int d = Integer.parseInt(lineTwo[0]);
        long[] arr = new long[l];
        long res = 0;

        for (int i = 1; i < l; i++) {
            for (int j = i * 2; j < l; j += i) {
                arr[j] += i;
            }
        }

        for (int i = 1; i < l; i++) {
            if (Math.abs(arr[i] - i) <= d) {
                res++;
            }
        }

        System.out.println(res);
    }
}