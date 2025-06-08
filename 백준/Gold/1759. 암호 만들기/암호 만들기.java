import java.io.*;
import java.util.*;

public class Main {
	static int L;
	static int C;
	static String[] words;
	static StringBuilder sb = new StringBuilder();
	static ArrayDeque<String> result = new ArrayDeque<>();
	static HashSet<Character> vowel = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		vowel.add('a');
		vowel.add('i');
		vowel.add('e');
		vowel.add('o');
		vowel.add('u');

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		words = new String[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			words[i] = st.nextToken();
		}

		Arrays.sort(words);
		dfs(0, 0, new StringBuilder());

		while (!result.isEmpty()) {
			sb.append(result.poll()).append("\n");
		}

		System.out.println(sb);
	}

	public static void dfs(int depth, int start, StringBuilder current) {
		if (current.length() == L) {
			String cs = current.toString();
			if (isValid(cs) && !result.contains(cs)) {
				result.add(cs);
			}
			return;
		}

		for (int i = start; i < C; i++) {
			current.append(words[i]);
			dfs(depth + 1, i + 1, current);
			current.deleteCharAt(current.length() - 1);
		}
	}

	private static boolean isValid(String str) {
		int vowels = 0, consonants = 0;

		for (char c : str.toCharArray()) {
			if (vowel.contains(c))
				vowels++;
			else
				consonants++;
		}

		return vowels >= 1 && consonants >= 2;
	}
}
