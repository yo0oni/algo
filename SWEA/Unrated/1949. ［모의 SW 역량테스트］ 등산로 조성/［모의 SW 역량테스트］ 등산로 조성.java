import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int N, K;
	static int[][] board;
	static int maxHeight;
	static int maxLength;
	static boolean[][] visited;
	static ArrayList<int[]> maxHeights;

	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			maxHeight = 0;
			maxLength = 0;
			board = new int[N][N];
			maxHeights = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					int height = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(height, maxHeight);
					board[i][j] = height;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == maxHeight) {
						maxHeights.add(new int[] { i, j });
					}
				}
			}

			for (int[] current : maxHeights) {
				visited = new boolean[N][N];
				visited[current[0]][current[1]] = true;
				dfs(0, current[0], current[1], board[current[0]][current[1]], false);
			}

			sb.append("#").append(t).append(" ").append(maxLength + 1).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int length, int ci, int cj, int currentHeight, boolean cutUsed) {
		boolean moved = false;

		for (int d = 0; d < 4; d++) {
			int ni = ci + di[d];
			int nj = cj + dj[d];

			if (!validate(ni, nj) || visited[ni][nj])
				continue;

			if (board[ni][nj] < currentHeight) {
				visited[ni][nj] = true;
				dfs(length + 1, ni, nj, board[ni][nj], cutUsed);
				visited[ni][nj] = false;
				moved = true;
			} else {
				if (!cutUsed) {
					int original = board[ni][nj];

					for (int cut = 1; cut <= K; cut++) {
						if (original - cut < currentHeight) {
							visited[ni][nj] = true;
							dfs(length + 1, ni, nj, original - cut, true);
							visited[ni][nj] = false;
							moved = true;
						}
					}
				}
			}
		}
		if (!moved) {
			maxLength = Math.max(maxLength, length);
		}
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < N;
	}
}
