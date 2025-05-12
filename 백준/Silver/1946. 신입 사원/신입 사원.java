import java.io.*;
import java.util.*;

public class Main {

	static class User implements Comparable<User> {
		private int paper;
		private int meet;

		public User(int paper, int meet) {
			this.paper = paper;
			this.meet = meet;
		}

		@Override
		public int compareTo(User other) {
			if (this.paper == other.paper) {
				return this.meet - other.meet;
			}
			return this.paper - other.paper;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			User[] users = new User[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				users[i] = new User(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			Arrays.sort(users);
			int count = 1;
			int minMeet = users[0].meet;

			for (int i = 1; i < N; i++) {
			    if (users[i].meet < minMeet) {
			        count++;
			        minMeet = users[i].meet;
			    }
			}
			System.out.println(count);
		}
	}

}
