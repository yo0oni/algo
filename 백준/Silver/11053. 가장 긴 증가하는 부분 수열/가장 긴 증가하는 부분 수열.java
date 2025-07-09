import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];
        Arrays.fill(dp, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i ++) {
            for(int j = 0; j < i; j ++) {
                if(A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}