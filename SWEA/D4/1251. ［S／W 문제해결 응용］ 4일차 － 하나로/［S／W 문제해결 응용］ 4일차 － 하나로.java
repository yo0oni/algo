import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {
	static int T, N;
	static double X, Y;
	static double E;
	static Island[] islands;
	static ArrayList<double[]> costs;
	static int[] parent;
	static double totalCost;

	static class Island {
		double number;
		double x;
		double y;

		public Island(double number, double x) {
			this.number = number;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			islands = new Island[N];
			costs = new ArrayList<>();
			parent = new int[N];
			totalCost = 0.0;

			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}

			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				X = Integer.parseInt(st.nextToken());
				islands[n] = new Island(n, X);
			}

			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				Y = Integer.parseInt(st.nextToken());
				islands[n].y = Y;
			}

			E = Double.parseDouble(br.readLine());

			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					Island start = islands[i];
					Island end = islands[j];
					double cost = calculateCost(start, end);
					costs.add(new double[] { cost, start.number, end.number });
				}
			}

			costs.sort((o1, o2) -> Double.compare(o1[0], o2[0]));

			for (double[] current : costs) {
				double cost = current[0];
				int start = (int) current[1];
				int end = (int) current[2];
				
				if (find(start) != find(end)) {
					union(start, end);
					totalCost += cost;
				}
			}

			sb.append("#").append(t).append(" ").append(Math.round(totalCost)).append("\n");
		}
		System.out.print(sb);
	}

	private static void union(int start, int end) {
		int ps = find(start);
		int pe = find(end);
		
		if (ps < pe) {
			parent[pe] = ps;
		} else {
			parent[ps] = pe;
		}
	}

	private static int find(int start) {
		if (parent[start] == start) {
			return start;
		}
		return parent[start] = find(parent[start]);
	}

	private static double calculateCost(Island start, Island end) {
		double L = Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2);
		return E * L;
	}
}
