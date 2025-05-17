import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Number implements Comparable<Number> {
		int index;
		int important;

		public Number(int index, int important) {
			this.index = index;
			this.important = important;
		}

		@Override
		public int compareTo(Number other) {
			return other.important - this.important;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			ArrayDeque<Number> dq = new ArrayDeque<Number>();
			PriorityQueue<Number> pq = new PriorityQueue<Number>();
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				int important = Integer.parseInt(st.nextToken());
				Number number = new Number(i, important);
				dq.add(number);
				pq.add(number);
			}

			int index = 1;
			while (!pq.isEmpty()) {
				Number number = dq.poll();

				if (number.important < pq.peek().important) {
					dq.offer(number);
				} else {
					pq.poll();

					if (number.index == M) {
						sb.append(index).append("\n");
						break;
					}
					index++;
				}
			}
		}
		System.out.println(sb);
	}

}
