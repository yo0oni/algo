import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] classes = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			classes[i][0] = Integer.parseInt(st.nextToken());
			classes[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(classes, Comparator.comparingInt(a -> a[0]));
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			int start = classes[i][0];
			int end = classes[i][1];

			if (!pq.isEmpty() && pq.peek() <= start) {
				pq.poll();
			}
			pq.offer(end);
		}

		System.out.println(pq.size());
	}
}
