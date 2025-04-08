import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] memory;
	static int[] cost;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memory = new int[N];
		cost = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;
		for (int c : cost)
			sum += c;

		dp = new int[sum + 1];

		for (int i = 0; i < N; i++) {
			for (int j = sum; j >= cost[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
			}
		}

		for (int i = 0; i <= sum; i++) {
			if (dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}

	}
}
