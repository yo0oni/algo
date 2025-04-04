import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N, M;
	static int a, b;
	static int[] count;
	static ArrayList<Integer>[] tallGraph;
	static ArrayList<Integer>[] shortGraph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			tallGraph = new ArrayList[N + 1];
			shortGraph = new ArrayList[N + 1];
			count = new int[N + 1];

			for (int i = 1; i < N + 1; i++) {
				tallGraph[i] = new ArrayList<>();
				shortGraph[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				tallGraph[a].add(b);
				shortGraph[b].add(a);
			}

			for (int i = 1; i < N + 1; i++) {
				findShorter(i);
				findTaller(i);
			}

			sb.append("#").append(t).append(" ").append(canKnow()).append("\n");
		}
		System.out.println(sb);
	}

	private static Object canKnow() {
		int answer = 0;
		for (int c : count) {
			if (c == N - 1) {
				answer++;
			}
		}
		return answer;
	}

	private static void findTaller(int start) {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		dq.add(start);

		while (!dq.isEmpty()) {
			int current = dq.poll();

			for (int next : tallGraph[current]) {
				if (!visited[next]) {
					dq.add(next);
					visited[next] = true;
					count[start]++;
				}
			}
		}
	}

	private static void findShorter(int start) {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		dq.add(start);

		while (!dq.isEmpty()) {
			int current = dq.poll();

			for (int next : shortGraph[current]) {
				if (!visited[next]) {
					dq.add(next);
					visited[next] = true;
					count[start]++;
				}
			}
		}
	}
}
