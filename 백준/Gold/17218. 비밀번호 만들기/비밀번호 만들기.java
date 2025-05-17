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
		int[][] dp = new int[a.length() + 1][b.length() + 1];

		for (int i = 1; i < a.length() + 1; i++) {
			for (int j = 1; j < b.length() + 1; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		int i = a.length();
		int j = b.length();

		while (i > 0 && j > 0) {
			if (a.charAt(i - 1) == b.charAt(j - 1)) {
				sb.append(a.charAt(i - 1));
				i--;
				j--;
			} else {
				if (dp[i - 1][j] > dp[i][j - 1]) {
					i--;
				} else {
					j--;
				}
			}
		}
		System.out.println(sb.reverse().toString());
	}

}
