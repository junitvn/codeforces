import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * B_Maximum_Xor_Secondary
 */
public class B_Maximum_Xor_Secondary {
    static class Node {
        public int value;
        public int index;

        public Node() {
        };

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);

        String[] sLine = br.readLine().split(" ");
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(sLine[i]);
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int j = 0; j < n; j++) {
            while (!stack.isEmpty() && stack.peek() <= s[j]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                res = Math.max(res, stack.peek() ^ s[j]);
            }
            stack.add(s[j]);
        }

        stack = new Stack<>();
        for (int j = n - 1; j >= 0; j--) {
            while (!stack.isEmpty() && stack.peek() <= s[j]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                res = Math.max(res, stack.peek() ^ s[j]);
            }
            stack.add(s[j]);
        }
        System.out.println(res);
    }
}