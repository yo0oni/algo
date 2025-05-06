import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);
		long minDiff = Long.MAX_VALUE;
		long[] answer = new long[3];

		for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				long sum = arr[i] + arr[left] + arr[right];

				if (Math.abs(sum) < minDiff) {
					minDiff = Math.abs(sum);
					answer[0] = arr[i];
					answer[1] = arr[left];
					answer[2] = arr[right];
				}

				if (sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
	}
}
