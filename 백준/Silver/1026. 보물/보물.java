import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Integer[] a, b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		a = new Integer[N];
		b = new Integer[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(a, Collections.reverseOrder());
		Arrays.sort(b);
		
		int answer = 0;
		for(int i = 0; i < N; i ++) {
			answer += a[i] * b[i];
		}
		
		System.out.println(answer);
	}
}