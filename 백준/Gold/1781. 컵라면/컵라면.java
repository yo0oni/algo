import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Problem[] problems;
	static HashSet<Integer> deadLines = new HashSet<>();
	static int answer;

	static class Problem implements Comparable<Problem> {
		int deadLine;
		int ramen;

		public Problem(int deadLine, int ramen) {
			this.deadLine = deadLine;
			this.ramen = ramen;
		}

		@Override
		public int compareTo(Problem other) {
			if (this.deadLine == other.deadLine) {
				return other.ramen - this.ramen;
			}
			return this.deadLine - other.deadLine;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		problems = new Problem[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadLine = Integer.parseInt(st.nextToken());
			int ramen = Integer.parseInt(st.nextToken());
			problems[i] = new Problem(deadLine, ramen);
		}

		Arrays.sort(problems);

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (Problem problem : problems) {
			pq.offer(problem.ramen);

			if (pq.size() > problem.deadLine) {
				pq.poll();
			}
		}

		int answer = 0;
		while (!pq.isEmpty()) {
			answer += pq.poll();
		}

		System.out.println(answer);
	}
}
