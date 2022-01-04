
/**
 * C_D_Y_CUNG
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class C_D_Y_CUNG {
    static class CustomComparator implements Comparator<Cung> {
        public int compare(Cung o1, Cung o2) {
            long result = o1.start - o2.start;
            return (int) result;
        }
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

    static class Cung {
        int start;
        int end;

        Cung() {
        };

        Cung(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nLine = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        ArrayList<Cung> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            if (start > end) {
                int temp = start;
                start = end;
                end = temp;
            }
            A.add(new Cung(start, end));
        }
        Collections.sort(A, new CustomComparator());

        int initialArray[] = new int[2 * n + 1];
        Node root = buildSegmentTree(1, 2 * n, initialArray);
        long res = 0;
        // cây segment tree lưu số lần xuất hiện của các số, rồi từ cây đó tính tổng số
        // lượng số có trong đoạn a[i] + 1 -> n
        for (int i = 0; i < n; i++) {
            //
            res += getMax(root, A.get(i).start, A.get(i).end);
            update(root, A.get(i).end, 1);
        }
        System.out.println(res);
    }
}