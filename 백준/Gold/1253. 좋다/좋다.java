import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] numbers = new long[N];

		for (int index = 0; index < N; index++) {
			numbers[index] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(numbers);
		int count = 0;

		for (int index = 0; index < N; index++) {
			Long target = numbers[index];

			int left = 0;
			int right = N - 1;

			while (left < right) {
				long sum = numbers[left] + numbers[right];

				if (sum == target) {
					if (left != index && right != index) {
						count++;
						break;
					}
				}

				if (left == index) {
					left++;
				} else if (right == index) {
					right--;
				} else {
					if (sum >= target) {
						right--;
					} else {
						left++;
					}
				}

			}
		}
		System.out.println(count);
	}
}
