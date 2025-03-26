import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution {
	static int T;
	static int N, M;
	static int a, b;
	static int[] parent;
	static boolean[] visited;
	static HashSet<Integer> result;
	static HashMap<Integer, ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			visited = new boolean[N + 1];
			parent = new int[N + 1];
			result = new HashSet<>();
			graph = new HashMap<>();

			for (int i = 1; i < N + 1; i++) {
				parent[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
				graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
			}

			for (int i = 1; i < N + 1; i++) {
				if (!visited[i]) {
					bfs(i);
				}
			}

			for (int i = 1; i < N + 1; i++) {
				result.add(parent[i]);
			}
			sb.append("#").append(t).append(" ").append(result.size()).append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs(int start) {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		dq.add(start);
		visited[start] = true;

		while (!dq.isEmpty()) {
			int current = dq.poll();

			if (graph.containsKey(current)) {
				for (int next : graph.get(current)) {
					if (!visited[next]) {
						visited[next] = true;
						dq.add(next);
						parent[next] = start;
					}
				}
			}
		}
	}
}
