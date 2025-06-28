import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static HashSet<Integer> visited;
	static int[] dir = new int[] { -1, 1, 2 };

	static class Position implements Comparable<Position> {
		int num;
		int time;

		public Position(int num, int time) {
			this.num = num;
			this.time = time;
		}

		@Override
		public int compareTo(Position other) {
			return this.time - other.time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (N == K) {
			System.out.println(0);
			return;
		}

		PriorityQueue<Position> pq = new PriorityQueue<>();
		visited = new HashSet<>();
		pq.add(new Position(N, 0));

		while (!pq.isEmpty()) {
			Position c = pq.poll();
			int current = c.num;
			int time = c.time;

			if (visited.contains(current)) continue;
			visited.add(current);
			
			if (current == K) {
				System.out.println(time);
				return;
			}

			for (int d : dir) {
				int next = (d == 2) ? current * 2 : current + d;
				int nextTime = (d == 2) ? time : time + 1;

				if (next >= 0 && next <= 100000) {
					pq.add(new Position(next, nextTime));
				}
			}
		}

	}
}