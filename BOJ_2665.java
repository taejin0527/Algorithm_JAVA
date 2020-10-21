import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 20 
 * @author : "AoN"

 * @Description : BOJ 2665 - 미로만들기
 * @Link : https://www.acmicpc.net/problem/2665
 * 
 */

public class BOJ_2665 {
	
	static int n;
	static int room[][], v[][];
	static final int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		room = new int[n][n]; v = new int[n][n];
		
		for(int i=0; i<n; ++i) {
			String row = sc.next();
			
			for(int j=0; j<n; ++j) {
				room[i][j] = row.charAt(j) - '0';
			}
		}
		
		bfs();
		
		System.out.println(v[n-1][n-1]);
		sc.close();
	}
	
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		for(int i=0; i<n; ++i) Arrays.fill(v[i], Integer.MAX_VALUE);
		
		q.offer(new Point(0, 0));
		v[0][0] = 0;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int i=0; i<4; ++i) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(0>nx || nx>=n || 0>ny || ny>=n) continue;
				
				if(room[nx][ny] == 1) {	// 흰 방인 경우(갈 수 있다)
					if(v[nx][ny] > v[cur.x][cur.y]) {
						v[nx][ny] = v[cur.x][cur.y];
						q.offer(new Point(nx, ny));
					}
				}else if(room[nx][ny] == 0) {	// 검은 방인 경우(벽을 부숴야 한다)
					if(v[nx][ny] > v[cur.x][cur.y] + 1) {
						v[nx][ny] = v[cur.x][cur.y] + 1;
						q.offer(new Point(nx, ny));
					}
				}
			}
		}
	}
	
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}
