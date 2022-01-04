
/**
 * A_RMQ
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_RMQ {
    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);

        String[] aLine = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(aLine[i]);
        }
        long st[][] = new long[n][log2(n) + 1];
        for (int i = 0; i < n; i++) {
            st[i][0] = arr[i];
        }
        for (int j = 1; j <= log2(n); j++) {
            for (int i = 0; i + (1 << j - 1) < n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
        String[] mLine = br.readLine().split(" ");
        int m = Integer.parseInt(mLine[0]);
        long res = 0;
        for (int x = 0; x < m; x++) {
            String[] abLine = br.readLine().split(" ");
            int a = Integer.parseInt(abLine[0]);
            int b = Integer.parseInt(abLine[1]);
            int k = log2(b - a + 1);
            res += Math.min(st[a][k], st[b - (1 << k) + 1][k]);
        }
        System.out.println(res);
    }
}