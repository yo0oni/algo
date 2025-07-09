import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Line[] arr;
    static int[] dp;

    static class Line implements Comparable<Line> {
        int a;
        int b;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line o) {
            return this.a - o.a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Line[N];
        dp = new int[N];
        Arrays.fill(dp, 1);

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr);

        for(int i = 0; i < N; i ++) {
            for(int j = 0; j < i; j ++) {
                if(arr[i].b > arr[j].b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for(int i = 0; i < N; i ++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(N-max);
    }
}