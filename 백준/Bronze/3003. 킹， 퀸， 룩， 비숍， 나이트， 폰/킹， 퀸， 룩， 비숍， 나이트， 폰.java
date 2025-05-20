import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] peice = new int[] { 1, 1, 2, 2, 2, 8 };
		int index = 0;
		
		while (st.hasMoreTokens()) {
			sb.append(peice[index++] - Integer.parseInt(st.nextToken())).append(" ");
		}
		System.out.println(sb);
	}
}