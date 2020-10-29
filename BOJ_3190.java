import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 29 
 * @author : "AoN"

 * @Description : BOJ 3190 - 뱀
 * @Link : https://www.acmicpc.net/problem/3190
 * 
 */

public class BOJ_3190 {
	
	static int N, K, L;
	
	static final int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};	// 상, 하, 좌, 우
	static final int left[] = {2, 3, 1, 0}, right[] = {3, 2, 0, 1};		// 왼쪽(좌우하상), 오른쪽(우좌상하)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	// 보드의 크기
		int map[][] = new int[N+1][N+1];
		
		K = Integer.parseInt(br.readLine());	// 사과의 개수
		for(int i=0; i<K; ++i) {				// 각 사과의 위치(r: 행, c: 열)
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
		}
		
		
		Deque<int[]> state = new LinkedList<>();
		L = Integer.parseInt(br.readLine());	// 뱀의 방향 변환 횟수
		for(int i=0; i<L; ++i) {				// x초 후 c방향으로 회전
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int c = st.nextToken().charAt(0) == 'L' ? 1 : 0;	// L 왼족, D 오른쪽

			state.add(new int[] {x, c});
		}
		
		Deque<Snake> snakeBody = new LinkedList<>();
		
		snakeBody.add(new Snake(1, 1));				// 뱀 몸통
		map[1][1] = 2;
		
		int[] cmd = state.pollFirst();
		int snakeX = 1, snakeY = 1, d = 3;					// 뱀 머리 위치, 방향
		
		// 'Dummy' game
		int playTime = 0;
		while(true) {
			++playTime;
			snakeX += dx[d]; snakeY += dy[d];
			
			if(1 > snakeX || snakeX > N || 1 > snakeY || snakeY > N) break;
			if(map[snakeX][snakeY] == 2) break;
			
			if(map[snakeX][snakeY] == 0) {
				Snake tail = snakeBody.pollFirst();
				map[tail.x][tail.y] = 0;
			}
			
			snakeBody.add(new Snake(snakeX, snakeY));
			map[snakeX][snakeY] = 2;
			
			if(playTime == cmd[0]) {
				d = cmd[1]==0 ? right[d] : left[d];
				cmd = !state.isEmpty() ? state.pollFirst() : cmd;
			}
		}
		
		System.out.println(playTime);
		br.close();
	}
	
	static class Snake {
		int x, y;
		
		public Snake(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}