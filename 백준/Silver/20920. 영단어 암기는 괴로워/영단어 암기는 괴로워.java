import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static HashMap<String, Integer> counts = new HashMap<>();

	static class Word implements Comparable<Word> {
		String value;
		int count;
		int length;

		public Word(String value, int count, int length) {
			this.value = value;
			this.count = count;
			this.length = length;
		}

		@Override
		public int compareTo(Word o) {
			if (this.count == o.count) {
				if (this.length == o.length) {
					return this.value.compareTo(o.value);
				}
				return o.length - this.length;
			}
			return o.count - this.count;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Word> words = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			String word = br.readLine();

			if (word.length() < M)
				continue;

			if (counts.containsKey(word)) {
				counts.put(word, counts.get(word) + 1);
			} else {
				counts.put(word, 1);
			}
		}

		for (String key : counts.keySet()) {
			int count = counts.get(key);
			Word word = new Word(key, count, key.length());
			words.add(word);
		}

		while (!words.isEmpty()) {
			sb.append(words.poll().value).append("\n");
		}
		System.out.println(sb);
	}

}
