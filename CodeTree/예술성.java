import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int totalScore;
	static int[][] board;
	static int[][] groupBoard;
	static HashMap<Integer, ArrayList<int[]>> group;

	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		groupBoard = new int[N][N];
		group = new HashMap<>();
		totalScore = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		makeGroup();
		totalScore = calculateScore();

		for (int i = 0; i < 3; i++) {
			group = new HashMap<>();
			groupBoard = new int[N][N];
			turnBoard();
			makeGroup();
			totalScore += calculateScore();
		}

		System.out.println(totalScore);
	}

	private static void turnBoard() {
		int[][] tempBoard = new int[N][N];

		for (int i = 0; i < N; i++) {
			tempBoard[N / 2][i] = board[i][N / 2];
		}

		for (int j = 0; j < N; j++) {
			tempBoard[j][N / 2] = board[N / 2][N - j - 1];
		}

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				tempBoard[j][i] = board[N / 2 - i - 1][j];
			}
		}

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				tempBoard[j][N / 2 + 1 + i] = board[N / 2 - i - 1][N / 2 + 1 + j];
			}
		}

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				tempBoard[N / 2 + 1 + j][i] = board[N / 2 + 1 + N / 2 - i - 1][j];
			}
		}

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				tempBoard[N / 2 + 1 + j][N / 2 + 1 + i] = board[N / 2 + 1 + N / 2 - i - 1][N / 2 + 1 + j];
			}
		}

		board = tempBoard;
	}

	private static int calculateScore() {
		int groupSize = group.size();
		int totalScore = 0;

		for (int i = 1; i < groupSize + 1; i++) {
			for (int j = i + 1; j < groupSize + 1; j++) {

				int score = 0;
				ArrayList<int[]> aGroup = group.get(i);
				ArrayList<int[]> bGroup = group.get(j);
				score += (aGroup.size() + bGroup.size());

				int[] aPos = aGroup.get(0);
				int[] bPos = bGroup.get(0);
				int aNumber = board[aPos[0]][aPos[1]];
				int bNumber = board[bPos[0]][bPos[1]];

				score *= aNumber;
				score *= bNumber;

				int shareLength = findShared(aGroup, j);
				totalScore += (score * shareLength);
			}
		}

		return totalScore;
	}

	private static int findShared(ArrayList<int[]> aGroup, int bNumber) {
		int shared = 0;

		for (int[] current : aGroup) {
			int ci = current[0];
			int cj = current[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && groupBoard[ni][nj] == bNumber) {
					shared++;
				}
			}
		}
		return shared;
	}

	private static void makeGroup() {
		int number = 1;
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					group.put(number, bfs(number, i, j, visited));
					number++;
				}
			}
		}
	}

	private static ArrayList<int[]> bfs(int type, int si, int sj, boolean[][] visited) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		ArrayList<int[]> points = new ArrayList<>();
		dq.add(new int[] { si, sj });
		points.add(new int[] { si, sj });
		visited[si][sj] = true;
		groupBoard[si][sj] = type;

		int number = board[si][sj];
		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && !visited[ni][nj]) {
					if (board[ni][nj] == number) {
						dq.add(new int[] { ni, nj });
						points.add(new int[] { ni, nj });
						visited[ni][nj] = true;
						groupBoard[ni][nj] = type;
					}
				}
			}
		}

		return points;
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < N;
	}
}
