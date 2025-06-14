import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[] sonsil;
	static long M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = 0;
		sonsil = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			sonsil[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(sonsil);
		
		if(sonsil.length % 2 != 0) {
			M = sonsil[N-1];
			
			for(int i = 0; i < N/2; i ++) {
				M = Math.max(M, sonsil[i] + sonsil[N - i - 2]);
			}
		} else {
			for(int i = 0; i < N/2; i ++) {
				M = Math.max(M, sonsil[i] + sonsil[N - i - 1]);
			}
		}
		
		
		
		System.out.println(M);
	}
}