import java.io.*;
import java.util.*;

public class Main {
	static int N, S, P;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];

		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			graph[A].add(B);
			graph[B].add(A);
		}

		ArrayDeque<int[]> dq = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		int[] support = new int[S + 1];
		int answer = N - 1;

		visited[P] = true;
		dq.offer(new int[] { P, 0 });

		while (!dq.isEmpty()) {
			int[] value = dq.poll();
			int current = value[0];
			int crackCount = value[1];

			for (int next : graph[current]) {
				if (!visited[next]) {
					if (next >= 1 && next <= S) {
						support[next] = crackCount + 1;
					} else {
						dq.offer(new int[] { next, crackCount + 1 });
						visited[next] = true;
					}
				}
			}
		}

		Arrays.sort(support);
		answer -= support[1];
		answer -= support[2];

		System.out.println(answer);
	}
}
