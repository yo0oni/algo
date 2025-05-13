class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if(s / n == 0) return new int[] {-1};
        
        int quo = s / n;
        int rem = s % n;
        
        for(int i = 0; i < n; i++){
            if(i >= n - rem){
                answer[i] = quo + 1;
            }
            else answer[i] = quo;
        }
        
        return answer;
    }
}