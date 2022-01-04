
/**
 * D_c_chung_l_n_nh_t
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D_c_chung_l_n_nh_t {
    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    static long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmLine = br.readLine().split(" ");
        int n = Integer.parseInt(nmLine[0]);
        int m = Integer.parseInt(nmLine[1]);
        long[] A = new long[n + 1];
        long[][] T = new long[17][n + 1];
        int M[][] = new int[m][3];
        for (int i = 0; i < m; i++) {
            String[] mLine = br.readLine().split(" ");
            int l = Integer.parseInt(mLine[0]);
            int r = Integer.parseInt(mLine[1]);
            int z = Integer.parseInt(mLine[2]);
            M[i][0] = l;
            M[i][1] = r;
            M[i][2] = z;
            T[z][l] += 1;
            if (r < n)
                T[z][r + 1] -= 1;
            for (int j = 1; j < n + 1; j++) {
                T[z][j] += T[z][j - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            A[i] = 1;
            for (int z = 1; z < 17; z++) {
                if (T[z][i] > 0) {
                    A[i] = lcm(A[i], z);
                }
            }
        }

        long st[][] = new long[n][log2(n) + 1];
        for (int i = 0; i < n; i++) {
            st[i][0] = A[i + 1];
        }

        for (int j = 1; j <= log2(n); j++) {
            for (int i = 0; i + (1 << j - 1) < n; i++) {
                st[i][j] = gcd(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
        boolean flag = true;
        for (int i = 0; i < m; i++) {
            int l = M[i][0] - 1;
            int r = M[i][1] - 1;
            int z = M[i][2];
            int k = log2(r - l + 1);
            if (z != gcd(st[l][k], st[r - (1 << k) + 1][k])) {
                flag = false;
            }
        }
        if (flag) {
            for (int i = 1; i <= n; i++) {
                System.out.print(A[i] + " ");
            }
        } else {
            System.out.print("Impossible");
        }
    }
}