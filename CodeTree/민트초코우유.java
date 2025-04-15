import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import javax.xml.transform.Templates;

public class Main {
	static int N, T;
	static Segyun[][] board;
	static int[][] teamBoard;
	static int teamNumber;

	static class Segyun implements Comparator<Segyun> {
		boolean[] color;
		int colorNum;
		int energy;
		int i;
		int j;

		public Segyun(int colorNum, int i, int j) {
			color = new boolean[3];
			this.colorNum = colorNum;
			color[colorNum] = true;
			this.i = i;
			this.j = j;
		}

		@Override
		public int compare(Segyun o1, Segyun o2) {
			if (o1.colorNum / 3 == o2.colorNum / 3) {
				if (o1.energy == o2.energy) {
					if (o1.i == o2.i) {
						return o1.j - o2.j;
					}
					return o1.i - o2.i;
				}
				return o2.energy - o1.energy;
			}
			return o1.colorNum / 3 - o2.colorNum / 3;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		board = new Segyun[N][N];
		teamBoard = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] strs = br.readLine().split("");

			for (int j = 0; j < N; j++) {
				if (strs[j].equals("T")) {
					board[i][j] = new Segyun(0, i, j);
				} else if (strs[j].equals("C")) {
					board[i][j] = new Segyun(1, i, j);
				} else if (strs[j].equals("M")) {
					board[i][j] = new Segyun(2, i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				board[i][j].energy = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<Segyun> tomorrow = new ArrayList<>();
		for (int t = 0; t < T; t++) {
			getEnergy();
			setTeamNumber();
			ArrayList<Segyun> today = selectReader();
			tomorrow = laserAttack(today, tomorrow);
			printAnswer();
		}
	}

	private static ArrayList<Segyun> laserAttack(ArrayList<Segyun> readers, ArrayList<Segyun> visited) {
		int[] di = { -1, 1, 0, 0, };
		int[] dj = { 0, 0, -1, 1 };

		ArrayList<Segyun> newVisited = new ArrayList<>();

		for (Segyun reader : readers) {
			if (visited.contains(reader))
				continue;

			int power = reader.energy - 1;
			int dir = reader.energy % 4;
			int ni = reader.i + di[dir];
			int nj = reader.j + dj[dir];
			reader.energy = 1;

			while (validate(ni, nj) && power > 0) {

				if (isSame(board[ni][nj].color, board[reader.i][reader.j].color) || visited.contains(board[ni][nj])) {
					ni += di[dir];
					nj += dj[dir];
					continue;
				}
				
				if (power > board[ni][nj].energy) {
					power -= (board[ni][nj].energy + 1);
					board[ni][nj].energy++;
					newVisited.add(board[ni][nj]);
					ni += di[dir];
					nj += dj[dir];
				} else {
					board[ni][nj].energy += power;
					addFood(board[ni][nj], reader.color);
					newVisited.add(board[ni][nj]);
					break;
				}
			}
		}
		return newVisited;
	}

	private static boolean isSame(boolean[] color, boolean[] color2) {
		for (int i = 0; i < 3; i++) {
			if (color[i] != color2[i]) {
				return false;
			}
		}
		return true;
	}

	private static void addFood(Segyun segyun, boolean[] reader) {
		boolean[] me = segyun.color;
		for (int i = 0; i < 3; i++) {
			if (!me[i] && reader[i]) {
				me[i] = true;
			}
		}

		if (me[0] && !me[1] && !me[2]) // 민트
			segyun.colorNum = 0;
		if (!me[0] && me[1] && !me[2]) // 초코
			segyun.colorNum = 1;
		if (!me[0] && !me[1] && me[2]) // 우유
			segyun.colorNum = 2;
		if (!me[0] && me[1] && me[2]) // 초코우유
			segyun.colorNum = 3;
		if (me[0] && !me[1] && me[2]) // 민트우유
			segyun.colorNum = 4;
		if (me[0] && me[1] && !me[2]) // 민트초코
			segyun.colorNum = 5;
		if (me[0] && me[1] && me[2]) // 민트초코우유
			segyun.colorNum = 6;
	}

	private static void printAnswer() {
		int[] answer = new int[7];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer[board[i][j].colorNum] += board[i][j].energy;
			}
		}
		for (int i = 6; i >= 0; i--) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

	private static ArrayList<Segyun> selectReader() {
		ArrayList<Segyun> readers = new ArrayList<>();
		for (int n = 1; n < teamNumber; n++) {
			Segyun reader = null;
			int maxEnergy = 0;
			int minI = Integer.MAX_VALUE;
			int minJ = Integer.MAX_VALUE;
			int teamCount = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (teamBoard[i][j] == n) {
						Segyun segyun = board[i][j];
						teamCount++;
						if (segyun.energy > maxEnergy || (segyun.energy == maxEnergy && i < minI)
								|| (segyun.energy == maxEnergy && i == minI && j < minJ)) {
							reader = segyun;
							minI = i;
							minJ = j;
							maxEnergy = segyun.energy;
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (teamBoard[i][j] == n) {
						if (i != minI || j != minJ) {
							board[i][j].energy--;
						}
					}
				}
			}
			reader.energy += (teamCount - 1);
			readers.add(reader);
		}
		readers.sort((o1, o2) -> o1.compare(o1, o2));
		return readers;
	}

	private static void setTeamNumber() {
		boolean[][] visited = new boolean[N][N];
		teamNumber = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, teamNumber, visited);
					teamNumber++;
				}
			}
		}
	}

	private static void bfs(int i, int j, int number, boolean[][] visited) {
		visited[i][j] = true;
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { i, j });
		teamBoard[i][j] = number;

		int[] di = { 0, 1, 0, -1 };
		int[] dj = { 1, 0, -1, 0 };

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int ci = current[0];
			int cj = current[1];

			for (int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];

				if (validate(ni, nj) && !visited[ni][nj] && board[ni][nj].colorNum == board[ci][cj].colorNum) {
					dq.add(new int[] { ni, nj });
					teamBoard[ni][nj] = number;
					visited[ni][nj] = true;
				}
			}
		}
	}

	private static boolean validate(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < N;
	}

	private static void getEnergy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j].energy++;
			}
		}
	}

}
