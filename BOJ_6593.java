import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 21 
 * @author : "AoN"

 * @Description : BOJ 6593 - 상범 빌딩
 * @Link : https://www.acmicpc.net/problem/6593
 * 
 */

public class BOJ_6593 {
	
	static int L, R, C;
	static char building[][][];
	static boolean v[][][];
	
	static Point s, e;
	
	// 동 서 남 북 상 하
	static final int dx[] = {0, 0, 0, 0, 1, -1};
	static final int dy[] = {0, 0, 1, -1, 0, 0};
	static final int dz[] = {1, -1, 0, 0, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			String[] r = br.readLine().split(" ");
			L = Integer.parseInt(r[0]);
			R = Integer.parseInt(r[1]);
			C = Integer.parseInt(r[2]);
			
			if(L==0 && R==0 && C==0) break;
			
			building = new char[L][R][C];
			for(int i=0; i<L; ++i) {
				for(int j=0; j<R; ++j) {
					String row = br.readLine(); 
					for(int k=0; k<C; ++k) {
						building[i][j][k] = row.charAt(k);
						
						if(building[i][j][k] == 'S') {
							s = new Point(i, j, k, 0);
						}
						if(building[i][j][k] == 'E') {
							e = new Point(i, j, k, 0);
						}
					} // end C
				} // end R
				br.readLine(); // 개행 처리
			} // end L
			
			int ans = bfs();
			System.out.println(ans > 0 ? "Escaped in " + ans + " minute(s)." : "Trapped!");
		} // end while
		
		br.close();
	}
	
	static int bfs() {
		Queue<Point> q = new LinkedList<>();
		v = new boolean[L][R][C];
		
		q.offer(s);
		v[s.x][s.y][s.z] = true;
		
		int t = -1;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.x == e.x && cur.y == e.y && cur.z == e.z) {
				t = cur.t;
				break;
			}
			
			for(int i=0; i<6; ++i) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				int nz = cur.z + dz[i];
				
				if(0 > nx || nx >= L || 0 > ny || ny >= R || 0 > nz || nz >= C) continue;
				if(building[nx][ny][nz] == '#' || v[nx][ny][nz]) continue;
				
				v[nx][ny][nz] = true;
				q.offer(new Point(nx, ny, nz, cur.t+1));
			}
		}
		
		return t;
	}
	
	static class Point{
		int x, y, z, t;
		
		public Point(int x, int y, int z, int t) {
			this.x = x; this.y = y; this.z = z; this.t = t;
		}
	}
}
