import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static long[] arr, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		tree = new long[N * 4];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == 0) {
				int left = Math.min(b, c) - 1;
				int right = Math.max(b, c) - 1;
				sb.append(query(1, 0, N - 1, left, right)).append("\n");
			} else {
				update(1, 0, N - 1, b - 1, c);
			}
		}
		System.out.println(sb);
	}

	private static long query(int node, int start, int end, int left, int right) {
		if (right < start || end < left)
			return 0;
		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
	}

	private static void update(int node, int start, int end, int index, long value) {
		if (index < start || index > end)
			return;

		if (start == end) {
			tree[node] = value;
			return;
		}

		int mid = (start + end) / 2;
		update(node * 2, start, mid, index, value);
		update(node * 2 + 1, mid + 1, end, index, value);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

}