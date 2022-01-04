
/**
 * B_Th_p_HN
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class B_Th_p_HN {

    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    static int getBit(long mask, int pos) {
        return (int) (mask >> pos) & 1;
    }

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

    static void update(Node currentNode, int index, long value) {
        if (currentNode.l == currentNode.r) {
            currentNode.max = value;
            return;
        }
        if (currentNode.left.l <= index && index <= currentNode.left.r) {
            update(currentNode.left, index, value);
        } else {
            update(currentNode.right, index, value);
        }
        currentNode.max = currentNode.left.max + currentNode.right.max;
    }

    static long getMax(Node currentNode, long i, long j) {
        if (i > j)
            return 0;
        if (i <= currentNode.l && currentNode.r <= j) {
            return currentNode.max;
        }
        long maxLeft = 0;
        if (currentNode.left.r >= i) {
            maxLeft = getMax(currentNode.left, i, j);
        }
        long maxRight = 0;
        if (currentNode.right.l <= j) {
            maxRight = getMax(currentNode.right, i, j);
        }
        return maxLeft + maxRight;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        String[] line = br.readLine().split(" ");
        int arr[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(line[i - 1]);
        }
        String[] sLine = br.readLine().split(" ");
        int s = Integer.parseInt(sLine[0]);
        int next[][] = new int[log2(s) + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            next[0][i] = arr[i];
        }
        for (int k = 1; k <= log2(s); k++) {
            for (int i = 1; i <= n; i++) {
                next[k][i] = next[k - 1][next[k - 1][i]];
            }
        }
        int A[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int index = i;
            for (int k = 0; k <= log2(s); k++) {
                if (getBit(s, k) == 1) {
                    index = next[k][index];
                }
            }
            A[index] = i;
        }
        int initialArray[] = new int[n + 1];
        Node root = buildSegmentTree(1, A.length, initialArray);
        long res = 0;
        // cây segment tree lưu số lần xuất hiện của các số, rồi từ cây đó tính tổng số
        // lượng số có trong đoạn a[i] + 1 -> n
        for (int i = 1; i <= n; i++) {
            res += getMax(root, A[i] + 1, n);
            update(root, A[i], 1);
        }
        System.out.println(res);
    }
}