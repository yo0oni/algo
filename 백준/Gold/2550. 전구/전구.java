import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static HashMap<Integer, Integer> indexing;
    static ArrayList<Integer> numbers;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new ArrayList<>();
        numbers.add(0);
        dp = new int[N + 1];
        indexing = new HashMap<>();
        Arrays.fill(dp, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            indexing.put(Integer.parseInt(st.nextToken()), i + 1);
        }

        System.out.println();

        int[] prefix = new int[N + 1];
        Arrays.fill(dp, 1);
        for (int i = 0; i <= N; i++) {
            prefix[i] = i;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < i; j++) {
                if (indexing.get(numbers.get(i)) > indexing.get(numbers.get(j))) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        prefix[i] = j;
                    }
                }
            }
        }

        int maxLength = 0;
        int maxIndex = -1;
        for (int i = 1; i < N + 1; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        while (prefix[maxIndex] != maxIndex) {
            answer.add(numbers.get(maxIndex));
            maxIndex = prefix[maxIndex];
        }
        answer.add(numbers.get(maxIndex));
        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        sb.append(maxLength).append("\n");
        for (int num : answer) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }
}