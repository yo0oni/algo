import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static int[][] board;
	static int si, sj, sd, ei, ej, ed;

	static int[] di = { 0, 0, 1, -1 };
	static int[] dj = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		si = Integer.parseInt(st.nextToken()) - 1;
		sj = Integer.parseInt(st.nextToken()) - 1;
		sd = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine());
		ei = Integer.parseInt(st.nextToken()) - 1;
		ej = Integer.parseInt(st.nextToken()) - 1;
		ed = Integer.parseInt(st.nextToken()) - 1;

		// 최단경로 찾기
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		boolean[][][] visited = new boolean[M][N][4];
		dq.add(new int[] { si, sj, sd, 0 });
		visited[si][sj][sd] = true;

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];
			int cd = current[2];
			int time = current[3];

			if (ci == ei && cj == ej && cd == ed) {
				System.out.println(time);
				break;
			}

			for (int s = 1; s <= 3; s++) {
				int ni = ci + di[cd] * s;
				int nj = cj + dj[cd] * s;

				if (!validate(ni, nj) || board[ni][nj] == 1) break;
				if (visited[ni][nj][cd]) continue;

				visited[ni][nj][cd] = true;
				dq.add(new int[] { ni, nj, cd, time + 1 });
			}

			for (int nd = 0; nd < 4; nd++) {
				if (cd == nd || visited[ci][cj][nd]) continue;
				
				if ((cd == 1 && nd == 0) || (cd == 0 && nd == 1) || (cd == 2 && nd == 3)
						|| (cd == 3 && nd == 2)) {
					dq.add(new int[] { ci, cj, nd, time + 2 });
				} else {
					dq.add(new int[] { ci, cj, nd, time + 1 });
				}
				visited[ci][cj][nd] = true;
			}
		}
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < M && 0 <= nj && nj < N;
	}
}