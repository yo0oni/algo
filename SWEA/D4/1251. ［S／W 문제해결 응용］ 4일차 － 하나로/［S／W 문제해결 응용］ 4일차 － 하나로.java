import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    static int T, N;
    static double E;
    static Island[] islands;
    static PriorityQueue<Edge> pq;
    static int[] parent;
    static double totalCost;

    static class Island {
        double x, y;

        public Island(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        double cost;

        public Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            islands = new Island[N];
            parent = new int[N];
            pq = new PriorityQueue<>();
            totalCost = 0.0;

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            double[] xs = new double[N];
            double[] ys = new double[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                xs[i] = Double.parseDouble(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                ys[i] = Double.parseDouble(st.nextToken());
                islands[i] = new Island(xs[i], ys[i]);
            }

            E = Double.parseDouble(br.readLine());

            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    double cost = calculateCost(islands[i], islands[j]);
                    pq.offer(new Edge(i, j, cost));
                }
            }

            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                if (find(edge.from) != find(edge.to)) {
                    union(edge.from, edge.to);
                    totalCost += edge.cost;
                }
            }

            sb.append("#").append(t).append(" ").append(Math.round(totalCost)).append("\n");
        }
        System.out.print(sb);
    }

    private static double calculateCost(Island a, Island b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return E * (dx * dx + dy * dy);
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        
        if (px < py) {
        	parent[py] = px;
        } else {
        	parent[px] = py;
        }
    }
}
