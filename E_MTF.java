
/**
 * E_MTF
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class E_MTF {
    static class Node {
        long max;
        int l, r;
        Node left, right;

        public Node() {
            this.max = 0;
            this.l = 0;
            this.r = 0;
            this.left = null;
            this.right = null;
        };

        public Node(long max, int l, int r, Node left, Node right) {
            this.max = max;
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
        }
    }

    static Node buildSegmentTree(int l, int r, int[] arr) {
        if (l == r)
            return new Node(arr[l - 1], l, r, null, null);
        int mid = (r + l) / 2;
        Node left = buildSegmentTree(l, mid, arr);
        Node right = buildSegmentTree(mid + 1, r, arr);
        return new Node(left.max + right.max, l, r, left, right);
    }

    static void update(Node currentNode, long index, long value) {
        if (currentNode.l == currentNode.r) {
            currentNode.max += value;
            return;
        }
        if (currentNode.left.l <= index && index <= currentNode.left.r) {
            update(currentNode.left, index, value);
        } else {
            update(currentNode.right, index, value);
        }
        currentNode.max = currentNode.left.max + currentNode.right.max;
    }

    static void update2(Node currentNode, long index, long value) {
        if (currentNode.l == currentNode.r) {
            currentNode.max += value;
        } else {
            int mid = (currentNode.l + currentNode.r) / 2;
            if (index <= mid) {
                if (currentNode.left == null) {
                    currentNode.left = new Node(0, currentNode.l, mid, null, null);
                }
                update2(currentNode.left, index, value);
            } else {
                if (currentNode.right == null) {
                    currentNode.right = new Node(0, mid + 1, currentNode.r, null, null);
                }
                update2(currentNode.right, index, value);
            }
            long maxLeft = currentNode.left != null ? currentNode.left.max : 0;
            long maxRight = currentNode.right != null ? currentNode.right.max : 0;
            currentNode.max = maxLeft + maxRight;
        }
    }

    static long getSum(Node currentNode, long i, long j) {
        if (currentNode == null)
            return 0;
        if (i > j)
            return 0;
        if (i <= currentNode.l && currentNode.r <= j) {
            return currentNode.max;
        }
        long maxLeft = 0;
        if (currentNode.left.r >= i) {
            maxLeft = getSum(currentNode.left, i, j);
        }
        long maxRight = 0;
        if (currentNode.right.l <= j) {
            maxRight = getSum(currentNode.right, i, j);
        }
        return maxLeft + maxRight;
    }

    static long getSum2(Node currentNode, long i, long j) {
        if (currentNode == null)
            return 0;
        if (i > j)
            return 0;
        if (i <= currentNode.l && currentNode.r <= j) {
            return currentNode.max;
        }
        long sum = 0;
        long mid = (currentNode.l + currentNode.r) / 2;
        if (mid >= i) {
            sum += getSum2(currentNode.left, i, j);
        }
        if (mid + 1 <= j) {
            sum += getSum2(currentNode.right, i, j);
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        String[] line = br.readLine().split(" ");
        long arr[] = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(line[i - 1]);
        }
        int MAX = 1000000000;
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        int initialArray[] = new int[n + 1];
        Node root1 = buildSegmentTree(1, n, initialArray);
        Node root2 = new Node(0, 1, MAX, null, null);
        for (int i = 1; i <= n; i++) {
            if (map.getOrDefault(arr[i], 0) != 0) {
                long prev = map.get(arr[i]);
                long s = getSum(root1, prev + 1, i - 1);
                System.out.print(s + 1 + " ");
                update(root1, prev, -1);
                update(root1, i, 1);
            } else {
                long s = getSum2(root2, arr[i] + 1, MAX);
                System.out.print(s + arr[i] + " ");
                update2(root2, arr[i], 1);
                update(root1, i, 1);
            }
            map.put(arr[i], i);
        }
    }

}