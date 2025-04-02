import java.util.*;
import java.io.*;

public class Main {
	static int n, d, k, c;
	static int[] sushi, count;
	static int varCount = 1;
	static int maxVarCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		sushi = new int[n];
		count = new int[d + 1];

		for (int i = 0; i < n; i++) {
			int number = Integer.parseInt(br.readLine());
			sushi[i] = number;
		}
		count[c]++;

		for (int i = 0; i < k; i++) {
			if (count[sushi[i % n]] == 0) {
				varCount++;
			}
			count[sushi[i]]++;
		}
		maxVarCount = varCount;

		for (int i = 1; i < n; i++) {
			count[sushi[(i - 1) % n]]--;

			if (count[sushi[(i - 1) % n]] == 0) {
				varCount--;
			}
			if (count[sushi[(i + k - 1) % n]] == 0) {
				varCount++;
			}
			count[sushi[(i + k - 1) % n]]++;

			maxVarCount = Math.max(varCount, maxVarCount);
		}

		System.out.println(maxVarCount);
	}
}
