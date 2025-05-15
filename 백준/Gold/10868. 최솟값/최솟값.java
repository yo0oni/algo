import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static long[] arr, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new long[N];
		tree = new long[N * 4];

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		init(1, 0, N - 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			sb.append(query(1, 0, N - 1, a - 1, b - 1)).append("\n");
		}
		System.out.println(sb);
	}

	private static long init(int node, int start, int end) {
		if (start == end)
			return tree[node] = arr[start];

		int mid = (start + end) / 2;
		return tree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
	}

	private static long query(int node, int start, int end, long left, long right) {
		if (right < start || end < left)
			return Integer.MAX_VALUE;
		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return Math.min(query(node * 2, start, mid, left, right), query(node * 2 + 1, mid + 1, end, left, right));
	}
}