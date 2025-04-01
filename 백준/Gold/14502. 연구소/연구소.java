import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<int[]> nothing;
	static ArrayList<int[]> virus;
	static int[][] board;
	static boolean[] visited;
	static int[][] tempBoard;
	static int wallCount = 0;
	static int maxSafeZone = 0;

	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		nothing = new ArrayList<>();
		virus = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				int number = Integer.parseInt(st.nextToken());

				if (number == 0) {
					nothing.add(new int[] { i, j });
				} else if (number == 2) {
					virus.add(new int[] { i, j });
				} else if (number == 1) {
					wallCount++;
				}
				board[i][j] = number;
			}
		}

		visited = new boolean[nothing.size()];
		combi(0, 0, new ArrayList<>());
		System.out.println(maxSafeZone);
	}

	private static void combi(int depth, int start, ArrayList<Integer> current) {
		if (depth == 3) {
			tempBoard = new int[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tempBoard[i][j] = board[i][j];
				}
			}
			int count = 0;

			makeWall(current);
			for (int[] v : virus) {
				count += bfs(v[0], v[1]);
			}

			maxSafeZone = Math.max(maxSafeZone, N * M - 3 - wallCount - count);
			return;
		}

		for (int i = start; i < nothing.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				current.add(i);
				combi(depth + 1, start + 1, current);
				current.remove(current.size() - 1);
				visited[i] = false;
			}
		}
	}

	private static int bfs(int si, int sj) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		int count = 1;
		dq.add(new int[] { si, sj });

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && tempBoard[ni][nj] == 0) {
					tempBoard[ni][nj] = 2;
					dq.add(new int[] { ni, nj });
					count++;
				}
			}
		}
		return count;
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < M;
	}

	private static void makeWall(ArrayList<Integer> current) {
		for (int index : current) {
			int[] nt = nothing.get(index);
			tempBoard[nt[0]][nt[1]] = 1;
		}
	}
}
