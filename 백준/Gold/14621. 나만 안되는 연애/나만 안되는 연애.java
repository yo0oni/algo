import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	static int N, M;
	static char[] gender;
	static int[] parent;
	static int u, v, d;
	static int answer;
	static ArrayList<int[]> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		gender = new char[N + 1];
		parent = new int[N + 1];
		graph = new ArrayList<>();

		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			gender[i] = st.nextToken().charAt(0);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			graph.add(new int[] { u, v, d });
		}

		graph.sort((o1, o2) -> o1[2] - o2[2]);
		for (int i = 0; i < graph.size(); i++) {
			int[] current = graph.get(i);
			int u = current[0];
			int v = current[1];
			int d = current[2];

			if (gender[u] == gender[v])
				continue;

			if (find(u) != find(v)) {
				answer += d;
				union(u, v);
			}
		}
		int root = find(1);

		for (int i = 2; i < N + 1; i++) {
			if (find(i) != root) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(answer);
	}

	private static int find(int a) {
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa > pb) {
			parent[pa] = pb;
		} else {
			parent[pb] = pa;
		}
	}
}
