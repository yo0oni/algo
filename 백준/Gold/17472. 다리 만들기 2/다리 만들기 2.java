
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int number = 1;
    static int n, m;
    static int[][] board;
    static boolean[][] number_visited;
    static List<int[]> lands = new ArrayList<>();
    static List<int[]> graph = new ArrayList<>();
    static int[] parent;

    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        // 그릴 수 있는 모든 간선 찾기
        // 길이 기준으로 정렬하기
        // mst 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for(int i = 0; i < n; i ++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < m; j ++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호 할당
        number_visited = new boolean[n][m];
        for(int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if(board[i][j] == 1 && !number_visited[i][j]) {
                    setNumber(i, j);
                }
            }
        }
        
        parent = new int[number+1];
        for (int i = 1; i < number+1; i++) parent[i] = i;

        // 1. 그릴 수 있는 간선 찾기
        for(int[] current: lands) {
            int ci = current[0];
            int cj = current[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci;
                int nj = cj;
                int distance = 0;

                while (true) {
                    ni += di[d];
                    nj += dj[d];
            
                    if (!validate(ni, nj) || board[ni][nj] != 0) break;
                    distance++;
                }
                
                // 번호가 다른 섬이고 거리가 2 이상이면
                if (validate(ni, nj) && board[ni][nj] != 0 && board[ni][nj] != board[ci][cj] && distance >= 2) {
                    // 거리, 출발섬 번호, 도착섬 번호 추가
                    graph.add(new int[]{distance, board[ci][cj], board[ni][nj]});
                }
            }
        }
        // 2. 간선의 길이 기준으로 정렬
        graph.sort((a, b) -> Integer.compare(a[0], b[0]));
        int total_distance = 0;

        // 3. MST 구현
        int total_edges = 0;
        for (int[] edges : graph) {
            int distance = edges[0];
            int start = edges[1];
            int end = edges[2];

            if (find(start) != find(end)) {
                union(start, end);
                total_distance += distance;
                total_edges++;
            }
        }

        // 간선이 (섬 개수 - 1)개인지 확인
        if (total_edges == number - 2) {
            System.out.println(total_distance);
        } else {
            System.out.println(-1);
        }
        
    }

    static void setNumber(int si, int sj) {
        Queue<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{si, sj});
        number_visited[si][sj] = true;
        board[si][sj] = number;
        lands.add(new int[]{si, sj});

        while(!dq.isEmpty()) {
            int[] current = dq.poll();
            int ci = current[0];
            int cj = current[1];

            for (int d = 0; d < 4; d ++) {
                int ni = di[d] + ci;
                int nj = dj[d] + cj;

                if (validate(ni, nj) && !number_visited[ni][nj]) {
                    if (board[ni][nj] == 1) {
                        board[ni][nj] = number;
                        number_visited[ni][nj] = true;
                        dq.add(new int[]{ni, nj});
                        lands.add(new int[]{ni, nj});
                    }
                }
            }
        }
        number ++;
    }

    static int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    static boolean validate(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
