import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] numbers;
	static ArrayList<Integer> lis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		lis = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int pos = Collections.binarySearch(lis, numbers[i]);

			if (pos < 0) {
				pos = -(pos + 1);
			}

			if (pos == lis.size()) {
				lis.add(numbers[i]);
			} else {
				lis.set(pos, numbers[i]);
			}
		}

		System.out.println(lis.size());

	}
}
