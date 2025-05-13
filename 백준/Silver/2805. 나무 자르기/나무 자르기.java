import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long M;
	static long[] trees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		trees = new long[N];
		long maxHeight = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			int height = Integer.parseInt(st.nextToken());
			trees[i] = height;
			maxHeight = Math.max(maxHeight, height);
		}
		
		long left = 0;
		long right = maxHeight;
		long answer = 0;
		
		while(left <= right) {
			long mid = (left + right) / 2;
			long total = getTree(mid);
			
			if(total >= M) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(answer);
	}

	private static long getTree(long mid) {
		long total = 0;
		
		for(int i = 0; i < N; i ++) {
			if(trees[i] < mid) continue;
			total += trees[i] - mid;
		}
		return total;
	}

}
