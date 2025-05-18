import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent;
	static HashSet<Integer> knows;
	static ArrayList<Integer>[] parties;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		knows = new HashSet<>();
		parent = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		for (int i = 0; i < t; i++) {
			knows.add(Integer.parseInt(st.nextToken()));
		}

		parties = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			parties[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int first = -1;

			for (int j = 0; j < n; j++) {
				int person = Integer.parseInt(st.nextToken());
				parties[i].add(person);

				if (first == -1)
					first = person;
				else
					union(first, person);
			}
		}

		int answer = 0;

		for (int i = 0; i < M; i++) {
			boolean canLie = true;

			cantLoop: for (int person : parties[i]) {
				for (int know : knows) {
					if (find(person) == find(know)) {
						canLie = false;
						break cantLoop;
					}
				}
			}

			if (canLie)
				answer++;
		}

		System.out.println(answer);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA < rootB) {
			parent[rootB] = rootA;
		} else {
			parent[rootA] = rootB;
		}
	}

	private static int find(int a) {
		if (a == parent[a]) {
			return parent[a];
		}
		return parent[a] = find(parent[a]);
	}
}
