import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[d + 1];
        int kind = 0;

        for (int i = 0; i < k; i++) {
            if (count[sushi[i]]++ == 0) kind++;
        }

        int max = kind;
        if (count[c] == 0) max++;

        for (int i = 1; i < N; i++) {
            if (--count[sushi[(i - 1) % N]] == 0) kind--;
            if (count[sushi[(i + k - 1) % N]]++ == 0) kind++;

            int temp = kind;
            if (count[c] == 0) temp++;

            if (temp > max) max = temp;
        }

        System.out.println(max);
    }
}
