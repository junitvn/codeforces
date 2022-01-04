
/**
 * G_ng_s_t_li_n_v_n
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class G_ng_s_t_li_n_v_n {
    static class Node {
        long max, lazy;
        int l, r;
        Node left, right;

        public Node() {
            this.max = 0;
            this.lazy = 0;
            this.l = 0;
            this.r = 0;
            this.left = null;
            this.right = null;
        };

        public Node(long max, long lazy, int l, int r, Node left, Node right) {
            this.max = max;
            this.lazy = lazy;
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
        }
    }

    static void update(Node currentNode, int x, int y, int value) {
        if (x <= currentNode.l && currentNode.r <= y) {
            currentNode.max += value;
            currentNode.lazy += value;
        } else {
            if (currentNode.left == null || currentNode.right == null)
                return;
            propagate(currentNode);
            if (currentNode.right.r >= x) {
                update(currentNode.left, x, y, value);
            }
            if (currentNode.left.l <= y) {
                update(currentNode.right, x, y, value);
            }
            currentNode.max = Math.max(currentNode.left.max, currentNode.right.max);
        }
    }

    static long getMax(Node currentNode, int x, int y) {
        if (x > y)
            return 0;
        if (x <= currentNode.l && currentNode.r <= y) {
            return currentNode.max;
        }
        propagate(currentNode);
        long maxLeft = Long.MIN_VALUE;
        if (currentNode.left.r >= x) {
            maxLeft = getMax(currentNode.left, x, y);
        }
        long maxRight = Long.MIN_VALUE;
        if (currentNode.right.l <= y) {
            maxRight = getMax(currentNode.right, x, y);
        }
        return Math.max(maxLeft, maxRight);
    }

    private static void propagate(Node currentNode) {
        if (currentNode.left == null || currentNode.right == null)
            return;
        currentNode.left.max += currentNode.lazy;
        currentNode.left.lazy += currentNode.lazy;
        currentNode.right.max += currentNode.lazy;
        currentNode.right.lazy += currentNode.lazy;
        currentNode.lazy = 0;
    }

    static Node buildSegmentTree(int l, int r) {
        if (l == r)
            return new Node(0, 0, l, r, null, null);
        int mid = (r + l) / 2;
        Node left = buildSegmentTree(l, mid);
        Node right = buildSegmentTree(mid + 1, r);
        return new Node(0, 0, l, r, left, right);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        int m = Integer.parseInt(nLine[1]);
        int z = Integer.parseInt(nLine[2]);

        Node root = buildSegmentTree(1, n);

        for (int i = 0; i < z; i++) {
            String[] lineQ = br.readLine().split(" ");
            int u = Integer.parseInt(lineQ[0]); // start station
            int v = Integer.parseInt(lineQ[1]) - 1; // end station
            int w = Integer.parseInt(lineQ[2]); // num of required seat
            update(root, u, v, w);
            if (getMax(root, u, v) <= m) {
                System.out.println("T");
            } else {
                update(root, u, v, -w);
                System.out.println("N");
            }
        }
    }
}