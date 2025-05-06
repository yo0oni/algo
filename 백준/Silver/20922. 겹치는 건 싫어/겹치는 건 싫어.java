import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		int[] count = new int[100001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			arr[i] = number;
		}

		int left = 0;
		int right = 0;
		int maxLength = 0;

		while (right < N) {
			if (count[arr[right]] < K) {
				count[arr[right]]++;
				right++;
			} else {
				count[arr[left]]--;
				left++;
			}

			maxLength = Math.max(maxLength, right - left);
		}
		System.out.println(maxLength);
	}
}
