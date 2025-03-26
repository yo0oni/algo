import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
 
class Solution {
    static int length, start;
    static int from, to;
    static HashMap<Integer, ArrayList<Integer>> graph;
    static HashSet<Integer> visited;
    static int maxLast;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;
 
        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            length = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            graph = new HashMap<>();
            visited = new HashSet<>();
            maxLast = 0;
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < length / 2; i++) {
                from = Integer.parseInt(st.nextToken());
                if (graph.get(from) == null) {
                    graph.put(from, new ArrayList<>());
                }
                to = Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
            }
            bfs(start, 0);
            sb.append("#").append(tc).append(" ").append(maxLast).append("\n");
        }
        System.out.println(sb);
    }
 
    private static int bfs(int start, int depth) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        ArrayList<Integer> group;
        dq.add(start);
 
        while (!dq.isEmpty()) {
            int size = dq.size();
            int tempMax = 0;
            boolean updated = false;
            group = new ArrayList<>();
 
            for (int i = 0; i < size; i++) {
                int current = dq.poll();
                group.add(current);
            }
 
            for (int nextParent : group) {
                if (graph.containsKey(nextParent)) {
                    for (int next : graph.get(nextParent)) {
                        if (!visited.contains(next)) {
                            visited.add(next);
                            dq.add(next);
                            tempMax = Math.max(next, tempMax);
                            updated = true;
                        }
                    }
                }
            }
            if (updated) {
                maxLast = tempMax;
            }
        }
        return maxLast;
    }
}