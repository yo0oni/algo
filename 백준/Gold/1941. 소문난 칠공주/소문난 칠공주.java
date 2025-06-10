import java.io.*;
import java.util.*;

public class Main {

    static char[][] board = new char[5][5];
    static boolean[] selected = new boolean[25];
    static int totalCount = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, 0);
        System.out.println(totalCount);
    }

    // 조합을 생성하면서 동시에 S 개수 세기
    static void dfs(int idx, int depth, int sCount) {
        if (depth - sCount > 3) return; // Y가 4명 이상이면 가지치기
        if (depth == 7) {
            if (isConnected()) totalCount++;
            return;
        }

        for (int i = idx; i < 25; i++) {
            selected[i] = true;
            int r = i / 5;
            int c = i % 5;
            dfs(i + 1, depth + 1, sCount + (board[r][c] == 'S' ? 1 : 0));
            selected[i] = false;
        }
    }

    // 선택된 7명이 연결되어 있는지 BFS로 확인
    static boolean isConnected() {
        boolean[][] visited = new boolean[5][5];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int count = 0;

        for (int i = 0; i < 25; i++) {
            if (selected[i]) {
                int r = i / 5;
                int c = i % 5;
                queue.offer(new int[]{r, c});
                visited[r][c] = true;
                break;
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            count++;

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dx[d];
                int nc = cur[1] + dy[d];
                int nextIdx = nr * 5 + nc;

                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                if (!selected[nextIdx] || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }

        return count == 7;
    }
}
