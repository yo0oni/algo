import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static long[] arr, minTree, maxTree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new long[N];
		minTree = new long[N * 4];
		maxTree = new long[N * 4];

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		initMin(1, 0, N - 1);
		initMax(1, 0, N - 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			sb.append(minQuery(1, 0, N - 1, a - 1, b - 1)).append(" ").append(maxQuery(1, 0, N - 1, a - 1, b - 1)).append("\n");
		}
		System.out.println(sb);
	}

	private static long initMin(int node, int start, int end) {
		if (start == end)
			return minTree[node] = arr[start];

		int mid = (start + end) / 2;
		return minTree[node] = Math.min(initMin(node * 2, start, mid), initMin(node * 2 + 1, mid + 1, end));
	}
	
	private static long initMax(int node, int start, int end) {
		if (start == end)
			return maxTree[node] = arr[start];

		int mid = (start + end) / 2;
		return maxTree[node] = Math.max(initMax(node * 2, start, mid), initMax(node * 2 + 1, mid + 1, end));
	}

	private static long minQuery(int node, int start, int end, long left, long right) {
		if (right < start || end < left)
			return Integer.MAX_VALUE;
		if (left <= start && end <= right)
			return minTree[node];

		int mid = (start + end) / 2;
		return Math.min(minQuery(node * 2, start, mid, left, right), minQuery(node * 2 + 1, mid + 1, end, left, right));
	}
	
	private static long maxQuery(int node, int start, int end, long left, long right) {
		if (right < start || end < left)
			return 0;
		if (left <= start && end <= right)
			return maxTree[node];

		int mid = (start + end) / 2;
		return Math.max(maxQuery(node * 2, start, mid, left, right), maxQuery(node * 2 + 1, mid + 1, end, left, right));
	}
}