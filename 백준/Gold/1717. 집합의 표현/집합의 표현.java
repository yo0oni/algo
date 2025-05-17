import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];

		for (int i = 0; i < n + 1; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (oper == 0) {
				int rootA = find(a);
				int rootB = find(b);

				if (rootA != rootB) {
					union(rootA, rootB);
				}
			} else {
				int rootA = find(a);
				int rootB = find(b);

				if (rootA == rootB) {
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
			}
		}
		System.out.println(sb);
	}

	private static void union(int rootA, int rootB) {
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
