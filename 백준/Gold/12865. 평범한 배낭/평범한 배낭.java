import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[][] products = new int[N][2];
		int[][] dp = new int[N + 1][K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());

			products[i][0] = W;
			products[i][1] = V;
		}

		for (int i = 1; i <= N; i++) {
			int w = products[i - 1][0];
			int v = products[i - 1][1];

			for (int j = 0; j <= K; j++) {
				if (j < w) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v); // 넣거나 말거나
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}
