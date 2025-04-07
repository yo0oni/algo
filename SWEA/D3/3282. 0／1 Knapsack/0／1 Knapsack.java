import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N, K;
	static int V, C;
	static int[][] dp;

	static int[] value;
	static int[] weight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			dp = new int[N + 1][K + 1];
			value = new int[N + 1];
			weight = new int[K + 1];

			for (int i = 1; i < N + 1; i++) {
				st = new StringTokenizer(br.readLine());
				weight[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());

			}
			for (int j = 0; j <= K; j++) {
				if (j >= weight[1]) {
					dp[1][j] = value[1];
				}
			}

			for (int i = 2; i < N + 1; i++) {
				for (int j = 0; j < K + 1; j++) {
					if (j >= weight[i]) {
						dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + value[i], dp[i - 1][j]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
			sb.append("#").append(t).append(" ").append(dp[N][K]).append("\n");
		}
		System.out.println(sb);
	}
}
