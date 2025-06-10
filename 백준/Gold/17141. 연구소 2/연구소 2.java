import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] board;
	static ArrayList<int[]> possible;
	static int minTime = 10000;

	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 모든 칸에 바이러스를 퍼뜨리는 최소 시간
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		possible = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int number = Integer.parseInt(st.nextToken());

				if (number == 2) {
					possible.add(new int[] { i, j });
				}

				board[i][j] = number;
			}
		}

		dfs(0, 0, new ArrayList<>());

		if (minTime == 10000) {
			System.out.println(-1);
		} else {
			System.out.println(minTime);
		}

	}

	public static void dfs(int depth, int start, ArrayList<int[]> current) {
		if (current.size() > M) {
			return;
		}

		if (current.size() == M) {
			int time = bfs(current) - 1;
			minTime = Math.min(time, minTime);
		}

		for (int i = start; i < possible.size(); i++) {
			current.add(possible.get(i));
			dfs(depth + 1, i + 1, current);
			current.remove(current.size() - 1);
		}
	}

	private static int bfs(ArrayList<int[]> viruses) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		int[][] virusBoard = new int[N][N];

		for (int[] virus : viruses) {
			dq.add(new int[] { virus[0], virus[1] });
			virusBoard[virus[0]][virus[1]] = 1;
		}

		int time = 1;
		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && board[ni][nj] != 1 && virusBoard[ni][nj] == 0) {
					virusBoard[ni][nj] = virusBoard[ci][cj] + 1;
					time = Math.max(virusBoard[ni][nj], time);
					dq.add(new int[] { ni, nj });
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 1 && virusBoard[i][j] == 0) {
					return Integer.MAX_VALUE;
				}
			}
		}
		return time;
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < N;
	}
}