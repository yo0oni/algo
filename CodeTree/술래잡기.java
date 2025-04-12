import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H, K;
	static boolean[][] treeBoard;
	static ArrayList<Integer>[][] playBoard;
	static Player[] players;

	static int[] pdx = { -1, 0, 1, 0 };
	static int[] pdy = { 0, 1, 0, -1 };
	static int[][] inToOut;
	static int[][] outToIn;
	static int totalScore = 0;
	static int sx, sy;
	static boolean out;

	static int[] sdx = { -1, 0, 1, 0 };
	static int[] sdy = { 0, 1, 0, -1 };

	static class Player {
		int x;
		int y;
		int dir;
		boolean alive;

		public Player(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.alive = true;
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		treeBoard = new boolean[N][N];
		playBoard = new ArrayList[N][N];
		players = new Player[M];
		inToOut = new int[N][N];
		outToIn = new int[N][N];
		sx = N / 2;
		sy = N / 2;
		out = true;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				playBoard[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			Player player = new Player(x, y, d);
			players[i] = player;
			playBoard[x][y].add(i);
		}

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			treeBoard[x][y] = true;
		}

		int currX = N / 2, currY = N / 2;
		int moveDir = 0, moveNum = 1;

		while (currX > 0 || currY > 0) {
			for (int i = 0; i < moveNum; i++) {
				inToOut[currX][currY] = moveDir;
				currX += sdx[moveDir];
				currY += sdy[moveDir];
				outToIn[currX][currY] = (moveDir + 2) % 4;

				if (currX == 0 && currY == 0)
					break;
			}

			moveDir = (moveDir + 1) % 4;

			if (moveDir == 0 || moveDir == 2)
				moveNum++;
		}

		for (int k = 1; k < K + 1; k++) {

			movePlayer();
			totalScore += k * moveSulrae();
		}

		System.out.println(totalScore);
	}

	private static int moveSulrae() {
		int dir; 
		int catchCount = 0;

		if (out) {
			dir = inToOut[sx][sy];
			sx = sx + sdx[dir];
			sy = sy + sdy[dir];

			if (sx == 0 && sy == 0) {
				out = false;
			}

			dir = inToOut[sx][sy];
		} else {
			dir = outToIn[sx][sy];
			sx = sx + sdx[dir];
			sy = sy + sdy[dir];

			if (sx == N / 2 && sy == N / 2) {
				out = true;
			}

			dir = outToIn[sx][sy];
		}

		for (int i = 0; i < 3; i++) {
			int cx = sx + sdx[dir] * i;
			int cy = sy + sdy[dir] * i;

			if (!validate(cx, cy))
				continue;

			if (playBoard[cx][cy].size() != 0) {
				if (!treeBoard[cx][cy]) {
					catchCount += playBoard[cx][cy].size();

					for (int m : playBoard[cx][cy]) {
						players[m].alive = false;
					}

					playBoard[cx][cy].clear();
				}
			}
		}
		return catchCount;
	}

	private static void movePlayer() {
		for (int m = 0; m < M; m++) {
			Player player = players[m];

			if (!player.alive)
				continue;

			if (Math.abs(player.x - sx) + Math.abs(player.y - sy) <= 3) {
				if (!validate(player.x + pdx[player.dir], player.y + pdy[player.dir])) {
					player.dir = (player.dir + 2) % 4;
				}

				if (player.x + pdx[player.dir] != sx || player.y + pdy[player.dir] != sy) {
					int index = findIndex(playBoard[player.x][player.y], m);
					playBoard[player.x][player.y].remove(index);
					player.x = player.x + pdx[player.dir];
					player.y = player.y + pdy[player.dir];
					playBoard[player.x][player.y].add(m);
				}
			}

		}
	}

	private static int findIndex(ArrayList<Integer> player, int num) {
		for (int i = 0; i < player.size(); i++) {
			if (player.get(i) == num) {
				return i;
			}
		}
		return -1;
	}

	private static boolean validate(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
