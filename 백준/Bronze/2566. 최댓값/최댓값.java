import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board = new int[9][9];
		int max = Integer.MIN_VALUE;
		int maxI = 0;
		int maxJ = 0;

		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] > max) {
					max = board[i][j];
					maxI = i + 1;
					maxJ = j + 1;
				}
			}
		}
		System.out.println(max);
		System.out.println(maxI + " " + maxJ);
	}
}
