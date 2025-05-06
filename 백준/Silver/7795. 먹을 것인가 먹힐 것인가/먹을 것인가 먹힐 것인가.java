import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] a = new int[N];
			int[] b = new int[M];
			int count = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(b);

			for (int i = 0; i < N; i++) {

				int left = 0;
				int right = M;

				while (left < right) {
					int mid = (left + right) / 2;

					if (b[mid] < a[i]) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}

				count += left;
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}
}
