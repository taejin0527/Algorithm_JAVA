package d0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 2 
 * @author : "AoN"

 * @Description : BOJ 17144 - 미세먼지 안녕!
 * @Link : https://www.acmicpc.net/problem/17144
 * 
 * 흔히 BFS 탐색할 때 큐를 사용해서 시간을 줄일 수 있는데
 * 미세먼지가 확산되고 공기청정기에 의해 순환되면서 큐에 입력된 좌표값 또한 변해야한다
 * 즉, 매 시뮬레이션 마다 전체를 탐색해야하기 때문에 굳이 큐를 사용하지 않아도 될 것 같다
 * 
 */

public class BOJ_17144 {
	static int R, C, T, airCleaner;
	static int[][] room;
	static Queue<int[]> q = new LinkedList<>();
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/**
		 * 방 정보를 순서대로 입력받는다
		 */
		R = Integer.parseInt(st.nextToken());							// 격자판 행(row)의 크기
		C = Integer.parseInt(st.nextToken());							// 격자판 열(col)의 크기
		T = Integer.parseInt(st.nextToken());							// 시간(time)
		
		room = new int[R][C];											// 방의 정보를 저장할 배열 공간 확보
		for(int i=0; i<R; ++i) {
			st = new StringTokenizer(br.readLine());					// 공백을 기준으로 한 행을 읽어서
			for(int j=0; j<C; ++j) {
				room[i][j] = Integer.parseInt(st.nextToken());			// 방 정보를 입력 받는다
				if(room[i][0] == -1) airCleaner = i;					// 공기청정기는 항상 1열에 존재하고, 해당 위치를 기억해둔다
				if(room[i][j] > 0) q.offer(new int[] {i, j});			// 미세먼지가 존재하면 큐에 저장한다
			}
		}
		
		/**
		 * 시뮬레이션은 크게 2가지 순서로 진행된다
		 * (반복) T 시간 동안
		 * 1. 미세먼지 확산(인접한 4방향으로 빈칸이 있다면 확산)
		 * 2. 공기청정기 작동 (위쪽은 반시계방향, 아래쪽은 시계방향 순환)
		 */
		
		while(T-- > 0) {
			diffuse();
			windCycle();
		}
		
		int sum=0;
		for(int[] row : room) {
			for(int col : row) {
				if(col > 0) sum += col;
			}
		}
		
		System.out.println(sum);									
		
		br.close();
	}
	
	static void diffuse() {
		/**
		 * 현재 방 정보가 저장된 room 배열에서 미세먼지 확산 정보를 online으로 수정하게 되면 안된다!!
		 * bfs를 통해 한 칸씩 정보를 수정하다보니 다음 칸의 남은 미세먼지 양이 이전 칸의 확산에 영향을 받기 때문에
		 * 가상의 방을 하나 더 만들고 이곳에는 확산되는 미세먼지 정보를 담고(더하기만 한다)
		 * 현재의 방에는 확산되고 남은 미세먼지 정보만 담아둔다(빼기만 함)
		 * 마지막에 가상의 방에 저장된 미세먼지 정보를 현재 방에 업데이트 해줘야한다
		 */
		int[][] nextRoom = new int[R][C];
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int dust = room[cur[0]][cur[1]] / 5;						// 확산되는 양은 A<r,c>/5
			
			for(int i=0; i<4; ++i) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(0 > nx || nx >= R || 0 > ny || ny >= C) continue;
				if(room[nx][ny] == -1) continue;
				nextRoom[nx][ny] += dust;
				room[cur[0]][cur[1]] -= dust;
			}
		}
		
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				room[i][j] += nextRoom[i][j];
			}
		}
	}
	
	static void windCycle() {
		int upperAC = airCleaner - 1;
		int lowerAC = airCleaner;
		
		// 위쪽 공기청정기(반시계방향 순환)
		for(int row=upperAC-1; row>0; --row) {						// 좌측(1열) 아래로 순환	
			room[row][0] = room[row-1][0];
		}
		for(int col=0; col<C-1; ++col) {							// 상단(1행) 왼쪽으로 순환	
			room[0][col] = room[0][col+1];
		}
		for(int row=0; row<upperAC; ++row) {						// 우측(C열) 위로 순환	
			room[row][C-1] = room[row+1][C-1];
		}
		for(int col=C-1; col>1; --col) {							// 하단(공기청정기 있는 행) 오른쪽으로 순환	
			room[upperAC][col] = room[upperAC][col-1];
		}
		room[upperAC][1] = 0;										// 공기 청정기에 의해 정화된 공기가 나감
		
		// 아래쪽 공기청정기(시계방향 순환)
		for(int row=lowerAC+1; row<R-1; ++row) {					// 좌측(1열) 위로 순환	
			room[row][0] = room[row+1][0];
		}
		for(int col=0; col<C-1; ++col) {							// 하단(R행) 왼쪽으로 순환	
			room[R-1][col] = room[R-1][col+1];
		}
		for(int row=R-1; row>lowerAC; --row) {						// 우측(C열) 아래로 순환	
			room[row][C-1] = room[row-1][C-1];
		}
		for(int col=C-1; col>1; --col) {							// 상단(공기청정기 있는 행) 오른쪽으로 순환	
			room[lowerAC][col] = room[lowerAC][col-1];
		}
		room[lowerAC][1] = 0;										// 공기 청정기에 의해 정화된 공기가 나감
		
		// 큐에 현재 미세먼지의 좌표를 입력한다
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				if(room[i][j] >= 5) q.offer(new int[] {i, j});	
			}
		}
	}
}