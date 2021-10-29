
/**
 * C_04_EKO
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class C_04_EKO {

    static int A(int m, int[] L) {
        int l = L[0];
        int r = L[L.length - 1] + 1;
        while (r - l > 1) {
            int mid = (r + l) / 2;
            long sum = sum(mid, L);
            if (sum < m) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return l;
    }

    static long sum(int x, int[] L) {
        long sum = 0;
        for (int i = 0; i < L.length; i++) {
            if (L[i] - x > 0)
                sum += L[i] - x;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nmLine[] = br.readLine().split(" ");
        int n = Integer.parseInt(nmLine[0]);
        int m = Integer.parseInt(nmLine[1]);
        String[] nLine = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(nLine[i]);
        }
        Arrays.sort(arr);
        System.out.println(A(m, arr));
    }
}