import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @FileName : BOJ_13460.java
 * @Project : 0825
 * @Date : 2020. 8. 25 
 * @author : "AoN"

 * @Description : 구슬 탈출 2
 * @Link : https://www.acmicpc.net/problem/13460
 * 
 */

public class BOJ_13460 {
	
	private int N, M;
	private StringTokenizer st;
	private char[][] board;
	private boolean[][][][] visited;
	private Ball redBall, blueBall;
	private int[] dx = {1, -1, 0, 0};
	private int[] dy = {0, 0, 1, -1};
	
	
	public BOJ_13460() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		/**
		 * '.'은 빈 칸을 의미
		 * '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미
		 * 'O'는 구멍의 위치를 의미
		 * 'R'은 빨간 구슬의 위치
		 * 'B'는 파란 구슬의 위치
		 */
		for(int i=0; i<N; ++i) {
			String row = br.readLine();
			for(int j=0; j<M; ++j) {
				board[i][j] = row.charAt(j);
				if(board[i][j] == 'R') {
					redBall = new Ball(i, j, 0);
				}
				if(board[i][j] == 'B') {
					blueBall = new Ball(i, j, 0);
				}
			}
		}
		
		BFS();
	
		br.close();
	}
	
	private void BFS() {
		Queue<qBall> q = new LinkedList<>();
		
		q.offer(new qBall(redBall.x, redBall.y, blueBall.x, blueBall.y, 1));
		visited[redBall.x][redBall.y][blueBall.x][blueBall.y] = true;
		
		while(!q.isEmpty()) {
			qBall cur = q.poll();
			
			// 만약, 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력
			if(cur.depth > 10) break;
			
			for(int i=0; i<4; ++i) {
				Ball nextRed = move(cur.redX, cur.redY, dx[i], dy[i]);
				Ball nextBlue = move(cur.blueX, cur.blueY, dx[i], dy[i]);

				/**
				 * 종료 조건이 조금 까다롭다...
				 */
				// 파란 구슬이 빠지면 안되므로 continue로 다음 방법을 찾아본다
				if(board[nextBlue.x][nextBlue.y] == 'O') continue;
				if(board[nextRed.x][nextRed.y] == 'O') {
					System.out.println(cur.depth);
					return;
				}
				
				// 만약 기울기 이후 구슬이 같은 위치에 있다면 한 칸 뒤로 되돌린다
				if(nextRed.x == nextBlue.x && nextRed.y == nextBlue.y) {
					if(nextRed.moved > nextBlue.moved) {
						nextRed.x -= dx[i];
						nextRed.y -= dy[i];
					}else {
						nextBlue.x -= dx[i];
						nextBlue.y -= dy[i];
					}
				}
				
				// 새로운 형태의 보드 상태라면 큐에 추가한다
				if(!visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y]) {
					visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y] = true;
					q.offer(new qBall(nextRed.x, nextRed.y, nextBlue.x, nextBlue.y, cur.depth+1));
				}	
			}//for
		}//while
		System.out.println(-1);
	}
	
	private Ball move(int x, int y, int dx, int dy) {
		int moved = 0;
		while(board[x+dx][y+dy] != '#'  && board[x][y] != 'O') {
			x += dx;
			y += dy;
			++moved;
		}
		
		return new Ball(x, y, moved);
	}
	
	public static void main(String[] args) throws IOException {
		new BOJ_13460();
	}
}

class Ball {
	int x, y, moved;

	public Ball(int x, int y, int moved) {
		this.x = x;
		this.y = y;
		this.moved = moved;
	}
}

class qBall {
	int redX, redY; 
	int blueX, blueY;
	int depth;
	
	public qBall(int redX, int redY, int blueX, int blueY, int depth) {
		this.redX = redX;
		this.redY = redY;
		this.blueX = blueX;
		this.blueY = blueY;
		this.depth = depth;
	}
}
