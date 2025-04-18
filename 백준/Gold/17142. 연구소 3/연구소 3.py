import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline


di = [0, 1, 0, -1]
dj = [1, 0, -1, 0]
                

def bfs(board, virus):
    dq = deque()
    time = 0
    visited = [[False] * n for _ in range(n)]
    
    for vi, vj in virus:
        dq.append([vi, vj, 0])
        visited[vi][vj] = True
        
    while dq:
        ci, cj, t = dq.popleft()
        
        for d in range(4):
            ni, nj = ci + di[d], cj + dj[d]

            if 0 <= ni < n and 0 <= nj < n and not visited[ni][nj]:
                if board[ni][nj] == 0:
                    dq.append((ni, nj, t+1))
                    time = max(time, t+1)
                    
                elif board[ni][nj] == "*":
                    dq.append((ni, nj, t+1))
                    
                visited[ni][nj] = True
                    
    for i in range(n):
        for j in range(n):
            if board[i][j] == 0 and not visited[i][j]:
                return 10000

    return time


n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
virus = []
for i in range(n):
    for j in range(n):
        if board[i][j] == 2: #바이러스
            virus.append((i, j))
            board[i][j] = '*'
        if board[i][j] == 1:
            board[i][j] = '-'

min_time = 10000
for v in combinations(virus, m):
    new_board = [arr[:] for arr in board]

    for vi, vj in v:
        new_board[vi][vj] = 0
    
    min_time = min(min_time, bfs(new_board, v))

if min_time == 10000:
    print(-1) 
else:
    print(min_time)