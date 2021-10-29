
/**
 * B_Scores
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class B_Scores {

    static int A(int x, ArrayList<Integer> L) {
        int l = -1;
        int r = L.size();
        while (r - l > 1) {
            int mid = (r + l) / 2;
            if (L.get(mid) > x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nmLine[] = br.readLine().split(" ");
        int n = Integer.parseInt(nmLine[0]);
        int m = Integer.parseInt(nmLine[1]);
        String[] cLine = br.readLine().split(" ");
        String[] sLine = br.readLine().split(" ");

        ArrayList<Integer>[] classInX = new ArrayList[n];
        for (int i = 0; i < m; i++) {
            int c = Integer.parseInt(cLine[i]) - 1;
            int s = Integer.parseInt(sLine[i]);
            if (classInX[c] == null)
                classInX[c] = new ArrayList<Integer>();
            classInX[c].add(s);
        }
        for (int i = 0; i < classInX.length; i++) {
            if (classInX[i] != null)
                Collections.sort(classInX[i]);
        }

        for (int i = 0; i < m; i++) {
            int s = Integer.parseInt(sLine[i]);
            int c = Integer.parseInt(cLine[i]) - 1;
            int res1 = A(s, classInX[c]) - A(s - 1, classInX[c]) - 1;
            int res2 = classInX[c].size() - A(s, classInX[c]);
            System.out.println(res1 + " " + res2);
        }
    }
}