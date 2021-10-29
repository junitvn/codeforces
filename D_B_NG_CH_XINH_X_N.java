
/**
 * D_B_NG_CH_XINH_X_N
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D_B_NG_CH_XINH_X_N {

    static long numOfCharacter(int i1, int i2, int j1, int j2, long[][][] T) {
        long res = 0;
        long sl = 0;
        for (int k = 0; k < 5; k++) {
            sl = T[k][i2][j2] - T[k][i1 - 1][j2] - T[k][i2][j1 - 1] + T[k][i1 - 1][j1 - 1];
            if (sl > 0)
                res++;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        final int N = 401;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nLine[] = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        char[][] original = new char[N][N];
        long[][][] T = new long[5][N][N];
        int m = 0;
        long result = 0;
        for (int i = 1; i <= n; i++) {
            String lineString[] = br.readLine().split("");
            m = lineString.length;
            for (int j = 1; j <= lineString.length; j++) {
                original[i][j] = lineString[j - 1].charAt(0);
                T[original[i][j] - 65][i][j] = 1;
                for (int k = 0; k < 5; k++) {
                    T[k][i][j] = T[k][i][j] + T[k][i][j - 1] + T[k][i - 1][j] - T[k][i - 1][j - 1];
                }
            }
        }

        for (int i1 = 1; i1 <= n; i1++) {
            for (int i2 = i1; i2 <= n; i2++) {
                int l = 0, r = 0;
                for (int j1 = 1; j1 <= m; j1++) {
                    while (l < m && numOfCharacter(i1, i2, j1, l + 1, T) < 3) {
                        l++;
                    }
                    while (r < m && numOfCharacter(i1, i2, j1, r + 1, T) <= 3) {
                        r++;
                    }
                    result += r - l;
                }
            }
        }
        System.out.println(result);
    }
}