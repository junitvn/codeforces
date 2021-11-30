
/**
 * I_Trung_v_
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class I_Trung_v_ {

    static int bs(int r, int c, int h, int w, int maxValue, int[][] arr) {
        int left = 0;
        int right = maxValue;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (check(r, c, mid, h, w, arr)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    private static boolean check(int r, int c, int mid, int h, int w, int[][] arr) {
        long[][] T = new long[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (arr[i][j] <= mid)
                    T[i][j] = 1;
                T[i][j] += T[i][j - 1] + T[i - 1][j] - T[i - 1][j - 1];
            }
        }
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (i + h - 1 <= r && j + w - 1 <= c) {
                    long S = T[i + h - 1][j + w - 1] - T[i - 1][j + w - 1] - T[i + h - 1][j - 1] + T[i - 1][j - 1];
                    if (S < (h * w + 1) / 2)
                        return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[] = br.readLine().split(" ");
        int r = Integer.parseInt(line[0]);
        int c = Integer.parseInt(line[1]);
        int h = Integer.parseInt(line[2]);
        int w = Integer.parseInt(line[3]);
        int[][] arr = new int[r + 1][c + 1];
        int maxValue = 0;
        for (int i = 1; i <= r; i++) {
            String cLine[] = br.readLine().split(" ");
            for (int j = 1; j <= c; j++) {
                arr[i][j] = Integer.parseInt(cLine[j - 1]);
                maxValue = Math.max(maxValue, arr[i][j]);
            }
        }
        System.out.println(bs(r, c, h, w, maxValue, arr));
    }
}