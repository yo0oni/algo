import java.io.*;
import java.util.*;

public class Main {
    static int A;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine());
        arr = new int[A];
        dp = new int[A];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            int number = Integer.parseInt(st.nextToken());
            arr[i] = number;
            dp[i] = number;
        }

        for (int i = 1; i < A; i++) {
            for(int j = 0; j < i; j ++) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
        }

        int max = 0;
        for(int i = 0; i < A; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}