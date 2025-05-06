import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		boolean[] isNotPrime = new boolean[N + 1];
		int[] prime = new int[N+1];
		prime[0] = 0;

		isNotPrime[0] = true;
		isNotPrime[1] = true;

		int index = 1;
		for (int i = 2; i <= N; i++) {
			if (!isNotPrime[i]) {
				prime[index++] = i;

				for (int j = i * 2; j <= N; j += i) {
					isNotPrime[j] = true;
				}
			}
		}

		for (int i = 1; i < index; i++) {
			prime[i] += prime[i - 1];
		}

		int left = 0;
		int right = 0;
		int count = 0;

		while (right < index) {
			int sum = prime[right] - prime[left];

			if (sum == N) {
				count++;
			}

			if (sum < N) {
				right++;
			} else {
				left++;
			}
		}

		System.out.println(count);
	}
}
