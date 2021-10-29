import java.util.Arrays;
import java.util.Scanner;

/**
 * B_Darts
 */
public class B_Darts {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int arrP[] = new int[n + 1];
        int A[] = new int[(n + 1) * (n + 1)];
        for (int i = 1; i <= n; i++) {
            arrP[i] = sc.nextInt();
        }

        int count = 0;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                A[count++] = arrP[i] + arrP[j];
            }
        }
        Arrays.sort(A);
        // for (int i = 0; i < A.length; i++) {
        // System.out.print(A[i] + " ");
        // }
        long res = 0;
        int j = A.length - 1;
        long max = 0;
        for (int i = 0; i < A.length; i++) {
            while (j >= 0 && A[i] + A[j] > m) {
                j--;
            }
            if (j < 0)
                break;
            max = A[i] + A[j];
            if (max > res)
                res = max;
        }
        System.out.print(res);
    }
}