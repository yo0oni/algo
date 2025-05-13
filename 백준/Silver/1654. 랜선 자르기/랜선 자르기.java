import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int N;
	static int[] heights;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		heights = new int[K];
		int maxHeight = 0;
		
		for(int i = 0; i < K; i ++) {
			int height = Integer.parseInt(br.readLine());
			heights[i] = height;
			maxHeight = Math.max(height, maxHeight);
		}
		
		long left = 1;
		long right = maxHeight;
		long answer = 0;
		
		while(left <= right) {
			long mid = (left + right) / 2;
			long count = calculate(mid);
			
			if(count >= N) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
			
		}
		System.out.println(answer);
	}

	private static long calculate(long mid) {
		long count = 0;
		
		for(int height : heights) {
			if (mid > height) continue;
			count += height / mid;
		}
		
		return count;
	}

}
