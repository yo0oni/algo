import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Edge> edges;

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        double cost;

        public Edge(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    static int N, M;
    static double answer = 0.0;
    static int[] parent;

    static public void union(int a, int b) {
        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static public int find(int a) {
        if(parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new PriorityQueue<>();
        parent = new int[N+1];

        int[][] node = new int[N+1][2];
        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            node[i][0] = X;
            node[i][1] = Y;
            parent[i] = i;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int pa = find(a);
            int pb = find(b);

            if(pa != pb) {
                union(pa, pb);
            }
        }

        for(int i = 1; i < N+1; i ++) {
            for(int j = i; j < N+1; j ++) {
                if(i == j) continue;

                int[] start = node[i];
                int[] end = node[j];
                double dx = start[0] - end[0];
                double dy = start[1] - end[1];
                double dist = Math.sqrt(dx * dx + dy * dy);
                edges.add(new Edge(i, j, dist));
            }
        }

        while(!allConnect()) {
            Edge edge = edges.poll();
            int start = edge.start;
            int end = edge.end;

            int ps = find(start);
            int pe = find(end);

            if(ps != pe) {
                union(ps, pe);
                answer += edge.cost;
            }
        }
        System.out.printf("%.2f", answer);
    }

    private static boolean allConnect() {
        int root = find(1);
        for(int i = 2; i <= N; i++) {
            if(find(i) != root) {
                return false;
            }
        }
        return true;
    }
}