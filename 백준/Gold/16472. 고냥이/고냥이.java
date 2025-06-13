import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[] chars;
	static int haveKind = 0;
	static HashMap<Character, Integer> charCount = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		chars = br.readLine().toCharArray();

		for (char ch : chars) {
			charCount.put(ch, 0);
		}

		int start = 0;
		int end = 0;
		int maxLen = 0;

		while (end < chars.length) {
			if (charCount.get(chars[end]) == 0) {
				haveKind++;
			}
			charCount.put(chars[end], charCount.get(chars[end]) + 1);

			end++;

			while (haveKind > N && start < end) {
				charCount.put(chars[start], charCount.get(chars[start]) - 1);
				if (charCount.get(chars[start]) == 0) {
					haveKind--;
				}
				start++;
			}

			maxLen = Math.max(maxLen, end - start);
		}
		
		System.out.println(maxLen);

	}
}
