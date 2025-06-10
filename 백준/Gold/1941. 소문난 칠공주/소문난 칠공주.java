import java.io.*;
import java.util.*;

public class Main {

	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };
	static ArrayList<int[]> positions;
	static char[][] board;
	static int totalCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 소문난 칠공주 조건
		// 7명
		// 가로나 세로 인접
		// 반드시 이다솜파가 아니어도 됨
		// but 이다솜파가 4명 이상이어야함
		board = new char[5][5];
		positions = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			char[] line = br.readLine().toCharArray();

			for (int j = 0; j < 5; j++) {
				positions.add(new int[] { i, j });
				board[i][j] = line[j];
			}
		}

		dfs(0, 0, new ArrayList<>());
		System.out.println(totalCount);

		System.out.println();

	}

	private static void dfs(int depth, int start, ArrayList<int[]> girls) {
		if (girls.size() > 7) {
			return;
		}

		if (girls.size() == 7) {
			if (connect(girls)) {
				totalCount++;
				return;
			}
		}

		for (int i = start; i < 25; i++) {
			girls.add(positions.get(i));
			dfs(depth + 1, i + 1, girls);
			girls.remove(girls.size() - 1);
		}
	}

	// bfs
	private static boolean connect(ArrayList<int[]> girls) {
		int sCount = 0;
		int yCount = 0;
		boolean isConnected = false;
		boolean[][] visited = new boolean[5][5];

		int[] start = girls.get(0);
		visited[start[0]][start[1]] = true;
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { start[0], start[1] });

		if (board[start[0]][start[1]] == 'S') {
			sCount++;
		} else {
			yCount++;
		}

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				for (int[] next : girls) {
					if (ni == next[0] && nj == next[1] && !visited[ni][nj]) {
						dq.add(new int[] { ni, nj });
						visited[ni][nj] = true;

						if (board[ni][nj] == 'S') {
							sCount++;
						} else {
							yCount++;
						}
					}
				}
			}
		}

		if (sCount >= 4 && sCount + yCount == 7) {
			isConnected = true;
		}

		return isConnected;
	}
}