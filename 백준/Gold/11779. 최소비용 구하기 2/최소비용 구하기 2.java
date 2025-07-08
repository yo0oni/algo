import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<Node>[] graph;
    static int totalCost = 0;

    static class Node implements Comparable<Node> {
        int cost;
        int number;

        public Node(int cost, int number) {
            this.cost = cost;
            this.number = number;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];

        for(int i = 1; i < n+1; i ++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i ++) {
            StringTokenizer st = new  StringTokenizer(br.readLine());
            int start =  Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(cost, end));
        }

        StringTokenizer st = new  StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, start));
        int[] prefix = new int[n+1];
        int[] minCost = new int[n+1];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int cost = node.cost;
            int current = node.number;

            if(current == end) {
                StringBuilder sb = new StringBuilder();

                sb.append(cost).append("\n");

                ArrayDeque<Integer> dq = new  ArrayDeque<>();
                dq.offer(end);

                while(end != start) {
                    dq.offer(prefix[end]);
                    end = prefix[end];
                }

                sb.append(dq.size()).append("\n");
                while(!dq.isEmpty()) {
                    sb.append(dq.pollLast()).append(" ");
                }

                System.out.println(sb);
                break;
            }

            for(Node nd: graph[current]) {
                int newCost = cost + nd.cost;
                int next = nd.number;

                if(newCost < minCost[next]) {
                    minCost[next] = newCost;
                    prefix[next] = current;
                    pq.offer(new Node(newCost, next));
                }
            }
        }
    }
}