import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[] parent, size;
	static HashMap<String, Integer> indexing;
	static int index;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int F = Integer.parseInt(br.readLine());
			indexing = new HashMap<>();
			index = 0;
			parent = new int[F * 2];
			size = new int[F * 2];

			for (int i = 0; i < F * 2; i++) {
				parent[i] = i;
				size[i] = 1;
			}

			for (int f = 0; f < F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				indexing.putIfAbsent(a, index++);
				indexing.putIfAbsent(b, index++);

				int aIdx = indexing.get(a);
				int bIdx = indexing.get(b);

				int rootA = find(aIdx);
				int rootB = find(bIdx);

				if (rootA != rootB) {
					union(rootA, rootB);
					
				}

				sb.append(size[find(aIdx)]).append("\n");
			}
		}

		System.out.print(sb);
	}

	private static void union(int rootA, int rootB) {
		if (rootA < rootB) {
			parent[rootB] = rootA;
			size[rootA] += size[rootB];
		} else {
			parent[rootA] = rootB;
			size[rootB] += size[rootA];
		}
	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
