import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int N, M;
	static int[][] board;
	static int maxProfit;
	static ArrayList<int[]> homes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			homes = new ArrayList<>();
			maxProfit = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					int number = Integer.parseInt(st.nextToken());
					if (number == 1) {
						homes.add(new int[]{i, j});
					}
					board[i][j] = number;
				}
			}

			for (int n = 1; n < 2 * N - 1; n++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						maxProfit = Math.max(calculateProfit(n, i, j), maxProfit);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(maxProfit).append("\n");
		}
		System.out.println(sb);
	}

	private static int calculateProfit(int k, int si, int sj) {
		int homeCount = 0;

		for (int[] home : homes) {
			int i = home[0], j = home[1];
			
			if (validate(i, j) && (Math.abs(si - i) + Math.abs(sj - j)) <= k - 1) {
				homeCount++;
			}
		}

		int cost = (k * k) + ((k - 1) * (k - 1));
		int profit = homeCount * M;

		if (profit >= cost) {
			return homeCount;
		} else {
			return 0;
		}
	}

	private static boolean validate(int i, int j) {
		return 0 <= i && i < N && 0 <= j && j < N;
	}
}
