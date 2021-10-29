
/**
 * A_Ch_n_hai_s_
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A_Ch_n_hai_s_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        long m = Long.parseLong(nLine[1]);
        String[] arrayString = br.readLine().split(" ");
        long[][] arr = new long[n][2];
        int j = n - 1;
        long res = 0;
        long max;
        long indexI = 0, indexJ = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = Long.parseLong(arrayString[i]);
            arr[i][1] = i;
        }
        Arrays.sort(arr, new java.util.Comparator<long[]>() {
            public int compare(long[] a, long[] b) {
                return Long.compare(a[0], b[0]);
            }
        });
        // Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            while (i < j && (double) arr[i][0] * (double) arr[j][0] > m) {
                j--;
            }
            if (j == i)
                break;
            max = arr[i][0] * arr[j][0];
            if (max > res) {
                res = max;
                indexI = arr[i][1] + 1;
                indexJ = arr[j][1] + 1;
            }
        }
        System.out.println(res);
        if (res != 0)
            System.out.print(indexI + " " + indexJ);
    }
}