import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int mi, mj, ei, ej;
	static ArrayList<int[]> warriors;
	static int[][] board;

	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int[] mdi = { -1, 1, 0, 0 };
	static int[] mdj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		warriors = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		mi = Integer.parseInt(st.nextToken());
		mj = Integer.parseInt(st.nextToken());
		ei = Integer.parseInt(st.nextToken());
		ej = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int wi = Integer.parseInt(st.nextToken());
			int wj = Integer.parseInt(st.nextToken());
			warriors.add(new int[] { wi, wj });
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<int[]> route = findRoute(mi, mj);
		if (route == null) {
			System.out.println(-1);
			return;
		}
		for (int[] current : route) {
			int ci = current[0];
			int cj = current[1];

			for (int index = warriors.size() - 1; index >= 0; index--) {
				int[] w = warriors.get(index);

				if (w[0] == ci && w[1] == cj) {
					warriors.remove(index);
				}
			}

			int[][] wBoard = new int[N][N];
			for (int[] w : warriors) {
				wBoard[w[0]][w[1]]++;
			}

			int maxStoneCount = -1;
			int[][] stoneBoard = new int[N][N];
			for (int dir : new int[] { 0, 4, 6, 2 }) {
				StoneResult sr = makeStone(wBoard, ci, cj, dir);

				if (sr.stoneCount > maxStoneCount) {
					maxStoneCount = sr.stoneCount;
					stoneBoard = sr.board;
				}
			}
			int[] res = moveMen(stoneBoard, ci, cj);
			System.out.println(res[0] + " " + maxStoneCount + " " + res[1]);
		}
		System.out.println(0);

	}

	private static int[] moveMen(int[][] stoneBoard, int mi, int mj) {
		int move = 0, attk = 0;
		int[][][] dirsArr = { { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } },
				{ { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } } };
		for (int[][] dirs : dirsArr) {
			for (int idx = warriors.size() - 1; idx >= 0; idx--) {
				int[] pos = warriors.get(idx);
				int ci = pos[0], cj = pos[1];

				if (stoneBoard[ci][cj] == 1)
					continue;
				int dist = Math.abs(mi - ci) + Math.abs(mj - cj);
				for (int[] d : dirs) {
					int ni = ci + d[0], nj = cj + d[1];
					if (validate(ni, nj) && stoneBoard[ni][nj] != 1 && dist > Math.abs(mi - ni) + Math.abs(mj - nj)) {
						if (ni == mi && nj == mj) {
							attk++;
							warriors.remove(idx);
						} else {
							warriors.set(idx, new int[] { ni, nj });
						}
						move++;
						break;
					}
				}
			}
		}
		return new int[] { move, attk };
	}

	private static StoneResult makeStone(int[][] wBoard, int si, int sj, int dir) {
		int ci = si + di[dir];
		int cj = sj + dj[dir];
		int stoneCount = 0;
		int[][] visited = new int[N][N];

		while (validate(ci, cj)) {
			visited[ci][cj] = 1;
			if (wBoard[ci][cj] > 0) {
				stoneCount += wBoard[ci][cj];
				ci += di[dir];
				cj += dj[dir];
				makeLine(ci, cj, dir, visited);
				break;
			}
			ci += di[dir];
			cj += dj[dir];
		}

		int lDir = (dir - 1 + 8) % 8;
		int rDir = (dir + 1) % 8;

		for (int nDir : new int[] { lDir, rDir }) {

			int ni = si + di[nDir];
			int nj = sj + dj[nDir];

			while (validate(ni, nj)) {
				if (visited[ni][nj] == 0) {
					if (wBoard[ni][nj] > 0) {
						visited[ni][nj] = 1;
						stoneCount += wBoard[ni][nj];
						makeSafe(ni, nj, dir, nDir, visited);
						break;
					}
				}

				int nni = ni;
				int nnj = nj;

				while (validate(nni, nnj)) {
					if (visited[nni][nnj] == 0) {
						visited[nni][nnj] = 1;

						if (wBoard[nni][nnj] > 0) {
							stoneCount += wBoard[nni][nnj];
							makeSafe(nni, nnj, dir, nDir, visited);
							break;
						}
					} else {
						break;
					}
					nni += di[dir];
					nnj += dj[dir];

				}

				ni += di[nDir];
				nj += dj[nDir];
			}
		}

		return new StoneResult(stoneCount, visited);
	}

	private static void makeSafe(int ci, int cj, int dir, int nDir, int[][] visited) {
		int ni = ci + di[dir];
		int nj = cj + dj[dir];
		makeLine(ni, nj, dir, visited);

		ni = ci + di[nDir];
		nj = cj + dj[nDir];

		while (validate(ni, nj)) {
			makeLine(ni, nj, dir, visited);
			ni += di[nDir];
			nj += dj[nDir];
		}
	}

	private static void makeLine(int ci, int cj, int dir, int[][] visited) {
		while (validate(ci, cj)) {
			visited[ci][cj] = 2;
			ci += di[dir];
			cj += dj[dir];
		}
	}

	private static ArrayList<int[]> findRoute(int si, int sj) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		int[][][] visited = new int[N][N][];
		dq.add(new int[] { si, sj });
		visited[si][sj] = new int[] { si, sj };

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			if (ci == ei && cj == ej) {
				ArrayList<int[]> route = new ArrayList<>();
				int[] prev = visited[ci][cj];
				ci = prev[0];
				cj = prev[1];

				while (ci != si || cj != sj) {
					route.add(new int[] { ci, cj });
					prev = visited[ci][cj];
					ci = prev[0];
					cj = prev[1];
				}
				Collections.reverse(route);
				return route;
			}

			for (int d = 0; d < 4; d++) {
				int ni = ci + mdi[d];
				int nj = cj + mdj[d];

				if (validate(ni, nj) && visited[ni][nj] == null && board[ni][nj] == 0) {
					dq.add(new int[] { ni, nj });
					visited[ni][nj] = new int[] { ci, cj };
				}
			}
		}

		return null;
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < N;
	}

	static class StoneResult {
		int stoneCount;
		int[][] board;

		StoneResult(int stoneCount, int[][] board) {
			this.stoneCount = stoneCount;
			this.board = board;
		}
	}

}

