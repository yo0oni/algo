import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static int game = 1;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static class Node implements Comparable<Node> {
        int i;
        int j;
        int coin;

        public Node(int i, int j, int coin) {
            this.i = i;
            this.j = j;
            this.coin = coin;
        }

        @Override
        public int compareTo(Node o) {
            return this.coin - o.coin;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            N = Integer.parseInt(br.readLine());

            if(N == 0) break;

            board = new int[N][N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(0,0,board[0][0]));
            int[][] minCoin =  new int[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    minCoin[i][j] = Integer.MAX_VALUE;
                }
            }
            minCoin[0][0] = board[0][0];

            while(!pq.isEmpty()) {
                Node current = pq.poll();
                int ci = current.i;
                int cj = current.j;
                int coin = current.coin;

                if(ci == N-1 && cj == N-1) {
                    sb.append("Problem ").append(game).append(": ").append(coin).append("\n");
                    game++;
                    break;
                }

                for(int d = 0; d < 4; d ++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];

                    if(validate(ni, nj)) {
                        int nextCoin = coin + board[ni][nj];

                        if(minCoin[ni][nj] > nextCoin) {
                            pq.offer(new Node(ni,nj,nextCoin));
                            minCoin[ni][nj] = nextCoin;
                        }
                    }
                }
            }
        }
        System.out.println(sb);
    }

    public static boolean validate(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}