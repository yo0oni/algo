import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int N, L;
	static int taste, kcal;
	static Hamburger[] hamburger;
	static int[][] dp;

	static class Hamburger {
		int taste;
		int kcal;

		public Hamburger(int taste, int kcal) {
			this.taste = taste;
			this.kcal = kcal;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			hamburger = new Hamburger[N];
			dp = new int[N][L+1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				taste = Integer.parseInt(st.nextToken());
				kcal = Integer.parseInt(st.nextToken());
				hamburger[i] = new Hamburger(taste, kcal);
			}

			for (int i = 0; i < L; i++) {
				if (hamburger[0].kcal <= i) {
					dp[0][i] = hamburger[0].taste;
				}
			}

			for (int i = 1; i < N; i++) {
				for (int j = 0; j <= L; j++) {
					if (j - hamburger[i].kcal >= 0) {
						dp[i][j] = Math.max(dp[i - 1][j - hamburger[i].kcal] + hamburger[i].taste, dp[i - 1][j]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}

			sb.append("#").append(t).append(" ").append(dp[N - 1][L - 1]).append("\n");
		}
		System.out.println(sb);
	}
}
