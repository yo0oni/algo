import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());

		long Z = (Y * 100) / X;

		if (Z >= 99) {
			System.out.println(-1);
			return;
		}

		long left = 0;
		long right = 1_000_000_000;
		long answer = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			long newZ = ((Y + mid) * 100) / (X + mid);

			if (newZ > Z) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);

	}

}
