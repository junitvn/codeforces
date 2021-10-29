import java.util.Scanner;

/**
 * F_T_ng_o_n_2
 */
public class F_T_ng_o_n_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m;
        int l, r;
        long x, y;
        n = sc.nextInt();
        m = sc.nextInt();
        long[] arrA = new long[n + 1];
        long[] arrB = new long[n + 1];
        long[] sumA = new long[n + 1];
        long[] sumB = new long[n + 1];
        long a, b;

        for (int i = 0; i < m; i++) {
            l = sc.nextInt();
            r = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            a = x - l * y;
            b = y;
            arrA[l] += a;
            arrB[l] += b;
            if (r < n) {
                arrA[r + 1] -= a;
                arrB[r + 1] -= b;
            }
        }
        for (int i = 1; i < n + 1; i++) {
            sumA[i] = sumA[i - 1] + arrA[i];
            sumB[i] = sumB[i - 1] + arrB[i];
            System.out.print(sumA[i] + i * sumB[i] + " ");
        }
    }
}