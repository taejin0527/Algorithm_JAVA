import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 8. 26 
 * @author : "AoN"

 * @Description : BOJ_2206 - 벽 부수고 이동하기
 * @Link : https://www.acmicpc.net/problem/2206
 * 
 */

public class BOJ_2206 {

	private int N, M;
	private int[][] map;
	private int[][][] visited;
	private int[] dx = {1, -1, 0, 0};
	private int[] dy = {0, 0, 1, -1};
	
	private void Solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		visited = new int[N+1][M+1][2];

		
		for(int i=1; i<=N; ++i) {
			String row = br.readLine();
			for(int j=1; j<=M; ++j) {
				map[i][j] = row.charAt(j-1) - '0';
			}
		}
		
		System.out.println(BFS());
		
		br.close();
	}
	
	private int BFS() {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(1,1,0));		// (1,1)에서 시작해서 아직 벽을 부수지 않음(0)
		visited[1][1][0] = 1;			// 방문체크
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if (cur.x == N && cur.y == M)	// 도착지점(N,M)에 도착하면 이동거리를 리턴한다
				return visited[cur.x][cur.y][cur.d];
			
			for(int i=0; i<4; ++i) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 다음 방문지점이 범위 안에 있고 방문한  적이 없는 경우
				if(0 < nx && nx <= N && 0 < ny && ny <= M && visited[nx][ny][cur.d] == 0) {
					// 갈 수 있는 길이라면 이동거리(visited)를 1 증가하고 해당 좌표는 큐에 추가한다
					if(map[nx][ny] == 0 ) {
						visited[nx][ny][cur.d]= visited[cur.x][cur.y][cur.d]+ 1;  
						q.offer(new Point(nx,ny,cur.d));
					}
					// 벽(1)으로 막혀있는데 한번도 부순적이 없다면 d를 0에서 1로 바꿔서 큐에 추가한다
					if(map[nx][ny] == 1 && cur.d == 0) {
						visited[nx][ny][1]= visited[cur.x][cur.y][cur.d]+ 1;  
						q.offer(new Point(nx,ny,1));
					}
				}
			}
		}
		
		// 도착지점(N,M)에 도착하지 못하면 -1을 리턴한다
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		new BOJ_2206().Solution();
	}
}

class Point {
	int x, y, d;
	
	public Point(int x, int y, int d) {
		this.x=x; this.y=y; this.d=d;
	}
}