import java.io.*;
import java.util.*;

public class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, answer, maxConnect;
    static int[][] room;
    static List<int[]> core;
    static int l_c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            answer = Integer.MAX_VALUE;
            maxConnect = 0;
            N = Integer.parseInt(br.readLine());
            room = new int[N][N];
            core = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    room[i][j] = Integer.parseInt(st.nextToken());
                    if (i != 0 && i != N - 1 && j != 0 && j != N - 1 && room[i][j] == 1) {
                        core.add(new int[]{i, j});
                    }
                }
            }

            l_c = core.size();
            dfs(0, 0, 0);

            System.out.println("#" + t + " " + answer);
        }
    }

    static void dfs(int depth, int cnt, int connect) {
        if (depth == l_c) {
            if (connect > maxConnect) {
                maxConnect = connect;
                answer = cnt;
            } else if (connect == maxConnect && cnt < answer) {
                answer = cnt;
            }
            return;
        }

        for (int k = depth; k < l_c; k++) {
            int cx = core.get(k)[0];
            int cy = core.get(k)[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx;
                int ny = cy;
                boolean flag = false;
                List<int[]> tmp = new ArrayList<>();
                int leng = 0;

                while (true) {
                    nx += dx[d];
                    ny += dy[d];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        flag = true;
                        break;
                    }
                    if (room[nx][ny] == 1) break;

                    tmp.add(new int[]{nx, ny});
                    leng++;
                }

                if (flag) {
                    for (int[] pos : tmp) {
                        room[pos[0]][pos[1]] = 1;
                    }

                    dfs(k + 1, cnt + leng, connect + 1);

                    for (int[] pos : tmp) {
                        room[pos[0]][pos[1]] = 0;
                    }
                }
            }

            dfs(k + 1, cnt, connect);
            return;
        }
    }
}
