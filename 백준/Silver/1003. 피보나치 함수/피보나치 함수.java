import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int T;
	static int N;
	static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			count = new int[N + 1];
			count[0] = 0;
			if (N >= 1)
				count[1] = 1;
			if (N >= 2)
				count[2] = 1;
			if (N >= 3)
				count[3] = 2;

			for (int i = 4; i <= N; i++) {
				count[i] = count[i - 1] + count[i - 2];
			}
			if (N == 0) {
				sb.append(1).append(" ").append(count[N]).append("\n");
			} else {
				sb.append(count[N - 1]).append(" ").append(count[N]).append("\n");
			}
		}
		System.out.println(sb);
	}
}
