
/**
 * A_NH_BERTRAN
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_NH_BERTRAN {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        long arr[] = new long[2 * n];
        for (int i = 2; i < 2 * n; i++) {
            if (arr[i] == 0) {
                for (int j = i * i; j < arr.length; j += i) {
                    arr[j] = 1;
                }
            }
        }
        long res = 0;
        for (int i = n + 1; i < 2 * n; i++) {
            if (arr[i] == 0)
                res++;
        }
        System.out.println(res);
    }
}