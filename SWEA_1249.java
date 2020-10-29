import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 10. 29 
 * @author : "AoN"

 * @Description : 1249. [S/W 문제해결 응용] 4일차 - 보급로
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD&categoryId=AV15QRX6APsCFAYD&categoryType=CODE
 * 
 */

public class SWEA_1249 {
	
	static int N, map[][];
	
	static final int dx[] = {-1, 1, 0 ,0}, dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder out = new StringBuilder();
		for(int tc=1; tc<=T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for(int i=0; i<N; ++i) {
				String row = br.readLine();
				for(int j=0; j<N; ++j) {
					map[i][j] = row.charAt(j) - '0';
				}
			}
			
			out.append('#').append(tc).append(' ').append(BFS()).append("\n");
		}
		
		System.out.println(out.toString());
		br.close();
	}
	
	static int BFS() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean v[][] = new boolean[N][N];
		
		pq.offer(new Point(0, 0, 0));
		v[0][0] = true;
		
		while(!pq.isEmpty()) {
			Point cur = pq.poll();
			
			if(cur.x == N-1 && cur.y == N-1) return cur.d;
			
			for(int i=0; i<4; ++i) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(0 > nx || nx >= N || 0 > ny || ny >= N) continue;
				if(v[nx][ny]) continue;
				
				v[nx][ny] = true;
				pq.offer(new Point(nx, ny, cur.d+map[nx][ny]));
			}
		}
		return -1;
	}
	
	static class Point implements Comparable<Point> {
		int x, y, d;
		
		public Point(int x, int y, int d) {
			this.x = x; this.y = y; this.d = d;
		}

		@Override
		public int compareTo(Point o) {
			return this.d - o.d;
		}
	}
}
