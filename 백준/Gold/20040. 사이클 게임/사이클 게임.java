import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] graph;
	static int[] parent;
	static int answer = 0;

	public static void union(int pa, int pb) {
		if (pa > pb) {
			parent[pa] = pb;
		} else {
			parent[pb] = pa;
		}
	}

	private static int find(int a) {
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];

		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int pa = find(a);
			int pb = find(b);

			if (pa == pb) {
				answer = i + 1;
				break;
			}

			union(pa, pb);
		}

		System.out.println(answer);

	}
}
