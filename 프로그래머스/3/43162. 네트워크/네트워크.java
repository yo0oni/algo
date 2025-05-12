import java.util.*;

class Solution {
    static int[][] computers;
        
    public int solution(int n, int[][] computers) {
        int answer = 0;
        this.computers = computers;
        
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i ++) {
            if(!visited[i]) {
                bfs(i, visited);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void bfs(int start, boolean[] visited) {
        visited[start] = true;
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
        dq.add(start);
        
        while(!dq.isEmpty()) {
            int current = dq.poll();
            
            for(int i = 0; i < computers[current].length; i ++) {
                int next = computers[current][i];
                
                if(!visited[i] && next == 1) {
                    visited[i] = true;
                    dq.add(i);                
                }
            }
        }
    }
}