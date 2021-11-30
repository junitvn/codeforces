
/**
 * D_Count_prod_squares
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D_Count_prod_squares {

    static long ckn(int k, int n) {
        // if (k == 0 || k == n)
        // return 1;
        // if (k == 1)
        // return n;
        // return ckn(k - 1, n - 1) + ckn(k, n - 1);
        return n * (n - 1) * (n - 2) / 6;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineOne = br.readLine().split(" ");
        long n = Long.parseLong(lineOne[0]);
        int arr[] = new int[(int) (n + 1)];
        if (n < 9) {
            System.out.println("0");
            return;
        }
        for (int i = 2; i <= n; i++) {
            if (arr[i] == 0) {
                for (int j = i; j <= n; j += i) {
                    arr[j] = i;
                }
            }
        }
        int numOfPerfectSquare = 0; // số lượng số chính phương
        int[] countOddExponents = new int[(int) (n + 1)];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                numOfPerfectSquare++;
                continue;
            }
            int t = i;
            int radix = arr[t];
            int productOddExponents = 1; // tích các thừ số có số mũ lẻ
            while (t > 1) {
                int exponent = 0;
                while (t % radix == 0) {
                    t /= radix;
                    exponent++;
                }
                if (exponent % 2 == 1) { // số mũ với cơ số arr[radixIndex] là lẻ
                    productOddExponents *= radix;
                }
                radix = arr[t];
            }
            if (productOddExponents == 1) { // tất cả số mũ là chẵn
                numOfPerfectSquare++;
            } else {
                countOddExponents[productOddExponents]++;
            }
        }
        long res = 0;
        for (int i = 0; i < countOddExponents.length; i++) {
            if (countOddExponents[i] >= 3) {
                res += ckn(3, countOddExponents[i]);
            }
        }
        res += ckn(3, numOfPerfectSquare);
        System.out.println(res);

    }

}