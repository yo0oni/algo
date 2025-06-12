import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static PriorityQueue<Integer> small;
	static PriorityQueue<Integer> large;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		small = new PriorityQueue<>((a, b) -> b - a);
		large = new PriorityQueue<>();

		// 두개의 큐가 있음
		// 앞 큐의 끝 값이 중간값

		// 1
		// 1 | 5
		// 1 2 | 5
		// 1 2 | 5 10
		// 1 | 5 10
		// -99 1 2 | 5 7 10
		// -99 1 2 | 5 7 10
		// 만약 위 상태에서 8이 들어오면 large의 peek값보다 들어온 값이 크면 large.poll해서 앞으로 넣고 number ㅐㄹㄹㄷ

		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());

			small.offer(number);

			if (!large.isEmpty() && small.peek() > large.peek()) {
				large.offer(small.poll());
			}

			if (small.size() > large.size() + 1) {
				large.offer(small.poll());
			} else if (large.size() > small.size()) {
				small.offer(large.poll());
			}

			System.out.println(small.peek());

		}
	}
}
