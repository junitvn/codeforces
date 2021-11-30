
/**
 * J_2_Arrays
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class J_2_Arrays {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1[] = br.readLine().split(" ");
        int m = Integer.parseInt(line1[0]);
        int n = Integer.parseInt(line1[1]);
        int k = Integer.parseInt(line1[2]);
        int[] a = new int[m];
        int[] b = new int[n];
        for (int i = 0; i < m; i++) {
            String line[] = br.readLine().split(" ");
            a[i] = Integer.parseInt(line[0]);
        }
        for (int i = 0; i < n; i++) {
            String line[] = br.readLine().split(" ");
            b[i] = Integer.parseInt(line[0]);
        }
        Arrays.sort(a);
        Arrays.sort(b);
        System.out.println();
    }
}