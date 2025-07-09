import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int N, taesu, P;
	static ArrayList<Integer> scores;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		taesu = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		scores = new ArrayList<>();

		if (N == 0) {
			System.out.println(1);
			return;
		}

		st = new StringTokenizer(br.readLine());
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int score = Integer.parseInt(st.nextToken());
			scores.add(score);
			min = Math.min(min, score);
			max = Math.max(max, score);
		}

		Collections.sort(scores, Comparator.reverseOrder());

		if (N == P) {
			if (min >= taesu) {
				System.out.println(-1);
				return;
			}
		} else {
			for (int i = 0; i < N; i++) {
				if (taesu >= scores.get(i)) {
					System.out.println(i + 1);
					return;
				}
			}
			if(min >= taesu) {
				System.out.println(scores.size() + 1);
				return;
			}
		}

		for (int i = 0; i < N; i++) {
			if (scores.get(i) <= taesu) {
				System.out.println(i + 1);
				return;
			}
		}
	}
}