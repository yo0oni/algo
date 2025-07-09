import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] pre;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        pre = new int[N];
        dp = new int[N];
        Arrays.fill(pre, -1);
        Arrays.fill(dp, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) {
                    if(dp[i] < dp[j]+1) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }
            }
        }

        int max = 0;
        int last = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            if(dp[i] > max) {
                max = dp[i];
                last = i;
            }
        }
        System.out.println(max);
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        while(last > -1) {
            dq.offerFirst(arr[last]);
            last = pre[last];
        }

        while(!dq.isEmpty()) {
            sb.append(dq.pollFirst()).append(" ");
        }
        System.out.println(sb);
    }
}