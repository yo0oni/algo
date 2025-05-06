import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		Arrays.sort(numbers);

		for (int i = 0; i < N; i++) {
			int target = numbers[i];

			int left = 0;
			int right = N - 1;

			while (left < right) {
				int sum = numbers[left] + numbers[right];

				if (sum == target && left != i && right != i) {
					answer++;
					break;
				}

				if (sum >= target && left != i) {
					right--;
				} else {
					left++;
				}
			}
		}
		System.out.println(answer);
	}
}
