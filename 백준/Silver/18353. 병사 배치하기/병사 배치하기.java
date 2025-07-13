import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] warriors;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		warriors = new int[N];
		dp = new int[N];
		Arrays.fill(dp, 1);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			warriors[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N; i ++) {
			for(int j = 0; j < i; j ++) {
				if(warriors[i] < warriors[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
		}
		
		int maxLen = 0;
		for (int i = 0; i < N; i++) {
		    if (dp[i] > maxLen) maxLen = dp[i];
		}
		
		System.out.println(N- maxLen);
		
	}
}
