
/**
 * C_Compass
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;

public class C_Compass {
    static class CustomComparator implements Comparator<Distance> {
        public int compare(Distance o1, Distance o2) {
            long result = o1.distance - o2.distance;
            return (int) result;
        }
    }

    static class Point {
        int x;
        int y;

        public Point() {
        };

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object startObject) {
            if (this == startObject)
                return true;
            if (startObject == null || getClass() != startObject.getClass())
                return false;
            Point star = (Point) startObject;
            return x == star.x && y == star.y;
        }
    }

    static class Distance {
        Point a = new Point();
        Point b = new Point();
        long distance;

        public Distance(Point a, Point b) {
            this.a.x = a.x;
            this.b.x = b.x;
            this.a.y = a.y;
            this.b.y = b.y;
            this.distance = (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nLine[] = br.readLine().split(" ");
        int n = Integer.parseInt(nLine[0]);
        ArrayList<Distance> dis = new ArrayList<Distance>();
        Point[] points = new Point[n];

        int res = 0;
        ArrayList<Double> arr = new ArrayList<Double>();
        for (int i = 0; i < n; i++) {
            String pointStrings[] = br.readLine().split(" ");
            int x = Integer.parseInt(pointStrings[0]);
            int y = Integer.parseInt(pointStrings[1]);
            points[i] = new Point(x, y);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dis.add(new Distance(points[i], points[j]));
            }
        }
        Collections.sort(dis, new CustomComparator());
        int i = 0, j = 0;
        HashSet<Point> set;
        while (true) {
            set = new HashSet<Point>();
            while (j + 1 < dis.size() && dis.get(i).distance == dis.get(j).distance) {
                set.add(dis.get(j).a);
                set.add(dis.get(j).b);
                j++;
            }
            set.add(dis.get(j).a);
            set.add(dis.get(j).b);
            if (set.size() == n) {
                res++;
                arr.add(Math.sqrt(dis.get(i).distance));
            }
            i = j;
            if (j == dis.size() - 1)
                break;
        }
        System.out.println(res);
        for (Double a : arr) {
            System.out.println(a);
        }
    }
}