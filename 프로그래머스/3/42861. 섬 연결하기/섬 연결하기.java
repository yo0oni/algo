import java.util.*;

class Solution {
    static int[] parent;
    
    public void union(int a, int b) {
        if(a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
    
    public int find(int a) {
        if(a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        
        for(int i = 0; i < n; i ++) {
            parent[i] = i;
        }
        
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        for(int i = 0; i < costs.length; i ++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int cost = costs[i][2];
            
            int pa = find(a);
            int pb = find(b);
            
            if(pa != pb) {
                union(pa, pb);
                answer += cost;
            }
            
        }
        
        return answer;
    }
}