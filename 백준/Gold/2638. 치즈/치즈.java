import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] board;

	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 0, 0부터 시작해서 빈칸인 곳들은 뚫려있는 빈공간
		// 위 탐색에서 도달하지 못한 빈칸은 갇힌 빈공간

		setStatus(); // 2은 뚫린 빈공간, 1은 치즈, 0은 도달 불가능한 빈공간
		int time = 0;
		while (true) {
			ArrayDeque<int[]> cheeses = getCheesePos();

			if (cheeses.size() == 0)
				break;

			meltCheese(cheeses);
			setAir();
			time++;
		}
		System.out.println(time);
	}

	private static void setAir() {
		boolean[][] visited = new boolean[N][M];
		ArrayDeque<int[]> dq = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 2 && !visited[i][j]) {
					visited[i][j] = true;
					dq.offer(new int[] { i, j });
				}
			}
		}

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int ci = cur[0], cj = cur[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && !visited[ni][nj] && board[ni][nj] == 0) {
					board[ni][nj] = 2;
					visited[ni][nj] = true;
					dq.offer(new int[] { ni, nj });
				}
			}
		}
	}



	private static void meltCheese(ArrayDeque<int[]> cheeses) {
		ArrayDeque<int[]> meltCheeses = new ArrayDeque<>();

		for (int[] cheese : cheeses) {
			int ci = cheese[0];
			int cj = cheese[1];
			int airCount = 0;

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && board[ni][nj] == 2) {
					airCount++;
				}
			}

			if (airCount >= 2) {
				meltCheeses.offer(new int[] { ci, cj });
			}
		}

		while (!meltCheeses.isEmpty()) {
			int[] melt = meltCheeses.poll();
			board[melt[0]][melt[1]] = 2;
		}
	}

	private static ArrayDeque<int[]> getCheesePos() {
		ArrayDeque<int[]> cheeses = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					cheeses.offer(new int[] { i, j });
				}
			}
		}

		return cheeses;
	}

	private static void setStatus() {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] { 0, 0 });
		board[0][0] = 2;

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && board[ni][nj] == 0) {
					board[ni][nj] = 2;
					dq.offer(new int[] { ni, nj });
				}
			}
		}
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < M;
	}
}
