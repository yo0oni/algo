import java.io.*;
import java.util.*;

public class Main {
    static long N, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        k = Long.parseLong(br.readLine());

        long left = 1;
        long right = k;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = countLessOrEqual(mid);

            if (count >= k) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static long countLessOrEqual(long x) {
        long count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(x / i, N);
        }
        return count;
    }
}