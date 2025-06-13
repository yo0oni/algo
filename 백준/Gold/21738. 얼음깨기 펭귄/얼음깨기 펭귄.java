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

		int answer = N - 1;
		boolean[] visited = new boolean[N + 1];
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		HashMap<Integer, Integer> zizidae = new HashMap<>();

		visited[P] = true;
		dq.offer(new int[] { P, 0 });

		while (!dq.isEmpty()) {
			int[] value = dq.poll();
			int current = value[0];
			int crackCount = value[1];

			for (int next : graph[current]) {
				if (!visited[next]) {
					if (next >= 1 && next <= S) {
						zizidae.put(next, crackCount + 1);
					} else {
						dq.offer(new int[] { next, crackCount + 1 });
						visited[next] = true;
					}
				}
			}
		}
		
		List<Integer> keySet = new ArrayList<>(zizidae.keySet());
		keySet.sort((a, b) -> zizidae.get(a) - zizidae.get(b));
		
		for(int i = 0; i < 2; i ++) {
			answer -= zizidae.get(keySet.get(i));
		}

		System.out.println(answer);
	}
}
