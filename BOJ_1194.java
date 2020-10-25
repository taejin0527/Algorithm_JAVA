import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 25 
 * @author : "AoN"

 * @Description : BOJ 1194 - 달이 차오른다, 가자.
 * @Link : https://www.acmicpc.net/problem/1194
 * 
 */

public class BOJ_1194 {
	
	static int N, M;
	static char map[][];
	
	static final int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		map = new char[N][M];
		int sx=0, sy=0;
		for(int i=0; i<N; ++i) {
			String row = br.readLine();
			
			for(int j=0; j<M; ++j) {
				map[i][j] = row.charAt(j);
				if(map[i][j] == '0') {
					sx = i; sy = j;
				}
			}
		}
		
		System.out.println(mazeRun(sx, sy));
		
		br.close();
	}
	
	static int mazeRun(int sx, int sy) {
		Queue<Point> q = new LinkedList<>();
		boolean v[][][] = new boolean[N][M][1<<6];
		
		q.offer(new Point(sx, sy, 0));
		v[sx][sy][0] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int t=0; t < qSize; ++t) {
				Point cur = q.poll();
				
				//System.out.println(cnt + ": " + cur.x + " " + cur.y);
				if(map[cur.x][cur.y] == '1') return cnt;
				
				for(int i=0; i<4; ++i) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					int key = cur.key;
					
					if(0 > nx || nx >= N || 0 > ny || ny >= M) continue;
					if(map[nx][ny] == '#') continue;
					
					if('a' <= map[nx][ny] && map[nx][ny] <= 'f') {
						key |= (1<<(map[nx][ny]-'a'));
					}
					
					if('A' <= map[nx][ny] && map[nx][ny] <= 'F') {
						if((key & (1<<(map[nx][ny]-'A'))) == 0) {
							continue;
						}
					}
					
					if(v[nx][ny][key]) continue;
					
					v[nx][ny][key] = true;
					q.offer(new Point(nx, ny, key));
				}
			}
			cnt++;
		}
		
		return -1;
	}
	
	static class Point {
		int x, y, key;
		
		public Point(int x, int y, int key) {
			this.x = x; this.y = y; this.key = key;
		}
	}
}
