import java.io.*;
import java.util.*;

public class Main {

	static class Subject {
		double score;
		String rank;

		public Subject(double score, String rank) {
			this.score = score;
			this.rank = rank;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Double> ranks = new HashMap<>();
		ranks.put("A+", 4.5);
		ranks.put("A0", 4.0);
		ranks.put("B+", 3.5);
		ranks.put("B0", 3.0);
		ranks.put("C+", 2.5);
		ranks.put("C0", 2.0);
		ranks.put("D+", 1.5);
		ranks.put("D0", 1.0);
		ranks.put("F", 0.0);
		double total = 0.0;
		
		ArrayList<Subject> subjects = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();

			double score = Double.parseDouble(st.nextToken());
			String rank = st.nextToken();

			if (rank.equals("P"))
				continue;

			total += score;
			subjects.add(new Subject(score, rank));
		}

		double sum = 0.0;

		for (Subject sb : subjects) {
			sum += (sb.score * ranks.get(sb.rank));
		}
		
		System.out.println(sum / total);
	}
}
