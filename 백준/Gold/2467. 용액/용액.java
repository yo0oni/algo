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
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N - 1;
		int nearZero = Integer.MAX_VALUE;
		int ans1 = 0;
		int ans2 = 0;

		while (left < right) {
			int sum = numbers[left] + numbers[right];

			if (Math.abs(sum) < nearZero) {
				nearZero = Math.abs(sum);
				ans1 = numbers[left];
				ans2 = numbers[right];

				if (sum < 0) {
					left++;
				} else {
					right--;
				}
			} else if (Math.abs(sum) >= nearZero) {
				if (sum < 0) {
					left++;
				} else {
					right--;
				}
			}

			if (Math.abs(sum) == 0) {
				break;
			}
		}

		int[] answer = new int[] { ans1, ans2 };
		Arrays.sort(answer);

		System.out.println(answer[0] + " " + answer[1]);
	}
}
