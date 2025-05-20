import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			sb.append(str.charAt(0)).append(str.charAt(str.length()-1)).append("\n");
		}
		System.out.println(sb);
	}
}