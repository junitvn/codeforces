
/**
 * B_RMQ2
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_RMQ2 {

    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // String[] mnLine = br.readLine().split(" ");
        // int m = Integer.parseInt(mnLine[0]);
        // int n = Integer.parseInt(mnLine[1]);

        // long[][] arr = new long[m][n];
        // for (int i = 0; i < m; i++) {
        // String[] rowLine = br.readLine().split(" ");
        // for (int j = 0; j < n; j++) {
        // arr[i][j] = Long.parseLong(rowLine[j]);
        // }
        // }
        // String[] qLine = br.readLine().split(" ");
        // int q = Integer.parseInt(qLine[0]);
        // long st[][][][] = new long[m][log2(m) + 1][n][log2(n) + 1]; // i, k, j, h

        // for (int i = 0; i < m; i++) {
        // for (int j = 0; j < n; j++) {
        // st[i][0][j][0] = arr[i][j];
        // }
        // }

        // for (int i = 0; i < m; i++) {
        // for (int h = 1; h <= log2(n); h++) {
        // for (int j = 0; j + (1 << h) - 1 < n; j++) {
        // st[i][0][j][h] = Math.min(st[i][0][j][h - 1], st[i][0][j + (1 << (h - 1))][h
        // - 1]);
        // }
        // }
        // }

        // for (int k = 1; k <= log2(m); k++) {
        // for (int i = 0; i + (1 << k) - 1 < m; i++) {
        // for (int h = 0; h <= log2(n); h++) {
        // for (int j = 0; j + (1 << h) - 1 < n; j++) {
        // st[i][k][j][h] = Math.min(st[i][k - 1][j][h], st[i + (1 << (k - 1))][k -
        // 1][j][h]);
        // }
        // }
        // }
        // }

        // for (int tc = 0; tc < q; tc++) {
        // String[] coorLine = br.readLine().split(" ");
        // int x1 = Integer.parseInt(coorLine[0]) - 1;
        // int y1 = Integer.parseInt(coorLine[1]) - 1;
        // int x2 = Integer.parseInt(coorLine[2]) - 1;
        // int y2 = Integer.parseInt(coorLine[3]) - 1;

        // int kColumn = log2((int) (x2 - x1 + 1));
        // int kRow = log2((int) (y2 - y1 + 1));
        // long min1 = Math.min(st[x1][kColumn][y1][kRow], st[x1][kColumn][y2 - (1 <<
        // kRow) + 1][kRow]);
        // long min2 = Math.min(st[x2 - (1 << kColumn) + 1][kColumn][y1][kRow],
        // st[x2 - (1 << kColumn) + 1][kColumn][y2 - (1 << kRow) + 1][kRow]);
        // System.out.println(Math.min(min1, min2));
        // }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int m = Integer.parseInt(lineOne[0]);
        int n = Integer.parseInt(lineOne[1]);

        int[][] a = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            String[] lineJ = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[i][j] = Integer.parseInt(lineJ[j - 1]);
            }
        }

        String[] lineQ = br.readLine().split(" ");
        int q = Integer.parseInt(lineQ[0]);

        int[][] Q = new int[q][4];

        for (int i = 0; i < q; i++) {
            String[] lineQLine = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                Q[i][j] = Integer.parseInt(lineQLine[j]);
            }
        }

        int logM = (int) (Math.log(m) / Math.log(2));
        int logN = (int) (Math.log(n) / Math.log(2));

        int[][][][] st = new int[m + 1][logM + 1][n + 1][logN + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                st[i][0][j][0] = a[i][j];
            }

            for (int h = 1; h <= logN; h++) {
                for (int j = 1; j + (1 << h) - 1 <= n; j++) {
                    st[i][0][j][h] = Math.min(st[i][0][j][h - 1], st[i][0][j + (1 << (h - 1))][h - 1]);
                }
            }
        }

        for (int k = 1; k <= logM; k++) {
            for (int i = 1; i + (1 << k) - 1 <= m; i++) {
                for (int h = 0; h <= logN; h++) {
                    for (int j = 1; j + (1 << h) - 1 <= n; j++) {
                        st[i][k][j][h] = Math.min(st[i][k - 1][j][h], st[i + 1 << (k - 1)][k - 1][j][h]);
                    }
                }
            }
        }

        for (int i = 0; i < q; i++) {
            int x1 = Q[i][0];
            int y1 = Q[i][1];
            int x2 = Q[i][2];
            int y2 = Q[i][3];

            int k = (int) (Math.log(x2 - x1 + 1) / Math.log(2));
            int h = (int) (Math.log(y2 - y1 + 1) / Math.log(2));

            int quare1 = st[x1][k][y1][h];
            int quare2 = st[x1][k][y2 - (1 << h) + 1][h];
            int quare3 = st[x2 - (1 << k) + 1][k][y1][h];
            int quare4 = st[x2 - (1 << k) + 1][k][y2 - (1 << h) + 1][h];

            int min1 = Math.min(quare1, quare2);
            int mint2 = Math.min(quare3, quare4);
            int kq = Math.min(min1, mint2);
            System.out.println(kq);
        }

    }
}