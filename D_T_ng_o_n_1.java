import java.util.Scanner;

/**
 * D_T_ng_o_n_1
 */
public class D_T_ng_o_n_1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n + 1];
        int l, r, v;
        long[] sum = new long[n + 1];
        for (int i = 0; i < m; i++) {
            l = sc.nextInt();
            r = sc.nextInt();
            v = sc.nextInt();
            arr[l] += v;
            if (r < n)
                arr[r + 1] -= v;
        }
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
            System.out.print(sum[i] + " ");
        }
    }
}