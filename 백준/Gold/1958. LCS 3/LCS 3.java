import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		String c = br.readLine();
		int[][][] dp = new int[a.length() + 1][b.length() + 1][c.length() + 1];

		for (int i = 1; i < a.length() + 1; i++) {
			for (int j = 1; j < b.length() + 1; j++) {
				for (int k = 1; k < c.length() + 1; k++) {
					if (a.charAt(i - 1) == b.charAt(j - 1) && b.charAt(j - 1) == c.charAt(k - 1)) {
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
					} else {
						dp[i][j][k] = Math.max(dp[i][j - 1][k], Math.max(dp[i][j][k - 1], dp[i - 1][j][k]));
					}
				}
			}
		}
		System.out.println(dp[a.length()][b.length()][c.length()]);
	}

}
