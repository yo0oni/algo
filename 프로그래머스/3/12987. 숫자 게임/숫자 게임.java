import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        PriorityQueue<Integer> apq = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> bpq = new PriorityQueue<>((a, b) -> b - a);
        
        for(int i = 0; i < B.length; i ++) {
            apq.add(A[i]);
            bpq.add(B[i]);
        }
        
        for(int i = 0; i < B.length; i ++) {
            if(apq.peek() < bpq.peek()) {
                answer++;
                bpq.poll();
            } 
            apq.poll();
        }
        
        return answer;
    }
}