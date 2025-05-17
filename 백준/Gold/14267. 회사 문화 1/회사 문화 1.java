import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.attribute.AclFileAttributeView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static ArrayList<Integer>[] child;
	static int[] score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		child = new ArrayList[n + 1];
		score = new int[n + 1];

		for (int i = 0; i < n + 1; i++) {
			child[i] = new ArrayList<Integer>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if (parent != -1) {
				child[parent].add(i + 1);
			}
		}

		for (int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			score[i] += w;
		}

		dfs(1);

		for (int i = 1; i < n + 1; i++) {
			System.out.print(score[i] + " ");
		}
	}

	private static void dfs(int current) {
		for (int next : child[current]) {
			score[next] += score[current];
			dfs(next);
		}
	}
}