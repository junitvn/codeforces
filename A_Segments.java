
/**
 * A_Segments
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A_Segments {

    static int A(int x, int[] L) {
        int l = -1;
        int r = L.length;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (L[mid] > x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    static int B(int x, int[] R) {
        int l = -1;
        int r = R.length;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (R[mid] > x - 1) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nmLine[] = br.readLine().split(" ");
        int n = Integer.parseInt(nmLine[0]);
        int[] L = new int[n];
        int[] R = new int[n];
        for (int i = 0; i < n; i++) {
            String abLine[] = br.readLine().split(" ");
            int u = Integer.parseInt(abLine[0]);
            ;
            int v = Integer.parseInt(abLine[1]);
            if (u < v) {
                L[i] = u;
                R[i] = v;
            } else {
                L[i] = v;
                R[i] = u;
            }

        }
        Arrays.sort(L);
        Arrays.sort(R);
        String mLine[] = br.readLine().split(" ");
        for (int i = 0; i < mLine.length; i++) {
            int x = Integer.parseInt(mLine[i]);
            int res = A(x, L) - A(x - 1, R);
            System.out.print(res + " ");
        }

    }
}