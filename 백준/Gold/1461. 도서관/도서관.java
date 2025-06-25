import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static PriorityQueue<Integer> minus;
	static PriorityQueue<Integer> plus;
	static int answer = 0;
	static int min = 0, max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minus = new PriorityQueue<>();
		plus = new PriorityQueue<>((a, b) -> b - a);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			if (number < 0) {
				minus.add(number);
			} else {
				plus.add(number);
			}
		}

		if (!minus.isEmpty()) {
			min = Math.abs(minus.peek());
		}
		if (!plus.isEmpty()) {
			max = plus.peek();
		}

		while (minus.size() >= M) {
			answer += Math.abs(minus.poll()) * 2;
			for (int i = 0; i < M - 1; i++) {
				minus.poll();
			}
		}

		if (!minus.isEmpty()) {
			answer += Math.abs(minus.poll()) * 2;
		}

		while (plus.size() >= M) {
			answer += plus.poll() * 2;
			for (int i = 0; i < M - 1; i++) {
				plus.poll();
			}
		}

		if (!plus.isEmpty()) {
			answer += plus.poll() * 2;
		}

		if (min > max) {
			answer -= min;
		} else {
			answer -= max;
		}

		System.out.println(answer);
	}
}