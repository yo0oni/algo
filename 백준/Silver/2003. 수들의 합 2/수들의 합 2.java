import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = 0;
		int sum = numbers[right];
		int answer = 0;

		while (right < N) {
			if (sum < M) {
				if (right + 1 == N) {
					break;
				}
				sum += numbers[++right];
			} else if (sum > M) {
				sum -= numbers[left++];
			}

			if (sum == M) {
				answer++;

				if (right + 1 == N) {
					break;
				}
				sum += numbers[++right];
			}
		}
		System.out.println(answer);
	}

}
