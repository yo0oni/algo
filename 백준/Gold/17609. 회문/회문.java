import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			char[] words = br.readLine().toCharArray();
			sb.append(getPalindromeLevel(words)).append("\n");
		}
        System.out.println(sb);
	}

	private static int getPalindromeLevel(char[] words) {
		int left = 0;
		int right = words.length - 1;

		while (left < right) {
			if (words[left] == words[right]) {
				left++;
				right--;
			} else {
				if (isPalindrome(words, left + 1, right) || isPalindrome(words, left, right - 1)) {
					return 1;
				} else {
					return 2;
				}
			}
		}
		return 0;
	}

	private static boolean isPalindrome(char[] words, int left, int right) {
		while (left < right) {
			if (words[left] != words[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
