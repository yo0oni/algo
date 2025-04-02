import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[2 * n];
        for (int i = 0; i < n; i++) {
            sushi[i] = sushi[i + n] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[d + 1];
        int uniqueCount = 0;

        for (int i = 0; i < k; i++) {
            if (count[sushi[i]] == 0) uniqueCount++;
            count[sushi[i]]++;
        }

        int max = (count[c] == 0) ? uniqueCount + 1 : uniqueCount;

        for (int i = 1; i < n; i++) {
            int out = sushi[i - 1];
            int in = sushi[i + k - 1];

            count[out]--;
            if (count[out] == 0) uniqueCount--;

            if (count[in] == 0) uniqueCount++;
            count[in]++;

            int current = (count[c] == 0) ? uniqueCount + 1 : uniqueCount;
            max = Math.max(max, current);
        }

        System.out.println(max);
    }
}
