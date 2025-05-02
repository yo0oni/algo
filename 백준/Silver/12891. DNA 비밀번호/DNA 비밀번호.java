import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int A, C, G, T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 'A’, ‘C’, ‘G’, ‘T’
		// 등장하는 문자의 개수가 특정 개수 이상

		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		String[] strs = br.readLine().split("");
		st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int answer = 0;
		int a = 0;
		int c = 0;
		int g = 0;
		int t = 0;

		for (int i = 0; i < P; i++) {
			if (strs[i].equals("A")) {
				a++;
			} else if (strs[i].equals("C")) {
				c++;
			} else if (strs[i].equals("G")) {
				g++;
			} else if (strs[i].equals("T")) {
				t++;
			}

		}

		if (isPossible(a, c, g, t)) {
			answer++;
		}

		for (int i = 0; i < S - P; i++) {
			if (strs[i].equals("A")) {
				a--;
			} else if (strs[i].equals("C")) {
				c--;
			} else if (strs[i].equals("G")) {
				g--;
			} else if (strs[i].equals("T")) {
				t--;
			}

			if (strs[i + P].equals("A")) {
				a++;
			} else if (strs[i + P].equals("C")) {
				c++;
			} else if (strs[i + P].equals("G")) {
				g++;
			} else if (strs[i + P].equals("T")) {
				t++;
			}

			if (isPossible(a, c, g, t)) {
				answer++;
			}
		}

		System.out.println(answer);
	}

	private static boolean isPossible(int a, int c, int g, int t) {
		if (a >= A && c >= C && g >= G && t >= T) {
			return true;
		}
		return false;
	}
}
