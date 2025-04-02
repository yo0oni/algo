import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int N, M, R, C, L;
	static int answer;
	static int[][] board;

	// 상 우 하 좌
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			board = new int[N][M];
			answer = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs(R, C);

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int si, int sj) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		dq.add(new int[] { si, sj, 1 });
		visited[si][sj] = true;

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];
			int time = current[2];
			int type = board[ci][cj];

			if (time <= L) {
				answer++;
			}

			for (int d = 0; d < 4; d++) {
				int ni = di[d] + ci;
				int nj = dj[d] + cj;

				if (validate(ni, nj) && !visited[ni][nj]) {
					if (board[ni][nj] != 0) {
						if (d == 0 && (type == 1 || type == 2 || type == 4 || type == 7)) {
							int nextType = board[ni][nj];

							if (nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) {
								dq.add(new int[] { ni, nj, time + 1 });
								visited[ni][nj] = true;
							}
						} else if (d == 1 && (type == 1 || type == 3 || type == 4 || type == 5)) {
							int nextType = board[ni][nj];

							if (nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) {
								dq.add(new int[] { ni, nj, time + 1 });
								visited[ni][nj] = true;
							}
						} else if (d == 2 && (type == 1 || type == 2 || type == 5 || type == 6)) {
							int nextType = board[ni][nj];

							if (nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) {
								dq.add(new int[] { ni, nj, time + 1 });
								visited[ni][nj] = true;
							}
						} else if (d == 3 && (type == 1 || type == 3 || type == 6 || type == 7)) {
							int nextType = board[ni][nj];

							if (nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) {
								dq.add(new int[] { ni, nj, time + 1 });
								visited[ni][nj] = true;
							}
						}
					}
				}
			}
		}
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < M;
	}
}
