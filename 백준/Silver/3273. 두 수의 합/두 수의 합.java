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

		int X = Integer.parseInt(br.readLine());

		Arrays.sort(numbers);

		int left = 0;
		int right = N - 1;
		int count = 0;

		while (left < right) {
			int sum = numbers[left] + numbers[right];

			if (sum < X) {
				left++;
			} else if (sum >= X) {
				right--;
			}

			if (sum == X) {
				count++;
			}
		}
		System.out.println(count);
	}

}
