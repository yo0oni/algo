import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] houses;
	static int N, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		houses = new int[N];

		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(houses);

		int answer = 0;
		int left = 1;
		int right = houses[N - 1] - houses[0];

		while (left <= right) {
			int mid = (left + right) / 2;

			if (canInstall(mid)) {
			    answer = mid;
			    left = mid + 1;
			} else {
			    right = mid - 1;
			}

		}
		System.out.println(answer);
	}
	
	private static boolean canInstall(int minDistance) {
	    int count = 1;
	    int lastInstalled = houses[0];

	    for (int i = 1; i < N; i++) {
	        if (houses[i] - lastInstalled >= minDistance) {
	            count++;
	            lastInstalled = houses[i];
	        }
	    }

	    return count >= C;
	}

}
