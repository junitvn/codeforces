
/**
 * C_S_N_GOLF
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class C_S_N_GOLF {

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

    static int check(int i, int j, int[][] arr) {
        if (arr[i][j] >= arr[i - 1][j] && arr[i][j] >= arr[i][j - 1] && arr[i - 1][j] >= arr[i - 1][j - 1]
                && arr[i][j - 1] >= arr[i - 1][j - 1]) {
            return 1;
        }
        return 0;
    }

    static long histogram(int[] h, int row) {
        int n = h.length;
        Node firstNode = new Node(-1, 0);
        Stack<Node> stack = new Stack<>();
        stack.push(firstNode);
        int[] l = new int[n + 1];
        int[] r = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            int height = h[i - 1];
            while (stack.peek().value >= height) {
                stack.pop();
            }
            l[i] = stack.peek().index + 1;
            stack.push(new Node(height, i));
        }

        stack = new Stack<>();
        stack.push(new Node(-1, n + 1));
        for (int i = n; i > 0; i--) {
            int height = h[i - 1];
            while (stack.peek().value >= height) {
                stack.pop();
            }
            r[i] = stack.peek().index - 1;
            stack.push(new Node(height, i));
        }

        long result = 0;

        for (int i = 1; i < n + 1; i++) {
            int height = h[i - 1];
            result = Math.max(result, 1l * (height + 1) * (r[i] - l[i] + 2));
            // if (1l * (height + 1) * (r[i] - l[i] + 2) == 5341) {
            // System.out.println("i=" + i);
            // System.out.println("h[i]=" + h[i]);
            // System.out.println("r[i]=" + r[i] + ", " + "l[i]=" + l[i]);
            // System.out.println("row=" + row);
            // }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(
        // new InputStreamReader(new
        // FileInputStream("D://IT//codeforces//test11.txt")));

        // String[] mnLine = br.readLine().split(" ");
        // int m = Integer.parseInt(mnLine[0]);
        // int n = Integer.parseInt(mnLine[1]);
        // FileWriter fileWriter = new FileWriter("A.txt");
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] arr = new int[m][n];
        int[][] mark = new int[m - 1][n - 1];
        for (int i = 0; i < m; i++) {
            // String[] row = br.readLine().split(" ");
            // fileWriter.append("row " + i + " -> " + row.length);
            // fileWriter.append("\n");

            for (int j = 0; j < n; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        long res = 0;
        for (int i = 0; i < m; i++) {
            int count = 1;
            for (int j = 1; j < n; j++) {
                if (arr[i][j] < arr[i][j - 1]) {
                    count = 1;
                } else
                    count++;
                res = Math.max(res, count);
            }
        }

        for (int j = 0; j < n; j++) {
            int count = 1;
            for (int i = 1; i < m; i++) {
                if (arr[i][j] < arr[i - 1][j]) {
                    count = 1;
                } else
                    count++;
                res = Math.max(res, count);
            }
        }
        boolean flag = false;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                mark[i - 1][j - 1] = check(i, j, arr);
                if (mark[i - 1][j - 1] == 1)
                    flag = true;
            }
        }

        // for (int i = 1079; i <= 1127; i++) {
        // for (int j = 1022; j <= 1130; j++) {
        // fileWriter.append(arr[i][j] + " ");
        // }
        // fileWriter.append("\n");
        // }
        // fileWriter.close();
        if (flag == false) {
            System.out.println(res);
            return;
        }
        int[] h = new int[n - 1];
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (mark[i][j] == 0) {
                    h[j] = 0;
                } else {
                    h[j]++;
                }
            }
            res = Math.max(res, histogram(h, i));
        }
        System.out.println(res);
    }
}