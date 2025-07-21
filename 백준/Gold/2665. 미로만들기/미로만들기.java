import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] board;

    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static class Node implements Comparable<Node> {
        int i;
        int j;
        int deleted;

        public Node(int i, int j, int deleted) {
            this.i = i;
            this.j = j;
            this.deleted = deleted;
        }

        @Override
        public int compareTo(Node o) {
            return this.deleted - o.deleted;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i = 0; i < N; i++) {
            char[] str = br.readLine().toCharArray();

            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(str[j]));
            }
        }

        PriorityQueue<Node> pq = new  PriorityQueue<>();
        pq.add(new Node(0, 0, 0));
        int[][] countDeleted = new int[N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(countDeleted[i], Integer.MAX_VALUE);
        }
        countDeleted[0][0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int ci = current.i;
            int cj = current.j;
            int cd = current.deleted;

            if(ci == N-1 && cj == N-1) {
                System.out.println(cd);
                return;
            }

            for(int d = 0; d < 4; d ++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(validate(ni, nj)) {
                    if(board[ni][nj] == 0) {
                        if(countDeleted[ni][nj] > cd + 1) {
                            pq.offer(new Node(ni, nj, cd + 1));
                            countDeleted[ni][nj] = cd + 1;
                        }
                    } else {
                        if(countDeleted[ni][nj] > cd) {
                            pq.offer(new Node(ni, nj, cd));
                            countDeleted[ni][nj] = cd;
                        }
                    }
                }
            }
        }
    }

    public static boolean validate(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}