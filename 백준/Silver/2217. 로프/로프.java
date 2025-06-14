import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] weight;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weight = new int[N];
		
		for(int i = 0; i < N; i ++) {
			weight[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(weight);
		
		for(int i = 0; i < N; i ++) {
			answer = Math.max(answer, weight[i] * (N-i));
		}
		System.out.println(answer);
	}
}