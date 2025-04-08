import java.util.*;
import java.io.*;

public class Solution {
    static int K;
    static int[][] magnets;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            K = Integer.parseInt(br.readLine());
            magnets = new int[4][8];

            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < K; k++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int number = Integer.parseInt(st.nextToken()) - 1; // 0-indexed
                int direction = Integer.parseInt(st.nextToken());

                visited = new boolean[4];
                dfs(number, direction);
            }

            int result = 0;
            for (int i = 0; i < 4; i++) {
                if (magnets[i][0] == 1) {
                    result += (1 << i);
                }
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int number, int direction) {
        visited[number] = true;

        if (number - 1 >= 0 && !visited[number - 1]) {
            if (magnets[number][6] != magnets[number - 1][2]) {
                dfs(number - 1, -direction);
            }
        }

        if (number + 1 < 4 && !visited[number + 1]) {
            if (magnets[number][2] != magnets[number + 1][6]) {
                dfs(number + 1, -direction);
            }
        }

        rotate(number, direction);
    }

    private static void rotate(int number, int direction) {
        if (direction == 1) {
            int temp = magnets[number][7];
            for (int i = 7; i > 0; i--) {
                magnets[number][i] = magnets[number][i - 1];
            }
            magnets[number][0] = temp;
        } else {
            int temp = magnets[number][0];
            for (int i = 0; i < 7; i++) {
                magnets[number][i] = magnets[number][i + 1];
            }
            magnets[number][7] = temp;
        }
    }
}
