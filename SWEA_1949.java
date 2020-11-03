import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 11. 2 
 * @author : "AoN"

 * @Description : 1949. [모의 SW 역량테스트] 등산로 조성
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq&categoryId=AV5PoOKKAPIDFAUq&categoryType=CODE&&&#none
 * 
 */

public class SWEA_1949 {
	
	static int N, K, maxH, ans;
	static int map[][];
	static boolean v[][];
	static List<Pos> top;
	
	static final int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder out = new StringBuilder();
		for(int tc=1; tc<=T; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			maxH = 0;
			top = new ArrayList<>();
			map = new int[N][N];
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] > maxH) {
						maxH = map[i][j];
						top.clear();
						top.add(new Pos(i, j));
					}else if(map[i][j] == maxH) {
						top.add(new Pos(i, j));
					}
				}
			}
			
			ans = 0;
			v = new boolean[N][N];
			
			for(Pos p : top) {
				v[p.x][p.y] = true; 
				dfs(p, maxH, 1, false);
				v[p.x][p.y] = false; 
			}
			
			out.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(out);
		br.close();
	}
	
	private static void dfs(Pos cur, int height, int pathLen, boolean digged) {
		for(int i=0; i<4; ++i) {
			ans = Math.max(ans, pathLen);
			
			int nx = cur.x + dx[i];
			int ny = cur.y + dy[i];
			
			if(0>nx || nx>=N || 0>ny || ny>=N) continue;
			if(v[nx][ny]) continue;
			
			if(height > map[nx][ny]) {
				v[nx][ny] = true;
				dfs(new Pos(nx, ny), map[nx][ny], pathLen+1, digged);
				v[nx][ny] = false;
			} else {
				if(!digged && map[nx][ny] - height < K) {
					v[nx][ny] = true;
					dfs(new Pos(nx, ny), height-1, pathLen+1, true);
					v[nx][ny] = false;
				}
			}
		}
	}

	static class Pos{
		int x, y;
		public Pos(int x, int y) {
			this.x = x; this.y = y;
		}
		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
	}
}
