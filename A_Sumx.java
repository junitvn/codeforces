
/**
 * A_Sumx
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_Sumx {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nLine[] = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        String line[] = br.readLine().split(" ");
        String sumLine[] = br.readLine().split(" ");
        int sum = Integer.parseInt(sumLine[0]);
        int[] arr = new int[n];
        int[] count = new int[10000001];
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(line[i]);
            count[arr[i]]++;
            if (i > 0 && sum - arr[i] >= 0) {
                if (count[sum - arr[i]] != 0) {
                    res += count[sum - arr[i]];
                }
                if (arr[i] == sum - arr[i])
                    res--;
            }
        }
        System.out.print(res);
    }
}