import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] count = new int[100001];
		int left = 0, right = 0;
		long answer = 0; // 꼭 long!

		while (right < N) {
			if (count[arr[right]] == 0) {
				count[arr[right]]++;
				right++;
				answer += right - left;
			} else {
				count[arr[left]]--;
				left++;
			}
		}

		System.out.println(answer);
	}
}
