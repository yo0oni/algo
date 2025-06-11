import java.io.*;
import java.util.*;

public class Main {
	static int N, Q;
	static int[] diff;
	static int[] people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		diff = new int[1_000_002];
		people = new int[1_000_002];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			diff[S] += 1;
			diff[E + 1] -= 1;
		}

		for (int i = 1; i < diff.length; i++) {
			people[i] += people[i - 1] + diff[i];
		}

		Q = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		while (st.hasMoreTokens()) {
			int time = Integer.parseInt(st.nextToken());
			sb.append(people[time]).append("\n");
		}

		System.out.println(sb);

	}
}
