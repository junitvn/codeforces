import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * D_Ngo_c_ng
 */
public class D_Ngo_c_ng {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String brackets[] = br.readLine().split("");
        int n = brackets.length + 1;
        int arr[] = new int[n];
        int T[] = new int[n];
        int countT[] = new int[12000000];
        long res = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (brackets[i - 1].equals("(")) {
                arr[i] = 1;
            } else {
                arr[i] = -1;
            }
            T[i] = T[i - 1] + arr[i];
            if (T[i] < min)
                min = T[i];
        }

        int increase = min > 0 ? 0 : 0 - min;
        T[0] = increase;
        countT[T[0]]++;

        for (int i = 1; i < n; i++) {
            T[i] += increase;
            if (T[i] < T[i - 1]) {
                countT[T[i - 1]] = 0;
                res += countT[T[i]];
            }
            countT[T[i]]++;

        }
        System.out.print(res);
    }
}