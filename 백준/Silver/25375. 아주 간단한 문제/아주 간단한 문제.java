import java.io.*;
import java.util.*;

public class Main {
	static int Q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Q = Integer.parseInt(br.readLine());

		for (int i = 0; i < Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			if (a * 2 > b || b % a != 0) {
				sb.append(0).append("\n");
			} else {
				sb.append(1).append("\n");
			}
		}
		System.out.println(sb);
	}
}