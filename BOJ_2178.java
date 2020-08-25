import java.util.Arrays;
import java.util.Scanner;

/**
 * @FileName : BOJ_2178.java
 * @Project : 0825
 * @Date : 2020. 8. 25 
 * @author : "AoN"

 * @Description :
 * @Link : https://www.acmicpc.net/problem/2178
 * 
 */

public class BOJ_2178 {
	
	private int N, M, minDist = Integer.MAX_VALUE;
	private int[][] map, visited;
	private int[] dx = {1, -1, 0, 0};
	private int[] dy = {0, 0, 1, -1};
	
	public BOJ_2178() {
		Scanner sc = new Scanner(System.in);
		
		// 미로의 크기 행 N, 열 M
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N+1][M+1];
		visited = new int[N+1][M+1];
		
		// 최대 이동거리는 배열의 크기가 된다(Integer.MAX_VALUE를 사용해도 무방)
		int maxDist = N*M+1;
		// 방문체크와 최소이동거리 계산에 사용되는 visited 배열에 할당한다
		for (int[] row: visited)
		    Arrays.fill(row, maxDist);
		

		for(int i=1; i<=N; ++i) {
			String row = sc.next();
			for(int j=1; j<=M; ++j) {
				map[i][j] = row.charAt(j-1) - '0';
			}
		}
		
		DFS(1, 1, 1);
		
		System.out.println(minDist);
		
		sc.close();
	}
	
	private void DFS(int x, int y, int dist) {
		// (기저 사례) 목적지인 N,M에 도달하면 반환
		if(x == N && y == M) {
			minDist = (minDist < dist) ? minDist : dist;
			return;
		}
		
		// 1. 현재 위치 좌표에 이동 거리를 표시한다
		visited[x][y] = dist;
		// 2. 4방 탐색을 하며 조건이 맞으면 재귀호출을 한다
		for(int i=0; i<4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 2-1. 범위를 벗어나거나 길이 아니면 (0) 스킵
			if(0 < nx && nx <= N  && 0 < ny && ny <= M && map[nx][ny] == 1) { 
			
				// 2-2. 다음 위치로 가기 전에 해당 위치에 저장된 이동거리 값과 비교해서
				//      현재 위치의 이동거리 값+1 이 더 작으면 이동한다 (불필요한 재귀를 줄이기 위해)
				if(visited[nx][ny] > dist + 1) DFS(nx, ny, dist+1);
			}
		}
	}
	
	public static void main(String[] args) {
		new BOJ_2178();
	}
}
