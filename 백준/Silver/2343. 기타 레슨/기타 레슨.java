import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[] arr = new long[N];
		long sum = 0;
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			long time = Long.parseLong(st.nextToken());
			arr[i] = time;
			sum += time;
		}

		long left = 1;
		long right = sum;
		long answer = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			int groupCount = calculate(arr, mid);

			if (groupCount <= M) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);

	}

	private static int calculate(long[] arr, long mid) {
		int count = 1;
		long sum = 0;

		for (long time : arr) {
			if (time > mid)
				return Integer.MAX_VALUE;
			if (time + sum > mid) {
				count++;
				sum = time;
			} else {
				sum += time;
			}
		}
		return count;
	}

}
