
/**
 * C_Dowry
 */

// i nho nhat
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class C_Dowry {
    static class CustomComparator implements Comparator<Gem> {
        public int compare(Gem o1, Gem o2) {
            long result = o1.weight - o2.weight;
            if (result < 0)
                return -1;
            else if (result > 0)
                return 1;
            return 0;
        }
    }

    static class Gem {
        public long weight;
        public long value;

        public Gem() {
        };

        public Gem(long w, long v) {
            this.weight = w;
            this.value = v;
        }
    }

    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    static int getBit(long mask, int pos) {
        return (int) (mask >> pos) & 1;
    }

    static int searchI(long L, ArrayList<Gem> gems, long sumWeightInPart1) {
        int l = -1;
        int r = gems.size();
        while (r - l > 1) {
            int mid = (r + l) / 2;
            if (L - sumWeightInPart1 <= gems.get(mid).weight) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    static int searchJ(long R, ArrayList<Gem> gems, long sumWeightInPart1) {
        int l = -1;
        int r = gems.size();
        while (r - l > 1) {
            int mid = (r + l) / 2;
            if (R - sumWeightInPart1 >= gems.get(mid).weight) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    static ArrayList<Gem> generateGemCollection(ArrayList<Gem> gems) {
        ArrayList<Gem> gemsResult = new ArrayList<>();
        for (long mask = 0; mask < (1l << gems.size()); mask++) {
            long sumWeight = 0;
            long sumValue = 0;
            for (int i = 0; i < gems.size(); i++) {
                sumWeight += getBit(mask, i) * gems.get(i).weight;
                sumValue += getBit(mask, i) * gems.get(i).value;
            }
            gemsResult.add(new Gem(sumWeight, sumValue));
        }
        return gemsResult;
    }

    static long getMaxBetweenIJ(long[][] st, int i, int j) {
        int k = log2(j - i + 1);
        return Math.max(st[i][k], st[j - (1 << k) + 1][k]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        long l = Long.parseLong(firstLine[1]);
        long r = Long.parseLong(firstLine[2]);
        Gem[] gems = new Gem[n];
        for (int i = 0; i < n; i++) {
            String[] wvLine = br.readLine().split(" ");
            long w = Long.parseLong(wvLine[0]);
            long v = Long.parseLong(wvLine[1]);
            gems[i] = new Gem(w, v);
        }
        ArrayList<Gem> gemsPart1 = new ArrayList<>();
        for (int i = 0; i < n / 2; i++) {
            gemsPart1.add(gems[i]);
        }
        ArrayList<Gem> gemsPart2 = new ArrayList<>();
        for (int i = n / 2; i < n; i++) {
            gemsPart2.add(gems[i]);
        }

        ArrayList<Gem> generatedGemsPart1 = generateGemCollection(gemsPart1);
        ArrayList<Gem> generatedGemsPart2 = generateGemCollection(gemsPart2);

        Collections.sort(generatedGemsPart2, new CustomComparator());

        int sizeOfPart2 = generatedGemsPart2.size();

        long[][] st = new long[sizeOfPart2][log2(sizeOfPart2) + 1];
        for (int i = 0; i < sizeOfPart2; i++) {
            st[i][0] = generatedGemsPart2.get(i).value;
        }

        for (int j = 1; j <= log2(sizeOfPart2); j++) {
            for (int i = 0; i + (1 << j - 1) < sizeOfPart2; i++) {
                st[i][j] = Math.max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }

        long result = 0;
        for (int w = 0; w < generatedGemsPart1.size(); w++) {
            long sumWeightInPart1 = generatedGemsPart1.get(w).weight;
            long sumValueInPart1 = generatedGemsPart1.get(w).value;
            int i = searchI(l, generatedGemsPart2, sumWeightInPart1);
            int j = searchJ(r, generatedGemsPart2, sumWeightInPart1);
            if (i <= j) {
                result = Math.max(result, sumValueInPart1 + getMaxBetweenIJ(st, i, j));
            }
        }
        System.out.println(result);
    }
}