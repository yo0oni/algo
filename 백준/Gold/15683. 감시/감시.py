import sys
input = sys.stdin.readline

di = [-1, 0, 1, 0]  # 상, 우, 하, 좌
dj = [0, 1, 0, -1]

# CCTV 별 가능한 방향 세트 (회전 포함)
cctv_dirs = [
    [],                # 0 (사용X)
    [[0], [1], [2], [3]],              # 1번: 4방향 중 1개 선택
    [[0, 2], [1, 3]],                  # 2번: 상하 or 좌우
    [[0, 1], [1, 2], [2, 3], [3, 0]],  # 3번: 직각 방향
    [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]],  # 4번: 3개 방향
    [[0, 1, 2, 3]]                      # 5번: 모든 방향
]

def watch(temp_board, si, sj, directions):
    """ 특정 CCTV가 감시하는 영역을 '#'로 마킹 """
    for d in directions:
        ci, cj = si, sj
        while True:
            ci, cj = ci + di[d], cj + dj[d]
            if not (0 <= ci < n and 0 <= cj < m) or temp_board[ci][cj] == 6:
                break  # 범위 초과 or 벽(6) 만나면 종료
            if temp_board[ci][cj] == 0:  
                temp_board[ci][cj] = '#'  # 감시 구역 표시


def dfs(depth, board):
    """ 모든 CCTV에 대한 회전 조합을 DFS로 탐색 """
    global answer
    
    if depth == len(cctvs):  # 모든 CCTV 배치 완료
        # 사각지대 카운트
        cnt = sum(row.count(0) for row in board)
        answer = min(answer, cnt)
        return

    temp_board = [row[:] for row in board]  # 원본 보드 복사 (백트래킹)

    si, sj, cctv_type = cctvs[depth]
    
    for directions in cctv_dirs[cctv_type]:  # 가능한 모든 방향 탐색
        watch(temp_board, si, sj, directions)  # CCTV 작동 시뮬레이션
        dfs(depth + 1, temp_board)  # 다음 CCTV 탐색
        temp_board = [row[:] for row in board]  # 원본 복구 (백트래킹)


# 입력 받기
n, m = map(int, input().split())
board = []
cctvs = []
answer = n * m  # 최악의 경우 (모두 사각지대)

for i in range(n):
    row = list(map(int, input().split()))
    for j in range(m):
        if 1 <= row[j] <= 5:
            cctvs.append((i, j, row[j]))  # CCTV 위치 저장
    board.append(row)

dfs(0, board)  # DFS 탐색 시작
print(answer)
