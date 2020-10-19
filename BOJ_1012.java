import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 14 
 * @author : "AoN"

 * @Description : BOJ 1012 - 유기농 배추
 * @Link : https://www.acmicpc.net/problem/1012
 * 
 */

public class BOJ_1012 {
	
	static int M, N, K;
	static int land[][];
	static boolean v[][];
	
	static final int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc=0; tc<T; ++tc) {
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			
			land = new int[M][N];
			for(int i=0; i<K; ++i) {
				land[sc.nextInt()][sc.nextInt()] = 1;
			}
			
			int cnt = 0;
			v = new boolean[M][N];
			for(int x=0; x<M; ++x) {
				for(int y=0; y<N; ++y) {
					if(land[x][y] == 1 && !v[x][y]) {
						v[x][y] = true;
						cnt += dfs(x, y);
					}
				}


			}
			
			System.out.println(cnt);
		}
		sc.close();
	}
	
	static int dfs(int x, int y) {
		for(int i=0; i<4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(0 > nx || nx >= M || 0 > ny || ny >= N) continue;
			if(v[nx][ny] || land[nx][ny] == 0) continue;
			
			v[nx][ny] = true;
			dfs(nx, ny);
		}
		
		return 1;
	}
}
