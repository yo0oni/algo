import java.io.*;
import java.util.*;

public class Main {
	static int N, H;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		int[] top = new int[N / 2];
		int[] bottom = new int[N / 2];

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0)
				bottom[i / 2] = Integer.parseInt(br.readLine());
			else
				top[i / 2] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bottom);
		Arrays.sort(top);

		int minCrash = Integer.MAX_VALUE;
		int count = 0;

		for (int h = 1; h <= H; h++) {
			int topCrash = top.length - lowerBound(top, H - h + 1);
			int bottomCrash = bottom.length - lowerBound(bottom, h);
			int total = topCrash + bottomCrash;

			if (total < minCrash) {
				minCrash = total;
				count = 1;
			} else if (total == minCrash) {
				count++;
			}
		}
		System.out.println(minCrash + " " + count);
	}

	private static int lowerBound(int[] arr, int height) {
		int left = 0;
		int right = arr.length - 1;
		int answer = arr.length;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (arr[mid] >= height) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return answer;
	}

}
