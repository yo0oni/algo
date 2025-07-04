import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			dp[i] = i;

			for (int j = 1; j * j <= i; j++) {
				dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
			}
		}

		System.out.println(dp[N]);
	}
}