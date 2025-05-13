import java.util.*;

class Solution {
    
    static ArrayList<Integer> cctvs = new ArrayList<>();
    
    class Route implements Comparable<Route> {
        int number;
        int start;
        int end;
        
        public Route(int number, int start, int end) {
            this.number = number;
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Route other) {
            if(this.end == other.end) {
                return this.start - other.start;
            }
            return this.end - other.end;
        }
    }
    
    public int solution(int[][] routes) {
        int answer = 0;
        boolean[] visited = new boolean[routes.length];
        PriorityQueue<Route> pq = new PriorityQueue<>();
        
        int number = 0;
        for(int[] route : routes) {
            pq.add(new Route(number++, route[0], route[1]));
        }
        
        while(!pq.isEmpty()) {
            Route current = pq.poll();
            
            if(checked(current)) {
                visited[current.number] = true;
                continue;
            }
            
            if(!visited[current.number]) {
                cctvs.add(current.end);
                answer++;
            }
        }
        
        return answer;
    }
    
    public boolean checked(Route route) {
        for(Integer cctv : cctvs) {
            if(route.end >= cctv && route.start <= cctv) {
                return true;
            }
        }
        return false;
    }
}