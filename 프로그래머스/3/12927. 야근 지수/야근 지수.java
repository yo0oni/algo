import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        // 야근 피로도 += Math.pow(야근 시작 시점에 남은 작억량, 2)
        // N시간동안 야근 피로도 최소화
        // 1시간동안 작업량 1 처리
        
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);

        for(int work: works) {
            pq.add(work);
            sum += work;
        }
        
        if(sum <= n) {
            return 0;
        }
        
        for(int i = 0; i < n; i ++) {
            int time = pq.poll() - 1;
            
            if(time > 0) {
                pq.add(time);
            }
        }
        
        while(!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}