import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * C_Dance
 */
public class C_Dance {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nLine = br.readLine();
        String student[] = br.readLine().split("");
        int n = Integer.parseInt(nLine) + 1;
        int arr[] = new int[n];
        int T[] = new int[n];
        int countT[] = new int[12000000];
        long res = 0;
        String firstCharacter = student[0];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (student[i - 1].equals(firstCharacter)) {
                arr[i] = 1;
            } else {
                arr[i] = -1;
            }
            T[i] = T[i - 1] + arr[i];
            if (T[i] < min)
                min = T[i];
        }

        int increase = min > 0 ? 0 : 0 - min;
        countT[increase]++;

        for (int i = 1; i < n; i++) {
            T[i] += increase;
            res += countT[T[i]];
            countT[T[i]]++;
        }
        System.out.print(res);
    }
}