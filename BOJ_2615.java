import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 8. 26 
 * @author : "AoN"

 * @Description : JUNGOL_1733 - 오목 (BOJ 2615)
 * @Link : http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1006&sca=99&sfl=wr_hit&stx=1733
 * 
 */

public class BOJ_2615 {
	
	private int[][] board = new int[20][20];
	private boolean[][][] visited = new boolean[20][20][4];
	private int[] dx = {1, 0, 1, -1};
	private int[] dy = {0, 1, 1, 1};
	
	private void Solution() {
		Scanner sc = new Scanner(System.in);
		boolean gameSet = false;
		
		for(int i=1; i<20; ++i) {
			for(int j=1; j<20; ++j) {
				board[i][j] = sc.nextInt();
			}
		}
		
		for(int i=1; i<20; ++i) {
			for(int j=1; j<20; ++j) {
				if(gameSet) break;
				if(board[i][j] == 0) continue;
				
				for(int d=0; d<4; ++d) {
					int depth = 0;
					if(visited[i][j][d]) continue;
					
					depth += DFS(i, j, d) + rDFS(i, j, d) - 1;
					if(depth == 5) {
						gameSet = true;
						
						System.out.println(board[i][j]);
						System.out.println(i + " " + j);
					}
				}
			}
		}
		
		if(!gameSet) 
			System.out.println(0);
		
		sc.close();
	}
	
	private int DFS(int x, int y, int d) {
		int depth = 1;
		visited[x][y][d] = true;
		
		int nx = x + dx[d];
		int ny = y + dy[d];
		
		if(0 < nx && nx < 20 && 0 < ny && ny < 20) {
			if(board[x][y] == board[nx][ny]) {
				depth += DFS(nx, ny, d);
			}
		}
		
		return depth;
	}
	
	private int rDFS(int x, int y, int d) {
		int depth = 1;
		visited[x][y][d] = true;
		
		int nx = x - dx[d];
		int ny = y - dy[d];
		
		if(0 < nx && nx < 20 && 0 < ny && ny < 20) {
			if(board[x][y] == board[nx][ny]) {
				depth += DFS(nx, ny, d);
			}
		}
		
		return depth;
	}
	
	public static void main(String[] args) {
		new BOJ_2615().Solution();
	}
}