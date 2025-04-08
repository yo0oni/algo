import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int L, N, Q;
	static int r, c, h, w, k;
	static int number, direction;
	static int[][] board;
	static int[][] wBoard;
	static Warrior[] warriors;
	static int totalDamage = 0;

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	static class Warrior {
		int r;
		int c;
		int h;
		int w;
		int hp;
		int damage;

		public Warrior(int r, int c, int h, int w, int hp) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.hp = hp;
			this.damage = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		board = new int[L][L];
		wBoard = new int[L][L];
		warriors = new Warrior[N + 1];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < L; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			for (int i = r - 1; i < r - 1 + h; i++) {
				for (int j = c - 1; j < c - 1 + w; j++) {
					wBoard[i][j] = n;
				}
			}
			warriors[n] = new Warrior(r - 1, c - 1, h, w, k);
		}

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			number = Integer.parseInt(st.nextToken());
			direction = Integer.parseInt(st.nextToken());

			if (warriors[number].hp <= 0)
				continue;

			ArrayList<Integer> visited = move(number, direction);
			checkHamjeong(number, visited);
		}

		for (int i = 1; i <= N; i++) {
			if (warriors[i].hp > 0) {
				totalDamage += warriors[i].damage;
			}
		}
		System.out.println(totalDamage);
	}

	private static void checkHamjeong(int number, ArrayList<Integer> visited) {
		for (int n = 1; n < N + 1; n++) {
			if (n == number) {
				continue;
			}

			if (!visited.contains(n)) {
				continue;
			}

			Warrior warrior = warriors[n];
			warriorLoop: for (int i = warrior.r; i < warrior.r + warrior.h; i++) {
				for (int j = warrior.c; j < warrior.c + warrior.w; j++) {
					if (board[i][j] == 1) {
						warrior.damage++;
						warrior.hp--;

						if (warrior.hp <= 0) {
							remove(warrior);
							break warriorLoop;
						}
					}
				}
			}
		}
	}

	private static void remove(Warrior warrior) {
		for (int i = warrior.r; i < warrior.r + warrior.h; i++) {
			for (int j = warrior.c; j < warrior.c + warrior.w; j++) {
				wBoard[i][j] = 0;
			}
		}
	}

	private static ArrayList<Integer> move(int number, int direction) {
		int[][] backupBoard = new int[L][L];

		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				backupBoard[i][j] = wBoard[i][j];
			}
		}

		int[][] backupWarrior = new int[N + 1][2];
		for (int i = 1; i < N + 1; i++) {
			Warrior warrior = warriors[i];
			backupWarrior[i][0] = warrior.r;
			backupWarrior[i][1] = warrior.c;
		}

		ArrayList<Integer> visited = new ArrayList<>();
		boolean canMove = moveChain(number, direction, visited);

		if (!canMove) {
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < L; j++) {
					wBoard[i][j] = backupBoard[i][j];
				}
			}
			for (int i = 1; i < N + 1; i++) {
				Warrior warrior = warriors[i];
				warrior.r = backupWarrior[i][0];
				warrior.c = backupWarrior[i][1];
			}

			visited.clear();
		}

		return visited;
	}

	private static boolean moveChain(int number, int direction, ArrayList<Integer> visited) {
		if (visited.contains(number)) {
			return true;
		}
		visited.add(number);

		Warrior warrior = warriors[number];
		int ni = warrior.r + di[direction];
		int nj = warrior.c + dj[direction];

		for (int i = ni; i < ni + warrior.h; i++) {
			for (int j = nj; j < nj + warrior.w; j++) {
				if (!validate(i, j) || board[i][j] == 2) {
					return false;
				}

				if (wBoard[i][j] != 0 && wBoard[i][j] != number) {
					boolean canMove = moveChain(wBoard[i][j], direction, visited);

					if (!canMove) {
						return false;
					}
				}

			}
		}

		for (int i = warrior.r; i < warrior.r + warrior.h; i++) {
			for (int j = warrior.c; j < warrior.c + warrior.w; j++) {
				wBoard[i][j] = 0;
			}
		}

		for (int i = ni; i < ni + warrior.h; i++) {
			for (int j = nj; j < nj + warrior.w; j++) {
				wBoard[i][j] = number;
			}
		}

		warrior.r = ni;
		warrior.c = nj;
		return true;
	}

	private static boolean validate(int i, int j) {
		return 0 <= i && i < L && 0 <= j && j < L;
	}
}
