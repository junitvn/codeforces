
/**
 * F_LCA
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class F_LCA {
    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    static int getBit(long mask, int pos) {
        return (int) (mask >> pos) & 1;
    }

    static void calculate_deep(int[] d, ArrayList<Integer>[] c, int r) {
        for (int i : c[r]) {
            d[i] = d[r] + 1;
            calculate_deep(d, c, i);
        }
    }

    static int getLCA(int a[][], int u, int v, int p[], int[] d, int n) {

        if (d[u] > d[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int delta = d[v] - d[u];

        for (int i = log2(n); i >= 0; i--) {
            if (getBit(delta, i) == 1) {
                v = a[v][i];
            }
        }
        if (u == v)
            return v;
        for (int i = log2(n); i >= 0; i--) {
            if (a[u][i] != a[v][i]) {
                u = a[u][i];
                v = a[v][i];
            }
        }
        return p[u];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        String[] treeLine = br.readLine().split(" ");
        int p[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(treeLine[i - 1]);
        }
        String[] qLine = br.readLine().split(" ");
        int q = Integer.parseInt(qLine[0]);
        int[][] query = new int[q][2];
        for (int i = 0; i < q; i++) {
            String[] queryLine = br.readLine().split(" ");
            query[i][0] = Integer.parseInt(queryLine[0]);
            query[i][1] = Integer.parseInt(queryLine[1]);
        }

        int a[][] = new int[n + 1][log2(n) + 1];

        ArrayList<Integer>[] c = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            c[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            c[p[i]].add(i);
            a[i][0] = p[i];
        }
        int d[] = new int[n + 1];
        calculate_deep(d, c, 0);

        for (int i = 1; i <= log2(n); i++) {
            for (int u = 1; u <= n; u++) {
                a[u][i] = a[a[u][i - 1]][i - 1];
            }
        }
        for (int i = 0; i < q; i++) {
            int u = query[i][0];
            int v = query[i][1];
            System.out.println(getLCA(a, u, v, p, d, n));
        }
    }
}