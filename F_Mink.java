
/**
 * F_Mink
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class F_Mink {
    static class Node {
        public long value;
        public int index;

        public Node() {
        };

        public Node(long value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tcLine = br.readLine().split(" ");
        int tc = Integer.parseInt(tcLine[0]);

        for (int testCase = 0; testCase < tc; testCase++) {
            String[] nkLine = br.readLine().split(" ");
            int n = Integer.parseInt(nkLine[0]);
            int k = Integer.parseInt(nkLine[1]);
            String[] aLine = br.readLine().split(" ");
            long arr[] = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = Long.parseLong(aLine[i - 1]);
            }
            ArrayList<Long> result = new ArrayList<>();
            Deque<Node> apm = new ArrayDeque<>();
            apm.add(new Node(Long.MAX_VALUE, 0));
            for (int i = 0; i <= n; i++) {

                while (!apm.isEmpty() && i - apm.getFirst().index >= k) {
                    apm.removeFirst();
                }
                if (apm.isEmpty())
                    apm.add(new Node(arr[i], i));
                else {
                    while (!apm.isEmpty() && arr[i] <= apm.getLast().value) {
                        apm.removeLast();
                    }
                    apm.add(new Node(arr[i], i));
                }
                if (!apm.isEmpty())
                    result.add(apm.getFirst().value);
            }

            for (Long res : result) {
                if (res == 0)
                    continue;
                System.out.print(res + " ");
            }
            System.out.println();
        }
    }
}