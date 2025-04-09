import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.chrono.MinguoChronology;
import java.time.chrono.MinguoDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board;
	static Human[] humans;
	static ArrayList<int[]> basecamps;

	static int[] di = { -1, 0, 0, 1 };
	static int[] dj = { 0, -1, 1, 0 };

	static class Human {
		int number;
		int i;
		int j;
		int pi;
		int pj;

		public Human(int number, int i, int j, int pi, int pj) {
			this.number = number;
			this.i = i;
			this.j = j;
			this.pi = pi;
			this.pj = pj;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		humans = new Human[M + 1];
		basecamps = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int number = Integer.parseInt(st.nextToken());
				if (number == 1) {
					basecamps.add(new int[] { i, j });
				}
				board[i][j] = number;
			}
		}

		for (int m = 1; m < M + 1; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			humans[m] = new Human(m, -1, -1, i - 1, j - 1);
		}

		int time = 0;
		while (true) {
			if (allArrive()) {
				break;
			}

			time++;

			for (int m = 1; m < M + 1; m++) {
				Human human = humans[m];

				if (validate(human.i, human.j) && board[human.pi][human.pj] != -1) {
					goToConv(human);
				}
			}

			for (int m = 1; m < M + 1; m++) {
				Human human = humans[m];

				if (validate(human.i, human.j) && (human.i == human.pi && human.j == human.pj)) {
					board[human.i][human.j] = -1;
				}
			}

			for (int m = 1; m < M + 1; m++) {
				Human human = humans[m];

				if (!validate(human.i, human.j) && m <= time) {
					goToBaseCamp(human);
				}
			}
		}
		System.out.println(time);
	}

	private static void goToBaseCamp(Human human) {
		int mbi = -1;
		int mbj = -1;
		int pi = human.pi;
		int pj = human.pj;
		int minDistance = Integer.MAX_VALUE;

		for (int[] camp : basecamps) {
			int bi = camp[0];
			int bj = camp[1];

			if (board[bi][bj] == -1)
				continue;

			ArrayDeque<int[]> dq = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];

			dq.add(new int[] { bi, bj, 0 });
			visited[bi][bj] = true;

			while (!dq.isEmpty()) {
				int[] current = dq.poll();
				int ci = current[0];
				int cj = current[1];
				int distance = current[2];

				if (ci == pi && cj == pj) {
					if (distance < minDistance || (distance == minDistance && bi < mbi)
							|| (distance == minDistance && bi == mbi && bj < mbj)) {
						minDistance = distance;
						mbi = bi;
						mbj = bj;
					}
					break;
				}

				for (int d = 0; d < 4; d++) {
					int ni = ci + di[d];
					int nj = cj + dj[d];

					if (validate(ni, nj) && !visited[ni][nj] && board[ni][nj] != -1) {
						dq.add(new int[] { ni, nj, distance + 1 });
						visited[ni][nj] = true;
					}
				}
			}

		}

		human.i = mbi;
		human.j = mbj;
		board[mbi][mbj] = -1;
	}

	private static void goToConv(Human human) {
		int si = human.i;
		int sj = human.j;
		int pi = human.pi;
		int pj = human.pj;
		int[][][] visited = new int[N][N][];
		ArrayDeque<int[]> dq = new ArrayDeque<>();

		dq.add(new int[] { si, sj });

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			if (ci == pi && cj == pj) {
				int[] prev = visited[ci][cj];

				while (visited[ci][cj][0] != si || visited[ci][cj][1] != sj) {
					prev = visited[ci][cj];
					ci = prev[0];
					cj = prev[1];
				}

				human.i = ci;
				human.j = cj;

				return;
			}

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && board[ni][nj] != -1 && visited[ni][nj] == null) {
					dq.add(new int[] { ni, nj });
					visited[ni][nj] = new int[] { ci, cj };
				}
			}
		}

	}

	private static boolean validate(int i, int j) {
		return 0 <= i && i < N && 0 <= j && j < N;
	}

	private static boolean allArrive() {
		boolean arrive = true;
		for (int m = 1; m < M + 1; m++) {
			if (humans[m].i != humans[m].pi || humans[m].j != humans[m].pj) {
				arrive = false;
				break;
			}
		}
		return arrive;
	}
}
