import java.io.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] board;
	static ArrayList<Chicken> chickens;
	static ArrayList<Home> homes;
	static int minChickenDistance;
	static boolean[] visited;

	static class Home {
		int i;
		int j;

		public Home(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static class Chicken {
		int i;
		int j;

		public Chicken(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		chickens = new ArrayList<>();
		homes = new ArrayList<>();
		minChickenDistance = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int number = Integer.parseInt(st.nextToken());
				if (number == 2) {
					chickens.add(new Chicken(i, j));
				} else if (number == 1) {
					homes.add(new Home(i, j));
				}
				board[i][j] = number;
			}
		}
		visited = new boolean[chickens.size()];
		dfs(0, 0, new ArrayList<>());
		System.out.println(minChickenDistance);
	}

	private static void dfs(int depth, int start, ArrayList<Chicken> selected) {
		if (depth == M) {
			int chickenDistance = calculateDistance(selected);
			minChickenDistance = Math.min(minChickenDistance, chickenDistance);
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected.add(chickens.get(i));
				dfs(depth + 1, i + 1, selected);
				selected.remove(selected.size() - 1);
				visited[i] = false;
			}
		}
	}

	private static int calculateDistance(ArrayList<Chicken> selected) {
		int totalDistance = 0;

		for (Home hm : homes) {
			int minDistance = Integer.MAX_VALUE;
			
			for (Chicken ck : selected) {
				int distance = Math.abs(ck.i - hm.i) + Math.abs(ck.j - hm.j);
				minDistance = Math.min(minDistance, distance);
			}
			
			totalDistance += minDistance;
		}
		return totalDistance;
	}
}
