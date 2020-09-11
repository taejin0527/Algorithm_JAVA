package d0911;

import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 11 
 * @author : "AoN"

 * @Description : BOJ 1743 - 음식물 피하기
 * @Link : https://www.acmicpc.net/problem/1743
 * 
 */

public class BOJ_1743 {

	static int N, M, K, ans = 0;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		map = new int[N][M];
		for(int i=0; i<K; ++i) {
			map[sc.nextInt()-1][sc.nextInt()-1] = 1;
		}
		
		findMaxWaste();
		
		System.out.println(ans);
		sc.close();
	}

	static void findMaxWaste() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j] == 1) {
					BFS(i, j);
				}
			}
		}
	}
	
	static void BFS(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		map[x][y] = 0;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			++cnt;
			
			for(int i=0; i<4; ++i) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(0 > nx || nx > N-1 || 0 > ny || ny > M-1) continue;
				if(map[nx][ny] == 0) continue;
				
				q.offer(new int[] {nx, ny});
				map[nx][ny] = 0;
			}
		}
		
		ans = Math.max(ans,  cnt);
	}
}
