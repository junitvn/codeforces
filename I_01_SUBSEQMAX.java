import java.util.Scanner;

/**
 * I_01_SUBSEQMAX
 */
public class I_01_SUBSEQMAX {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] sum = new long[n + 1];
        long[] minSum = new long[n + 1];
        // minSum[0] = Long.MAX_VALUE;
        long max = 0, maxI;
        for (int i = 1; i < n + 1; i++) {
            sum[i] = sum[i - 1] + sc.nextInt();
            minSum[i] = minSum[i - 1] > sum[i] ? sum[i] : minSum[i - 1];
        }
        for (int i = 1; i < sum.length; i++) {
            maxI = sum[i] - minSum[i - 1];
            if (maxI > max)
                max = maxI;
        }
        System.out.print(max);
    }
}