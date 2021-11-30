
/**
 * F_X_u_p
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F_X_u_p {

    static int bs(int[] p, int[] f, int x, int length) {
        int l = 0;
        int r = length;

        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (f[mid] >= x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (r == length)
            return -1;
        return p[r];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        int n = Integer.parseInt(lineOne[0]);
        String[] lineString = br.readLine().split("");
        int arr[] = new int[n + 1];
        long T[] = new long[n + 1];
        int p[] = new int[n + 2]; // vi tri so 1 thu k
        int f[] = new int[n + 2]; // f[k] = T[p[k]]
        int pIndex = 1;
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(lineString[i - 1]);
            if (arr[i] == 0) {
                T[i] = T[i - 1] + 1;
            } else {
                T[i] = T[i - 1];
                p[pIndex] = i;
                f[pIndex] = (int) T[i];
                pIndex++;
            }
        }
        f[pIndex] = Integer.MAX_VALUE;
        int res = pIndex - 1;
        for (int x = 1; x <= n / 2; x++) {
            int i = 0;
            int count = 0;
            while (true) {
                int j = bs(p, f, (int) T[i] + x, pIndex);
                if (j > 0) {
                    i = j;
                    count++;
                } else {
                    break;
                }
            }
            if (T[n] - T[i] < x)
                count--;
            if (count > 0)
                res = Math.max((x * (count + 1) + count), res);
        }
        System.out.println(res);
    }
}