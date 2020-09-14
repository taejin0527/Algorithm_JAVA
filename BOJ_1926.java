package d0913;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 13 
 * @author : "AoN"

 * @Description : BOJ 1926 - 그림
 * @Link : https://www.acmicpc.net/problem/1926
 * 
 */

public class BOJ_1926 {
	static int N, M;
	static int map[][];
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		for(int i=0; i<N; ++i) for(int j=0; j<M; ++j) map[i][j] = sc.nextInt();
		
		int cnt = 0, size = 0, maxSize = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j] == 1) {
					++cnt;
					
					map[i][j] = 0;
					size = dfs(i, j , 1);
					maxSize = Math.max(maxSize, size);
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(maxSize);
		sc.close();
	}
	
	static int dfs(int x, int y, int size) {
		
		for(int i=0; i<4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(0 > nx || nx > N-1 || 0 > ny || ny > M-1) continue;
			if(map[nx][ny] == 0) continue;
			map[nx][ny] = 0;
			size = dfs(nx, ny, ++size);
		}
		return size;
	}
}
