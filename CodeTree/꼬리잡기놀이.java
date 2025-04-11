import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.xml.transform.Templates;

public class Main {

	static int N, M, K;
	static int[][] board;
	static int[] score;
	static int[][] teamNumberBoard;
	static ArrayList<int[]>[] lines;
	static int totalScore = 0;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		teamNumberBoard = new int[N][N];
		score = new int[M + 5];
		lines = new ArrayList[M + 5];

		int direction = 0;

		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());

			for (int y = 0; y < N; y++) {
				board[x][y] = Integer.parseInt(st.nextToken());
			}
		}

		int number = 5;
		boolean[][] teamBoardVisited = new boolean[N][N];
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (!teamBoardVisited[x][y] && board[x][y] != 0) {
					teamNumberBoard[x][y] = number;
					initTeamNumberBoard(x, y, teamBoardVisited, number++);
				}
			}
		}

		number = 5;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (board[x][y] == 1) {
					int teamNum = teamNumberBoard[x][y];
					lines[teamNum] = initLine(teamNum, x, y);
				}
			}
		}

		int index = 0;
		for (int k = 1; k < K + 1; k++) {

			for (int m = 5; m < M + 5; m++) {
				move(lines[m], m);
			}

			int sequence = 0;
			int teamNumber = 0;
			ArrayList<int[]> line = null;

			if (direction == 0) {
				for (int y = 0; y < N; y++) {
					if (board[index][y] >= 1 && board[index][y] <= 3) {
						teamNumber = teamNumberBoard[index][y];
						line = lines[teamNumber];
						sequence = findSequence(line, index, y);
						Collections.reverse(line);
						changeHeadAndTail(line);
						break;
					}
				}
				index++;
			} else if (direction == 1) {
				for (int x = N - 1; x >= 0; x--) {
					if (board[x][index] >= 1 && board[x][index] <= 3) {
						teamNumber = teamNumberBoard[x][index];
						line = lines[teamNumber];
						sequence = findSequence(line, x, index);
						Collections.reverse(line);
						changeHeadAndTail(line);
						break;
					}
				}
				index++;
			} else if (direction == 2) {
				for (int y = N - 1; y >= 0; y--) {
					if (board[index][y] >= 1 && board[index][y] <= 3) {
						teamNumber = teamNumberBoard[index][y];
						line = lines[teamNumber];
						sequence = findSequence(line, index, y);
						Collections.reverse(line);
						changeHeadAndTail(line);
						break;
					}
				}
				index--;
			} else if (direction == 3) {
				for (int x = 0; x < N; x++) {
					if (board[x][index] >= 1 && board[x][index] <= 3) {
						teamNumber = teamNumberBoard[x][index];
						line = lines[teamNumber];
						sequence = findSequence(line, x, index);
						Collections.reverse(line);
						changeHeadAndTail(line);
						break;
					}
				}
				index--;
			}

			if (teamNumber != 0) {
				score[teamNumber] += sequence * sequence;
			}
			if ((k % N) == 0) {
				direction = (direction + 1) % 4;

				if (direction == 0) {
					index = 0;
				} else if (direction == 1) {
					index = 0;
				} else if (direction == 2) {
					index = N - 1;
				} else if (direction == 3) {
					index = N - 1;
				}
			}
		}

		for (int m = 5; m < M + 5; m++) {
			totalScore += score[m];
		}
		System.out.println(totalScore);

	}

	private static void changeHeadAndTail(ArrayList<int[]> line) {
		int[] head = line.get(0);
		int[] tail = line.get(line.size() - 1);

		board[head[0]][head[1]] = 1;
		board[tail[0]][tail[1]] = 3;
	}

	private static int findSequence(ArrayList<int[]> line, int x, int y) {
		for (int sq = 0; sq < line.size(); sq++) {
			int[] current = line.get(sq);

			if (current[0] == x && current[1] == y) {
				return sq + 1;
			}
		}
		return -1;
	}

	private static void initTeamNumberBoard(int x, int y, boolean[][] visited, int number) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { x, y });
		visited[x][y] = true;
		teamNumberBoard[x][y] = number;

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int cx = current[0];
			int cy = current[1];

			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if (validate(nx, ny) && !visited[nx][ny]) {
					if (board[nx][ny] != 0) {
						dq.add(new int[] { nx, ny });
						visited[nx][ny] = true;
						teamNumberBoard[nx][ny] = number;
					}
				}
			}

		}
	}

	private static void move(ArrayList<int[]> line, int teamNumber) {
		int[] tail = line.remove(line.size() - 1);
		int tx = tail[0];
		int ty = tail[1];
		int sx = 0;
		int sy = 0;
		board[tx][ty] = 4;

		for (int[] current : line) {
			int cx = current[0];
			int cy = current[1];

			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if (validate(nx, ny)) {
					if (board[cx][cy] == 1 && board[nx][ny] == 4) {
						board[cx][cy] = teamNumber;
						board[nx][ny] = 1;
						sx = nx;
						sy = ny;
						break;
					} else if (board[cx][cy] == 2 && board[nx][ny] == teamNumber) {
						board[cx][cy] = teamNumber;
						board[nx][ny] = 2;
						break;
					}
				}
			}
		}

		for (int d = 0; d < 4; d++) {
			int nx = tx + dx[d];
			int ny = ty + dy[d];

			if (validate(nx, ny) && board[nx][ny] == teamNumber) {
				board[nx][ny] = 3;
				break;
			}
		}

		lines[teamNumber] = initLine(teamNumber, sx, sy);
	}

	private static ArrayList<int[]> initLine(int number, int sx, int sy) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		ArrayList<int[]> line = new ArrayList<>();
		dq.add(new int[] { sx, sy });
		line.add(new int[] { sx, sy });

		boolean[][] visited = new boolean[N][N];
		visited[sx][sy] = true;

		while (!dq.isEmpty()) {
			int[] current = dq.poll();
			int cx = current[0];
			int cy = current[1];

			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if (validate(nx, ny) && !visited[nx][ny]) {
					if (board[cx][cy] != 1 && board[nx][ny] == 3) {
						line.add(new int[] { nx, ny });
						return line;
					}
					if (board[cx][cy] == board[nx][ny] || board[cx][cy] + 1 == board[nx][ny]) {
						line.add(new int[] { nx, ny });
						dq.add(new int[] { nx, ny });
						visited[nx][ny] = true;
					}
				}
			}
		}

		return null;
	}

	private static boolean validate(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < N;
	}

}
