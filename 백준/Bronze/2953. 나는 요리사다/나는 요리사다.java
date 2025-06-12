import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int maxSum = 0;
		int maxNum = 0;

		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sum = 0;

			while (st.hasMoreTokens()) {
				sum += Integer.parseInt(st.nextToken());
			}

			if (sum > maxSum) {
				maxNum = i + 1;
				maxSum = sum;
			}
		}
		
		System.out.println(maxNum + " " + maxSum);

	}
}
