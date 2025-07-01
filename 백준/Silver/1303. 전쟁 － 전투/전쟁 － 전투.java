import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] visited;
	static int[][] board;
	static int N, M;
	static int[] answer;

	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[M][N];
		answer = new int[2];

		for (int i = 0; i < M; i++) {
			String input = br.readLine();

			for (int j = 0; j < N; j++) {
				char wb = input.charAt(j);

				if (wb == 'W') {
					board[i][j] = 0;
				} else {
					board[i][j] = 1;
				}
			}
		}

		visited = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
				}
			}
		}

		System.out.println(answer[0] + " " + answer[1]);
	}

	private static void bfs(int si, int sj) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { si, sj });
		visited[si][sj] = true;
		int count = 1;

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && !visited[ni][nj] && board[ni][nj] == board[si][sj]) {
					count++;
					visited[ni][nj] = true;
					dq.add(new int[] { ni, nj });
				}
			}
		}
		answer[board[si][sj]] += Math.pow(count, 2);
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < M && 0 <= nj && nj < N;
	}
}