import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long M;
	static long[] times;
	static long maxTime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		times = new long[N];

		for (int i = 0; i < N; i++) {
			long time = Long.parseLong(br.readLine());
			times[i] = time;
			maxTime = Math.max(time, maxTime);
		}

		long left = 0;
		long right = maxTime * M;
		long answer = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			long count = checkTime(mid);

			if (count >= M) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}

	private static long checkTime(long mid) {
		long count = 0;
		for (long time : times) {
			count += (mid / time);

			if (count >= M)
				break;
		}
		return count;
	}
}
