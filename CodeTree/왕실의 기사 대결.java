import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int L, N, Q;
	static Warrior[] warriors;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] board;
	static int[][] warriorBoard;
	static int totalDamage = 0;

	static class Warrior {
		int x;
		int y;
		int h;
		int w;
		int hp;
		int damage;

		public Warrior(int x, int y, int h, int w, int hp) {
			this.x = x;
			this.y = y;
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
		warriors = new Warrior[N + 1];
		warriorBoard = new int[L][L];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < L; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			Warrior warrior = new Warrior(r, c, h, w, k);
			for (int x = r; x < r + h; x++) {
				for (int y = c; y < c + w; y++) {
					warriorBoard[x][y] = i;
				}
			}
			warriors[i] = warrior;
		}

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			if (warriors[number].hp <= 0)
				continue;

			ArrayList<Integer> visited = move(number, direction);
			damage(number, visited);
		}
		for (int n = 1; n < N + 1; n++) {
			if (warriors[n].hp > 0) {
				totalDamage += warriors[n].damage;
			}
		}
		System.out.println(totalDamage);
	}

	private static ArrayList<Integer> move(int number, int direction) {
		int[][] tempWarriorBoard = new int[L][L];
		int[][] tempRC = new int[N + 1][2];

		for (int x = 0; x < L; x++) {
			for (int y = 0; y < L; y++) {
				tempWarriorBoard[x][y] = warriorBoard[x][y];
			}
		}

		for (int n = 1; n < N + 1; n++) {
			Warrior warrior = warriors[n];
			tempRC[n][0] = warrior.x;
			tempRC[n][1] = warrior.y;
		}

		ArrayList<Integer> visited = new ArrayList<>();
		boolean canMove = moveChain(number, direction, visited);
		if (!canMove) {
			for (int x = 0; x < L; x++) {
				for (int y = 0; y < L; y++) {
					warriorBoard[x][y] = tempWarriorBoard[x][y];
				}
			}

			for (int n = 1; n < N + 1; n++) {
				Warrior warrior = warriors[n];
				warrior.x = tempRC[n][0];
				warrior.y = tempRC[n][1];
			}
			visited.clear();
		}
		return visited;
	}

	private static boolean moveChain(int number, int dir, ArrayList<Integer> visited) {
		if (visited.contains(number)) {
			return true;
		}

		Warrior warrior = warriors[number];
		for (int x = warrior.x; x < warrior.x + warrior.h; x++) {
			for (int y = warrior.y; y < warrior.y + warrior.w; y++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if (!validate(nx, ny))
					return false;

				if (board[nx][ny] == 2) {
					return false;
				}

				if (warriorBoard[nx][ny] != 0 && warriorBoard[nx][ny] != number) {
					boolean canMoveChain = moveChain(warriorBoard[nx][ny], dir, visited);

					if (!canMoveChain) {
						return false;
					}
				}
			}
		}

		for (int x = warrior.x; x < warrior.x + warrior.h; x++) {
			for (int y = warrior.y; y < warrior.y + warrior.w; y++) {
				warriorBoard[x][y] = 0;
			}
		}

		warrior.x += dx[dir];
		warrior.y += dy[dir];
		for (int x = warrior.x; x < warrior.x + warrior.h; x++) {
			for (int y = warrior.y; y < warrior.y + warrior.w; y++) {
				warriorBoard[x][y] = number;
			}
		}
		visited.add(number);
		return true;
	}

	private static boolean validate(int nx, int ny) {
		return 0 <= nx && nx < L && 0 <= ny && ny < L;
	}

	private static void damage(int number, ArrayList<Integer> visited) {
		for (int n = 1; n < N + 1; n++) {
			if (n == number || !visited.contains(n)) {
				continue;
			}

			Warrior warrior = warriors[n];

			damageLoop: for (int x = warrior.x; x < warrior.x + warrior.h; x++) {
				for (int y = warrior.y; y < warrior.y + warrior.w; y++) {
					if (board[x][y] == 1) {
						warrior.hp--;
						warrior.damage++;

						if (warrior.hp <= 0) {
							remove(warriors[n]);
							break damageLoop;
						}
					}
				}
			}
		}
	}

	private static void remove(Main.Warrior warrior) {
		for (int x = warrior.x; x < warrior.x + warrior.h; x++) {
			for (int y = warrior.y; y < warrior.y + warrior.w; y++) {
				warriorBoard[x][y] = 0;
			}
		}
	}
}
