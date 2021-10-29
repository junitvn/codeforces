import java.util.Scanner;

/**
 * E_T_ng_hcn
 */
public class E_T_ng_hcn {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m, x1, x2, y1, y2, v;
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] arr = new int[n + 1][n + 1];
        long[][] sum = new long[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            v = sc.nextInt();
            arr[x1][y1] += v;
            if (y2 < n)
                arr[x1][y2 + 1] -= v;
            if (x2 < n)
                arr[x2 + 1][y1] -= v;
            if (x2 < n && y2 < n)
                arr[x2 + 1][y2 + 1] += v;
        }
        for (int i = 1; i < sum.length; i++) {
            for (int j = 1; j < sum.length; j++) {
                sum[i][j] = arr[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
    }
}