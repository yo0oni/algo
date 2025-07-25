import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) break;
            dist[a][b] = dist[b][a] = 1;
        }

        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        int[] score = new int[N + 1];
        int minScore = INF;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++)
                score[i] = Math.max(score[i], dist[i][j]);
            minScore = Math.min(minScore, score[i]);
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (score[i] == minScore)
                candidates.add(i);
        }

        sb.append(minScore).append(" ").append(candidates.size()).append("\n");
        for (int c : candidates)
            sb.append(c).append(" ");
        System.out.println(sb);
    }
}
