import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] words;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			words = br.readLine().toCharArray();
			K = Integer.parseInt(br.readLine());

			int shortLength = findShort();
			if (shortLength == -1) {
				sb.append(-1).append("\n");
				continue;
			}

			int longLength = findLong();
			sb.append(shortLength).append(" ").append(longLength).append("\n");
		}
		System.out.println(sb);
	}

	private static int findLong() {
		int maxLength = -1;

		for (int left = 0; left < words.length; left++) {
			int[] count = new int[26];

			for (int right = left; right < words.length; right++) {
				count[words[right] - 'a']++;

				if (words[left] == words[right] && count[words[left] - 'a'] == K) {
					maxLength = Math.max(maxLength, right - left + 1);
				}
			}
		}

		return maxLength;
	}

	private static int findShort() {
	    int minLength = Integer.MAX_VALUE;

	    for (int i = 0; i < words.length; i++) {
	        int kCount = 0;

	        for (int j = i; j < words.length; j++) {
	            if (words[j] == words[i]) {
	                kCount++;
	            }

	            if (kCount == K) {
	                minLength = Math.min(minLength, j - i + 1);
	                break; 
	            }
	        }
	    }

	    return (minLength == Integer.MAX_VALUE) ? -1 : minLength;
	}

}
