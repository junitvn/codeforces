
/**
 * D_04_BOOKS1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D_04_BOOKS1 {

    static boolean check(int k, long s, int m, long[] T, boolean shouldPrint, int[] original) {
        int count = 0;
        int i = m, j = m;
        int p[] = new int[m + 1];
        while (i > 0) {
            while (j > 0 && T[i] - T[j - 1] <= s) {
                j--;
            }
            count++;
            j = Math.max(j, k - count);
            p[j] = 1;
            if (i == j)
                return false;
            i = j;
        }
        if (shouldPrint == true) {
            for (int l = 1; l < original.length; l++) {
                System.out.print(original[l] + " ");
                if (p[l] == 1)
                    System.out.print(" / ");
            }
        }
        return count <= k;
    }

    static long bs(int k, int m, long[] T) {
        long l = -1;
        long r = T[T.length - 1];
        while (r - l > 1) {
            long mid = (r + l) / 2;
            if (check(k, mid, m, T, false, new int[0])) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nLine[] = br.readLine().split(" ");
        int N = Integer.parseInt(nLine[0]);
        for (int tc = 1; tc <= N; tc++) {
            String mkLine[] = br.readLine().split(" ");
            int m = Integer.parseInt(mkLine[0]); // number of books
            int k = Integer.parseInt(mkLine[1]); // number of scribers
            String[] pagesLine = br.readLine().split(" ");
            int[] pagesInBook = new int[m + 1];
            long[] T = new long[m + 1];
            for (int i = 1; i <= m; i++) {
                pagesInBook[i] = Integer.parseInt(pagesLine[i - 1]);
                T[i] = T[i - 1] + pagesInBook[i];
            }
            long s = bs(k, m, T);
            check(k, s, m, T, true, pagesInBook);
            System.out.println();
        }
    }
}