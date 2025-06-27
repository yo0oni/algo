import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] crane, box;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		crane = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			crane[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		box = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(crane);
		Arrays.sort(box);

		if (box[M - 1] > crane[N - 1]) {
			System.out.println(-1);
			return;
		}

		boolean[] moved = new boolean[M];
		int movedCount = 0;
		int time = 0;

		while (movedCount < M) {
			int boxIdx = M - 1;

			for (int i = N - 1; i >= 0; i--) {
				while (boxIdx >= 0) {
					if (!moved[boxIdx] && crane[i] >= box[boxIdx]) {
						moved[boxIdx] = true;
						movedCount++;
						boxIdx--;
						break;
					}
					boxIdx--;
				}
			}

			time++;
		}

		System.out.println(time);
	}
}
