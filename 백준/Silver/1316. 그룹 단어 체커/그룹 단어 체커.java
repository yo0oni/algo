import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;

		for (int i = 0; i < N; i++) {
			int[] last = new int[27];
			char[] chars = br.readLine().toCharArray();
			boolean fail = false;

			for (int j = 1; j < chars.length + 1; j++) {
				char ch = chars[j-1];

				if (last[ch - 'a'] !=  0 && last[ch - 'a'] != j - 1 && j != 0) {
					fail = true;
					break;
				}
				last[ch - 'a'] = j;
			}

			if (!fail) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}
