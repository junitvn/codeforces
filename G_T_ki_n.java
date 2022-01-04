
/**
 * G_T_ki_n
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class G_T_ki_n {

    static class Node {
        int current;
        int parent;
        int distance;

        public Node() {
        }

        public Node(int current, int parent, int distance) {
            this.current = current;
            this.parent = parent;
            this.distance = distance;
        }
    }

    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    static int getBit(long mask, int pos) {
        return (int) (mask >> pos) & 1;
    }

    static void calculate_deep(int[] d, ArrayList<Node>[] c, int r, long length[]) {
        for (Node i : c[r]) {
            d[i.current] = d[r] + 1;
            length[i.current] = length[r] + i.distance;
            calculate_deep(d, c, i.current, length);
        }
    }

    static int getLCA(int a[][], int u, int v, Node p[], int[] d, int n) {

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
        return p[u].parent;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] nLine = br.readLine().split(" ");
            int n = Integer.parseInt(nLine[0]);
            if (n == 0) {
                break;
            }
            Node p[] = new Node[n + 1];
            p[0] = new Node(0, 0, 0);
            for (int i = 1; i <= n - 1; i++) {
                String[] line = br.readLine().split(" ");
                int a = Integer.parseInt(line[0]);
                int l = Integer.parseInt(line[1]);
                p[i] = new Node(i, a, l);
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

            ArrayList<Node>[] c = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                c[i] = new ArrayList<>();
            }
            for (int i = 1; i <= n - 1; i++) {
                c[p[i].parent].add(p[i]);
                a[i][0] = p[i].parent;
            }
            long length[] = new long[n + 1];
            int d[] = new int[n + 1];
            calculate_deep(d, c, 0, length);

            for (int i = 1; i <= log2(n); i++) {
                for (int u = 1; u <= n; u++) {
                    a[u][i] = a[a[u][i - 1]][i - 1];
                }
            }

            for (int i = 0; i < q; i++) {
                int u = query[i][0];
                int v = query[i][1];
                int lca = getLCA(a, u, v, p, d, n);
                System.out.print(length[u] + length[v] - 2 * length[lca] + " ");
            }
            System.out.println();
        }
    }
}