import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : BOARDCOVER.java
 * @Project : ALGO
 * @Date : 2020. 8. 21 
 * @author : "AoN"

 * @Description :
 * 문제 링크 : https://algospot.com/judge/problem/read/BOARDCOVER
 * 
 */

public class BoardCover {
	private BufferedReader br;
	private StringTokenizer st;
	private int H, W;
	private int[][] board;
	private final int[][][] coverType = {
			{ {0,0}, {1,0}, {0,1} },	// ┌
			{ {0,0}, {0,1}, {1,1} }, 	// ㄱ
			{ {0,0}, {1,0}, {1,1} }, 	// ㄴ
			{ {0,0}, {1,0}, {1,-1}},	// ┘
	};

	
	public BoardCover() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		while(--T >= 0) {
			// 1. 입력
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			board = new int[H][W];
			for(int i=0; i<H; ++i) {
				String row = br.readLine();
				for(int j=0; j<W; ++j) {
					board[i][j] = row.charAt(j) == '#' ? 1 : 0;
				}
			}	
			
			System.out.println(cover());
		}

		br.close();
	}
	
	// type은 블록의 모양 타입
	// delta=1이면 덮고, -1이면 되돌린다
	private boolean set(int x, int y, int type, int delta) {
		boolean flag = true;
		for(int i=0; i<3; ++i) {
			int nx = x + coverType[type][i][0];
			int ny = y + coverType[type][i][1];
			
			// 범위를 벗어나면
			if(nx < 0 || nx >= H || ny < 0 || ny >= W) {
				flag = false;
			}
			// 겹치는 경우(board의 값이 1보다 커지면)
			else if((board[nx][ny] += delta) > 1) {
				flag = false;
			}
		}
		return flag;
	}
	
	private int cover() {
		int x = -1, y = -1;
		
		// 비어있는 칸의 좌표를 찾는다
		loop:for(int i=0; i<H; ++i) {
			for(int j=0; j<W; ++j) {
				if(board[i][j] == 0) {
					x = i;
					y = j;
					break loop;
				}
			}
		}
		
		// (기저 사례) 모든 칸을 채웠다면 1을 반환
		if(y == -1 && x == -1) return 1;
		
		int ret = 0;
		for(int type=0; type<4; ++type) {
			if(set(x, y, type, 1)) {
				ret += cover();
			}
			// 덮었던 블록을 되돌린다
			set(x, y, type, -1);
		}
		
		return ret;
	}
	
	public static void main(String[] args) throws IOException{
		new BoardCover();
	}
}
