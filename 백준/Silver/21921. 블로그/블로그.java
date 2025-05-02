import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] count = new int[N];
		int answer = 0;
		int maxDay = 1;
		int maxAnswer = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			count[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < X; i++) {
			answer += count[i];
		}

		maxAnswer = Math.max(answer, maxAnswer);

		for (int i = 0; i < N - X; i++) {
			answer -= count[i];
			answer += count[i + X];
			if (answer > maxAnswer) {
				maxDay = 1;
				maxAnswer = answer;
			} else if (answer == maxAnswer) {
				maxDay++;
			}
		}

		if (maxAnswer == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(maxAnswer);
			System.out.println(maxDay);
		}

	}
}
