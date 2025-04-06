import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K;
	static int[][] board;
	static Fairy[] fairies;
	static int answer;
	static boolean[][] isDoor;

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	static class Fairy {
		int i;
		int j;
		int doorDir;

		public Fairy(int j, int doorDir) {
			this.i = 1;
			this.j = j - 1;
			this.doorDir = doorDir;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		fairies = new Fairy[K + 1];
		board = new int[R + 3][C];
		isDoor = new boolean[R + 3][C];

		for (int i = 1; i < K + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			fairies[i] = new Fairy(c, d);
		}

		for (int number = 1; number < K + 1; number++) {
			Fairy fairy = fairies[number];

			while (true) {
				if (canDown(fairy)) {
					fairy.i += 1;
				} else if (canLeft(fairy)) {
					fairy.i += 1;
					fairy.j -= 1;
					fairy.doorDir = (fairy.doorDir - 1 + 4) % 4;
				} else if (canRight(fairy)) {
					fairy.i += 1;
					fairy.j += 1;
					fairy.doorDir = (fairy.doorDir + 1) % 4;
				} else {
					break;
				}
			}

			if (fairy.i <= 3) {
				board = new int[R + 3][C];
				isDoor = new boolean[R + 3][C];
			} else {
				board[fairy.i][fairy.j] = number;
				board[fairy.i + 1][fairy.j] = number;
				board[fairy.i - 1][fairy.j] = number;
				board[fairy.i][fairy.j + 1] = number;
				board[fairy.i][fairy.j - 1] = number;

				int ni = fairy.i + di[fairy.doorDir];
				int nj = fairy.j + dj[fairy.doorDir];
				isDoor[ni][nj] = true;
				
				int row = findMaxRow(fairy.i, fairy.j);
				answer += (row - 2);
			}
		}
		System.out.println(answer);
	}

	private static int findMaxRow(int si, int sj) {
		// 출구 기준 먼저 탐색 후 si + 1 탐색
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[R + 3][C];
		int maxRow = si + 1;

		dq.add(new int[] { si, sj });
		visited[si][sj] = true;

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && !visited[ni][nj]) {
					if (board[ni][nj] == board[ci][cj]
							|| (isDoor[ci][cj] && board[ni][nj] != board[ci][cj] && board[ni][nj] != 0)) {
						dq.add(new int[] { ni, nj });
						visited[ni][nj] = true;
						maxRow = Math.max(ni, maxRow);
					}
				}
			}
		}

		return maxRow;
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < R + 3 && 0 <= nj && nj < C;
	}

	private static boolean canRight(Fairy fairy) {
		int si = fairy.i;
		int sj = fairy.j;

		if (validate(si, sj + 2) && validate(si + 1, sj + 1) && validate(si - 1, sj + 1)) {
			if (board[si][sj + 2] == 0 && board[si + 1][sj + 1] == 0 && board[si - 1][sj + 1] == 0) {
				if (validate(si + 2, sj + 1) && validate(si + 1, sj + 2)) {
					if (board[si + 2][sj + 1] == 0 && board[si + 1][sj + 2] == 0) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static boolean canLeft(Fairy fairy) {
		int si = fairy.i;
		int sj = fairy.j;

		if (validate(si, sj - 2) && validate(si - 1, sj - 1) && validate(si + 1, sj - 1)) {
			if (board[si][sj - 2] == 0 && board[si - 1][sj - 1] == 0 && board[si + 1][sj - 1] == 0) {
				if (validate(si + 1, sj - 2) && validate(si + 2, sj - 1)) {
					if (board[si + 1][sj - 2] == 0 && board[si + 2][sj - 1] == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean canDown(Fairy fairy) {
		int si = fairy.i;
		int sj = fairy.j;

		if (validate(si + 2, sj) && validate(si + 1, sj - 1) && validate(si + 1, sj + 1)) {
			if (board[si + 2][sj] == 0 && board[si + 1][sj - 1] == 0 && board[si + 1][sj + 1] == 0) {
				return true;
			}
		}
		return false;
	}
}
