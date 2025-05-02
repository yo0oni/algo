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
		int[] temper = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			temper[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		int maxAnswer = Integer.MIN_VALUE;
		for (int i = 0; i < K; i++) {
			answer += temper[i];
		}

		maxAnswer = Math.max(answer, maxAnswer);

		for (int i = 0; i < N - K; i++) {
			answer -= temper[i];
			answer += temper[i + K];
			maxAnswer = Math.max(answer, maxAnswer);
		}

		System.out.println(maxAnswer);
	}
}
