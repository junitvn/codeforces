import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * L_i_ch_
 */
public class L_i_ch_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = line.length();
        String doubleLine[] = line.concat(line).split("");
        int n2 = doubleLine.length;
        String s[] = new String[n2];
        s[0] = "";
        long[] TA = new long[n2];
        long[] TB = new long[n2];
        long[] TC = new long[n2];
        int na = 0, nb = 0, nc = 0;
        long min = Long.MAX_VALUE;
        for (int i = 1; i < n2; i++) {
            s[i] = doubleLine[i - 1];
            if (s[i].equals("A")) {
                TA[i] = TA[i - 1] + 1;
                TB[i] = TB[i - 1];
                TC[i] = TC[i - 1];
            } else if (s[i].equals("B")) {
                TB[i] = TB[i - 1] + 1;
                TA[i] = TA[i - 1];
                TC[i] = TC[i - 1];
            } else if (s[i].equals("C")) {
                TC[i] = TC[i - 1] + 1;
                TA[i] = TA[i - 1];
                TB[i] = TB[i - 1];
            }
        }
        na = (int) TA[n];
        nb = (int) TB[n];
        nc = (int) TC[n];
        for (int i = 1; i <= n; i++) {
            long sa = na - TA[na + i - 1] + TA[i - 1];
            long sb = nb - TB[na + nb + i - 1] + TB[na + i - 1];
            long sc = nc - TC[na + nc + i - 1] + TC[na + i - 1];
            long sab_n = TA[na + nb + i - 1] - TA[na + i - 1];
            long sac_n = TA[na + nc + i - 1] - TA[na + i - 1];
            long sb_n = TB[na + i - 1] - TB[i - 1];
            long sc_n = TC[na + i - 1] - TC[i - 1];

            long minAB = sa + sb - Math.min(sab_n, sb_n);
            long minAC = sa + sc - Math.min(sac_n, sc_n);

            if (minAB < min)
                min = minAB;
            if (minAC < min)
                min = minAC;
        }
        System.out.print(min);
    }
}