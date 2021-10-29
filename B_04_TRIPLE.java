import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * B_04_TRIPLE
 */
public class B_04_TRIPLE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        int sum = Integer.parseInt(nLine[1]);
        String[] arrayString = br.readLine().split(" ");
        int[] arr = new int[n];
        int[] count = new int[1000000];
        int res = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrayString[i]);
            int sum_j_k = sum - arr[i];
            for (int j = i + 1; j < n; j++) {
                arr[j] = Integer.parseInt(arrayString[j]);
                if (sum_j_k - arr[j] >= 0) {
                    res += count[sum_j_k - arr[j]];
                    count[arr[j]]++;
                }
            }
            for (int j = i + 1; j < n; j++) {
                count[arr[j]] = 0;
            }
        }
        System.out.print(res);
    }
}