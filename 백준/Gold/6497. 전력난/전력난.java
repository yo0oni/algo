import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

class Main {

	static int m, n;
	static ArrayList<Edge> costs;
	static int[] parent;
	static int x, y, z;
	static int originCost;
	static int minCost;

	static class Edge {
		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			if (m == 0 && n == 0) {
				break;
			}
			parent = new int[m + 1];
			costs = new ArrayList<>();
			originCost = 0;
			minCost = 0;

			for (int i = 0; i < m + 1; i++) {
				parent[i] = i;
			}

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				z = Integer.parseInt(st.nextToken());
				originCost += z;
				costs.add(new Edge(x, y, z));
			}

			costs.sort((o1, o2) -> Integer.compare(o1.cost, o2.cost));

			Iterator<Edge> iter = costs.iterator();
			while (iter.hasNext()) {
			    Edge edge = iter.next();
			    if (find(edge.from) != find(edge.to)) {
			        union(edge.from, edge.to);
			        minCost += edge.cost;
			    }
			}
			sb.append(originCost - minCost).append("\n");
		}
		System.out.println(sb);
	}

	private static void union(int from, int to) {
		int pFrom = find(from);
		int tFrom = find(to);

		if (pFrom < tFrom) {
			parent[pFrom] = tFrom;
		} else {
			parent[tFrom] = pFrom;
		}
	}

	private static int find(int v) {
		if (parent[v] != v) {
			parent[v] = find(parent[v]);
		}
		return parent[v];
	}
}
