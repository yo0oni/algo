import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		sb.append(2000).append("\n");
		
		for(int i = 0; i < 1000; i ++) {
			sb.append(1000).append(" ");
		}
		
		for(int i = 1; i <= 1000; i ++) {
			sb.append(1).append(" ");
		}
		
		System.out.println(sb);
	}
}