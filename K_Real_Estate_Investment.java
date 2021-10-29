import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * K_Real_Estate_Investment
 */
public class K_Real_Estate_Investment {

    public static class Res {
        public long maxProfit;
        public long minLength;

        Res() {
            maxProfit = 0;
            minLength = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nLine[] = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        long b[][];
        long T[][];
        long sumRow[];
        long TRow[];
        long minTRow[];
        long pos[];

        for (int tc = 1; tc <= n; tc++) {
            long bestProfit = 0;
            long bestCost = 0;
            String size[] = br.readLine().split(" ");
            int r = Integer.parseInt(size[0]);
            int c = Integer.parseInt(size[1]);
            int l = Integer.parseInt(size[2]);
            // Mảng profit - l sau 5 năm
            b = new long[r + 1][c + 1];
            // Mảng cộng dồn của b để tính tổng của cột j từ r1 -> r2
            T = new long[r + 1][c + 1];

            for (int i = 1; i <= r; i++) {
                String row[] = br.readLine().split(" ");
                for (int j = 1; j <= c; j++) {
                    b[i][j] = Integer.parseInt(row[j - 1]) - l;
                    T[i][j] = T[i - 1][j] + T[i][j - 1] - T[i - 1][j - 1] + b[i][j];
                }
            }
            for (int r1 = 1; r1 <= r; r1++) {
                for (int r2 = r1; r2 <= r; r2++) {
                    TRow = new long[c + 1];
                    minTRow = new long[c + 1];
                    pos = new long[c + 1];
                    sumRow = new long[c + 1];
                    for (int j = 1; j <= c; j++) {
                        // Tính tổng từ r1 -> r2 của cột j
                        sumRow[j] = T[r2][j] - T[r1 - 1][j] - T[r2][j - 1] + T[r1 - 1][j - 1];
                        // Mảng cộng dồn của sumRow
                        TRow[j] = TRow[j - 1] + sumRow[j];
                        // Cập nhật minT và vị trí của minT
                        if (minTRow[j - 1] < TRow[j]) {
                            minTRow[j] = minTRow[j - 1];
                            pos[j] = pos[j - 1];
                        } else {
                            minTRow[j] = TRow[j];
                            pos[j] = j;
                        }
                    }
                    // Tìm maxProfit và minCost
                    Res res = calculateRes(TRow, minTRow, c, pos);
                    long maxProfit = res.maxProfit;
                    long minLength = res.minLength;
                    long cost = minLength * (r2 - r1 + 1) * l;
                    if (maxProfit > bestProfit) {
                        bestProfit = maxProfit;
                        bestCost = cost;
                    } else if (maxProfit == bestProfit) {
                        bestCost = Math.min(bestCost, cost);
                    }
                }
            }
            System.out.println("#" + tc + " " + bestProfit + " " + bestCost);
        }
    }

    private static Res calculateRes(long[] tRow, long[] minTRow, int c, long pos[]) {
        long maxProfit = Long.MIN_VALUE;
        long minLength = 999;
        for (int i = 1; i <= c; i++) {
            long max = tRow[i] - minTRow[i - 1];
            if (max > maxProfit) {
                maxProfit = max;
                minLength = i - pos[i - 1];
            }
        }
        Res res = new Res();
        res.maxProfit = maxProfit;
        res.minLength = minLength;
        return res;
    }

}