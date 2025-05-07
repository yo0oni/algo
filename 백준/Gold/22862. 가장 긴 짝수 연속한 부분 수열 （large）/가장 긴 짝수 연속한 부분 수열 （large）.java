import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] arr = new int[S];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < S; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int maxLength = 0;
		int left = 0;
		int right = 0;
		int delete = 0;

		while (right < S && left <= right) {
			if (arr[right] % 2 != 0 && delete < N) {
				delete++;
				right++;
			} else if (arr[right] % 2 != 0 && delete >= N) {
				if (arr[left] % 2 != 0) {
					delete--;
				}
				left++;
			} else if (arr[right] % 2 == 0) {
				right++;
			}
			maxLength = Math.max(maxLength, right - left - delete);

		}
		System.out.println(maxLength);
	}

}
