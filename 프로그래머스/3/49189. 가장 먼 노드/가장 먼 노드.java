import java.util.*;

class Solution {
    static ArrayList<Integer>[] graph;
    static ArrayDeque<int[]> depthes = new ArrayDeque<>();
    static int maxDepth = 1;
    static int answer = 0;
    
    public int solution(int n, int[][] edge) {
        graph = new ArrayList[n+1];
        
        for(int i = 1; i < n +1; i ++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edge.length; i ++) {
            int[] e = edge[i];
            int start = e[0];
            int end = e[1];
            
            graph[start].add(end);
            graph[end].add(start);
        }
        
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        
        for(int num : graph[1]) {
            visited[num] = true;
            dq.offer(new int[]{num, 1});
        }
        
        while(!dq.isEmpty()) {
            int[] current = dq.poll();
            int number = current[0];
            int depth = current[1];
            depthes.offer(new int[] {number, depth});
            
            for(int next : graph[number]) {
                if(!visited[next]) {
                    visited[next] = true;
                    maxDepth = Math.max(depth+1, maxDepth);
                    dq.offer(new int[] {next, depth+1});
                }
            }
        }

        while(!depthes.isEmpty()) {
            int[] cur = depthes.poll();
            
            if(cur[1] == maxDepth) {
                System.out.println(cur[0]);
                answer++;
            }
        }
        
        return answer;
    }
}