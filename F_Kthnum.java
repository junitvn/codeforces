
/**
 * F_Kthnum
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class F_Kthnum {

    static class Node {
        int l, r;
        Node left, right;
        long[] list;

        public Node() {
        };

        public Node(int l, int r, Node left, Node right, int[] a) {
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
            this.list = new long[r - l + 1];
            int index = 0;
            for (int i = l; i <= r; i++) {
                if (i <= a.length) {
                    this.list[index++] = a[i];
                }
            }
            Arrays.sort(this.list);
        }

    }

    static Node build(int l, int r, int[] a) {
        if (l == r) {
            return new Node(l, r, null, null, a);
        } else {
            int mid = (l + r) / 2;
            Node left = build(l, mid, a);
            Node right = build(mid + 1, r, a);
            return new Node(l, r, left, right, a);
        }
    }

    static int getCountNumberSmallerThanK(Node currentNode, int k, int x, int y) {
        if (x <= currentNode.l && currentNode.r <= y) {
            return bs(currentNode.list, k);
        } else {
            int sum = 0;
            int mid = (currentNode.l + currentNode.r) / 2;
            if (mid >= x) {
                sum += getCountNumberSmallerThanK(currentNode.left, k, x, y);
            }
            if (mid + 1 <= y) {
                sum += getCountNumberSmallerThanK(currentNode.right, k, x, y);
            }
            return sum;
        }
    }

    private static int bs(long[] list, int k) {
        int l = -1;
        int r = list.length;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (list[mid] > k) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    static int bsResult(Node root, int x, int y, int k) {
        int l = -1000000000;
        int r = 1000000000;
        while (r - l > 1) {
            int mid = (r - l) / 2 + l;
            if (getCountNumberSmallerThanK(root, mid, x, y) < k) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        int q = Integer.parseInt(nLine[1]);
        String[] line = br.readLine().split(" ");
        int arr[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(line[i - 1]);
        }

        Node root = build(1, n, arr);

        for (int i = 0; i < q; i++) {
            String[] lineQ = br.readLine().split(" ");
            int x = Integer.parseInt(lineQ[0]);
            int y = Integer.parseInt(lineQ[1]);
            int k = Integer.parseInt(lineQ[2]);
            System.out.println(bsResult(root, x, y, k));
        }
    }
}