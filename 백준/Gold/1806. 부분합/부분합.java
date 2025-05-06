import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N + 1];
		numbers[0] = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 2; i < N + 1; i++) {
			numbers[i] += numbers[i - 1];
		}

		int answer = Integer.MAX_VALUE;
		int left = 0;
		int right = 0;

		while (right < N+1) {
			int sum = numbers[right] - numbers[left];

			if (sum >= S) {
				answer = Math.min(answer, right - left);
			}

			if (sum < S) {
				right++;
			} else {
				left++;
			}
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}
}
