package d0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 8. 28 
 * @author : "AoN"

 * @Description : 7699. 수지의 수지 맞는 여행
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWqUzj0arpkDFARG&categoryId=AWqUzj0arpkDFARG&categoryType=CODE
 * 
 */

public class SWEA_7699{

	private int R, C, maxPath;
	private int[][] board;
	private boolean[] v;
	private int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	
	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder output = new StringBuilder();
		for(int tc=1; tc<=T; ++tc) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			board = new int[R][C];
			for(int i=0; i<R; ++i) {
				String row = br.readLine();
				for(int j=0; j<C; ++j) {
					board[i][j] = row.charAt(j) - 'A';
				}
			}
			
			v = new boolean[26];
			maxPath = 0;
			v[board[0][0]] = true;
			dfs(0,0,1);
			
			output.append('#').append(tc).append(' ').append(maxPath).append('\n');
		}

		System.out.println(output);
		
		br.close();
	}
	
	private void dfs(int x, int y, int depth) {
		maxPath = maxPath < depth ? depth : maxPath;
		
		if(depth>=26) {
			return;
		}
		
		for(int i=0; i<4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(0 > nx || nx >= R || 0 > ny || ny >= C) continue;
			if(!v[board[nx][ny]]) {
				v[board[nx][ny]] = true;
				dfs(nx, ny, depth+1);
				v[board[nx][ny]] = false;
			}	
		}
	}
	
	public static void main(String[] args) throws IOException {
		new SWEA_7699().run();
	}
}