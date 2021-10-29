
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

/**
 * C_Diamonds
 */
public class C_Diamonds {
    final static int MAX_N = 101;

    public static void main(String[] args) throws Exception {
        InputStreamReader r = new InputStreamReader(System.in);
        DataInputStream dis = new DataInputStream(System.in);
        int l = dis.readInt();
        int m = dis.readInt();
        System.out.println(l + ' ' + m);
        // long[][][] sum = new long[MAX_N][MAX_N][MAX_N];
        // int count = 3;
        // for (int i = 1; i <= n; i++) {
        // for (int j = 1; j <= m; j++) {
        // for (int k = 1; k <= l; k++) {
        // sum[i][j][k] = Integer.parseInt(line[count++]) - 48;
        // sum[i][j][k] += sum[i][j - 1][k] + sum[i - 1][j][k] - sum[i - 1][j - 1][k] +
        // sum[i][j][k - 1]
        // - sum[i][j - 1][k - 1] - sum[i - 1][j][k - 1] + sum[i - 1][j - 1][k - 1];
        // }
        // }
        // }
        // while (br.read() != -1) {

        // String points[] = br.readLine().trim().split(" ");
        // for (int i = 0; i < points.length; i++) {
        // System.out.print(points[i]);
        // }
        // int x1 = Integer.parseInt(points[0]);
        // int y1 = Integer.parseInt(points[1]);
        // int z1 = Integer.parseInt(points[2]);
        // int x2 = Integer.parseInt(points[3]);
        // int y2 = Integer.parseInt(points[4]);
        // int z2 = Integer.parseInt(points[5]);
        // long t = sum[z2][y2][x2] - sum[z2][y1][x2] - sum[z1][y2][x2] +
        // sum[z1][y1][x2] - sum[z2][y2][x1]
        // + sum[z2][y1][x1] + sum[z1][y2][x1] - sum[z1][y1][x1];
        // System.out.println(t);
        // }
    }
}