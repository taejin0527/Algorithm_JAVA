import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 23 
 * @author : "AoN"

 * @Description : BOJ 2573 - 빙산
 * @Link : https://www.acmicpc.net/problem/2573
 * 
 */

public class BOJ_2573 {
	
	static int N, M, cnt, year;
	static int iceberg[][], temp[][];
	static boolean flag;
	static boolean v[][];
	
	static final int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = nm[0];
		M = nm[1];
		
		iceberg = new int[N][M];
		for(int i=0; i<N; ++i) {
			int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			for(int j=0; j<M; ++j) {
				iceberg[i][j] = row[j];
			}
		}
		
		while(true) {
			// 0. (base) 빙산이 모두 녹거나
			if(flag) break;
			// (base) 두 덩어리 이상으로 분리되면 종료
			if(cnt >= 2) break;
			
			// 1. 빙산이 녹음
			yearAfter();
			// 2. 덩어리 카운트
			countIceberg();
		}
		
		System.out.println(flag ? 0 : year);
		br.close();
	}
	
	static void yearAfter() {
		temp = new int[N][M];
		flag = true;
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(iceberg[i][j] == 0) continue;
				flag = false;
				int t = 0;
				
				for(int k=0; k<4; ++k) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if(0>nx || nx>=N || 0>ny || ny>=M) continue;
					if(iceberg[nx][ny]>0) continue;
					++t;
				}
				
				temp[i][j] = iceberg[i][j] - t;
				if(temp[i][j] < 0) temp[i][j] = 0;
			}
		}
		
		iceberg = Arrays.stream(temp).map(int[]::clone).toArray(int[][]::new);
		++year;
	}
	
	static void countIceberg() {
		v = new boolean[N][M];
		
		int t=0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(iceberg[i][j] == 0 || v[i][j]) continue;
				t += bfs(i, j);
			}
		}
		cnt = t;
	}
	
	static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int cx = q.peek()[0];
			int cy = q.poll()[1];
			
			for(int i=0; i<4; ++i) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(0>nx || nx>=N || 0>ny || ny>=M) continue;
				if(iceberg[nx][ny]==0 || v[nx][ny]) continue;
				v[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
		
		return 1;
	}
}
