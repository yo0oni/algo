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
		int maxLength = 0;
		int[][] dp = new int[a.length() + 1][b.length() + 1];

		for (int i = 1; i < a.length() + 1; i++) {
			for (int j = 1; j < b.length() + 1; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					maxLength = Math.max(maxLength, dp[i][j]);
				} else {
					dp[i][j] = 0;
				}
			}
		}

		System.out.println(maxLength);
	}

}
