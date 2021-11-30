
/**
 * A_Queries
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_Queries {
    static int getBit(long pos, int v) {
        return ((int) pos >> v) & 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[] = br.readLine().split(" ");
        int q = Integer.parseInt(line[0]);
        int s0 = Integer.parseInt(line[1]);
        int a = Integer.parseInt(line[2]);
        int b = Integer.parseInt(line[3]);
        long[] arr = new long[1 << 26];
        // int[] arrS = new int[q + 1];
        // arrS[0] = s0;
        long sum = 0;
        long s = s0;
        for (int i = 0; i < q; i++) {
            if (i != 0)
                s = (a * s + b) % (1l << 32);

            if ((s & 1) == 1) { // lẻ => thêm
                long p = s / 2;
                long u = p >> 5;
                long v = p & 31;
                if (getBit(arr[(int) u], (int) v) == 0) { // chưa được đánh dấu
                    // đánh dấu
                    arr[(int) u] ^= (1 << v);
                    // cộng thêm vào sum
                    sum += p;
                }
            } else { // chẵn => xóa
                long p = s / 2;
                long u = p >> 5;
                long v = p & 31;
                if (getBit(arr[(int) u], (int) v) == 1) {
                    // bỏ đánh dấu
                    arr[(int) u] ^= (1 << v);
                    // trừ
                    sum -= p;
                }
            }
        }
        System.out.println(sum);
    }
}