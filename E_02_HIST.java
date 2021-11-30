
/**
 * E_02_HIST
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class E_02_HIST {
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
        while (true) {
            String[] line = br.readLine().split(" ");

            if (Integer.parseInt(line[0]) == 0 && line.length == 1)
                break;
            int n = Integer.parseInt(line[0]);
            Node firstNode = new Node(-1, 0);
            Stack<Node> stack = new Stack<>();
            stack.push(firstNode);
            int[] l = new int[n + 1];
            int[] r = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                int h = Integer.parseInt(line[i]);
                while (stack.peek().value >= h) {
                    stack.pop();
                }
                l[i] = stack.peek().index + 1;
                stack.push(new Node(h, i));
            }

            stack = new Stack<>();
            stack.push(new Node(-1, n + 1));
            for (int i = n; i > 0; i--) {
                int h = Integer.parseInt(line[i]);
                while (stack.peek().value >= h) {
                    stack.pop();
                }
                r[i] = stack.peek().index - 1;
                stack.push(new Node(h, i));
            }

            long result = 0;

            for (int i = 1; i < n + 1; i++) {
                int h = Integer.parseInt(line[i]);
                result = Math.max(result, 1l * h * (r[i] - l[i] + 1));
            }
            System.out.println(result);
        }
    }
}